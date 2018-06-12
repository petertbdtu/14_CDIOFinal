package ase;

import data.DALException;
import java.util.List;

import data.dto.UserDTO;
import data.dto.RecipeCompDTO;
import data.dto.ProductBatchDTO;
import data.dto.IngredientBatchDTO;
import data.dto.ProductBatchCompDTO;

import data.idao.IProductBatchCompDAO;
import data.idao.IIngredientBatchDAO;
import data.idao.IProductBatchDAO;
import data.idao.IRecipeCompDAO;
import data.idao.IRecipeDAO;
import data.idao.IUserDAO;

import data.dao.UserDAO;
import data.dao.RecipeDAO;
import data.dao.RecipeCompDAO;
import data.dao.ProductBatchDAO;
import data.dao.IngredientBatchDAO;
import data.dao.ProductBatchCompDAO;

public class WeightController implements Runnable {
	
	IWeightSocket ws;
	State state;
	
	//DAOs - Access to datalayer
	IUserDAO ud;
	IRecipeDAO rd;
	IRecipeCompDAO rcd;
	IProductBatchDAO pbd;
	IIngredientBatchDAO ibd;
	IProductBatchCompDAO pbcd;
		
	//DTOs used to store values for saving
	ProductBatchCompDTO curPbc;
	IngredientBatchDTO curIb;
	ProductBatchDTO curPb;
	RecipeCompDTO curRc;
	UserDTO curLab;

	public enum State {
		LAB_ID, PB_ID, RB_ID
	}

	public WeightController() {
		ws = new WeightSocket();
		state = State.LAB_ID;
		
		ud = UserDAO.getInstance();
		rd = RecipeDAO.getInstance();
		rcd = RecipeCompDAO.getInstance();
		pbd = ProductBatchDAO.getInstance();
		ibd = IngredientBatchDAO.getInstance();
		pbcd = ProductBatchCompDAO.getInstance();
	}

	@Override
	public void run() {
		boolean isRunning = true;

		while(isRunning) {
			switch (state) {
			case LAB_ID:
				if(LabId())
					state = State.PB_ID;
				break;
			case PB_ID:
				if(PbId())
					state = State.RB_ID;
				else
					state = State.LAB_ID;
				break;
			case RB_ID:
				RbId();
				if(moreIngredients())
					state = State.RB_ID;
				else
					state = State.LAB_ID;
				break;
			default:
				state = State.LAB_ID;
				break;
			}
		}
	}

	private boolean LabId() {
		curLab = null;
		
		ws.clearText();
		ws.tare();
		int labNo = ws.getInput("Indtast operatornr:");

		//check labNo inside domain
		if(labNo < 1 || labNo > 999){
			errorInState("Ugyldigt operatornr.");
			return false;
		}

		try {
			//Ensure userId exists
			curLab = ud.getUser(labNo);
		} catch (DALException e) {
			errorInState("Ugyldigt operatornr.");
			return false;
		}
		
		ws.showText("Velkommen "+ curLab.getUsrName());
		ws.sleep(3);
		ws.clearText();
		
		//Ensure correct userId was entereed
		return ws.getConfirmation("Er brugeren korrekt?");     
	}

	private boolean PbId() {
		curPb = null;
		int pbId = ws.getInput("Indtast produktbatch nr.:");

		//check pbNo inside domain
		if(pbId < 1 || pbId > 99999999){
			errorInState("Ugyldigt produktbatch nr.");
			return false;
		}
		String tmpRecipeName = null;
		try {
			//Ensure pbId exists
			curPb = pbd.getProductBatch(pbId);
			tmpRecipeName = rd.getRecipe(curPb.getRecipeId()).getRecipeName();
		} catch (DALException e) {
			errorInState("Ugyldigt produktbatch nr.");
			return false;
		}
		
		//Ensure PB.status = not done
		if(curPb.getStatus() == 2) {
			errorInState("Produktbatch er afsluttet");
			return false;
		}
		
		ws.showText("Recept: " + tmpRecipeName);
		ws.sleep(3);
		ws.clearText();
		
		//Ensure correct pbId entered
		if(!ws.getConfirmation("Korrekt produktbatch?"))
			return false;
		
		//Set PB.status = active
		curPb.setStatus(1);
		try {
			pbd.updateProductBatch(curPb);
		} catch (DALException e) {
			errorInState("P.batch ikke opdateret");
			return false;
		}
		return true;
	}

	private void RbId() {
		curIb = null;
		curRc = null;
		
		//Create new PBC with known values
		curPbc = new ProductBatchCompDTO();
		curPbc.setpbID(curPb.getPbId());
		curPbc.setUsrID(curLab.getUsrId());

		//tara registration
		if(!ws.getConfirmation("Er vægten ubelastet?"))
			while(!ws.getConfirmation("Er alt fjernet fra vægten?"));
		ws.tare();
		ws.haltProgress("Placer tara");
		curPbc.setTara(ws.tare());

		//Check if valid ibId
		try {
			curIb = ibd.getIngredientBatch(ws.getInput("Indtast RaavareBatch nr."));
		} catch (DALException e) {
			errorInState("Ugyldigt raavarebatch nr.");
			return;
		}

		//Check if ingredientBatch.ingredientId match recipe.ingredientId
		if(!pbcInRecipe()) {
			errorInState("Ugyldigt raavarebatch nr.");
			return;
		}
		curPbc.setibID(curIb.getIbId());

		//Check if PBC already done
		if(pbcAlreadyDone()) {
			errorInState("Raavare allerede afvejet");
			return;
		}

		//Get weight
		ws.haltProgress("Placer "+ curRc.getAmount()+"g");
		curPbc.setNetto(ws.getWeight());
		
		//Reset
		ws.tare();
		ws.haltProgress("Fjern alt");

		//Get brut and check
		int brut = ws.tare();
		if(Math.abs(brut - (curPbc.getNetto() + curPbc.getTara())) <= 10) {
			errorInState("Bruttocheck fejlet, Proev igen");
			return;
		}

		//Get diff and check tolerance
		double diffWeight = Math.abs(curRc.getAmount() - curPbc.getNetto());
		if(diffWeight / curRc.getAmount() * 100 > curRc.getTolerance()) {
			errorInState("Udenfor tolerence, Proev igen");
			return;
		}
		
		try {
			pbcd.createProductBatchComp(curPbc);
		} catch (DALException e) {
			errorInState("Kunne ikke gemme afvejning");
			return;
		}
		
		ws.showText("Godkendt, Afvejning gemt");
		ws.sleep(3);
		ws.clearText();
	}

	private void errorInState(String msg) {
		ws.showError();
		ws.showText(msg);
		ws.sleep(3);
		ws.clearText();
	}
	
	private boolean pbcInRecipe() {
		for (RecipeCompDTO rcTemp : rcd.getRecipeCompList(curPb.getRecipeId())) {
			if(rcTemp.getIngredientId() == curIb.getIngredientId()) {
				curRc = rcTemp;
				return true;
			}
		}
		return false;
	}

	private boolean pbcAlreadyDone() {
		try {
			for(ProductBatchCompDTO pbcTemp : pbcd.getProductBatchCompList(curPb.getPbId()))
				if(pbcTemp.getibID() == curIb.getIbId())
					return true;
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean moreIngredients() {
		boolean moreIngredients = false;
		try {
			List<RecipeCompDTO> rcList = rcd.getRecipeCompList(curPb.getRecipeId());
			List<ProductBatchCompDTO> pbcList = pbcd.getProductBatchCompList(curPb.getPbId());
			moreIngredients = (rcList.size() != pbcList.size());
		} catch (DALException e) {}

		if(moreIngredients) {
			moreIngredients = ws.getConfirmation("Vil du afveje mere?");
		} else {
			curPb.setStatus(2);
			try {
				pbd.updateProductBatch(curPb);
			} catch (DALException e) {
				errorInState("P.batch ikke opdateret");
			}
		}

		return moreIngredients;	
	}	
}
