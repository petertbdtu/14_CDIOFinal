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

public class WeightController {
	
	IWeightSocket ws;
	State state;
	UserDTO curLab;
	ProductBatchDTO curPb;
	
	public enum State {
	    LAB_ID, PB_ID, RB_ID
	}
	
	public WeightController() {
		ws = new WeightSocket();
		state = State.LAB_ID;
	}

	public void run() throws Exception {
		boolean isRunning = true;
		
		ws.clearText();
		ws.tare();
		
		while(isRunning) {
			switch (state) {
				case LAB_ID:
					if(LabId())
						state = State.PB_ID;
					break;
				case PB_ID:
					if(PbId())
						state = State.RB_ID;
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
	
	private void errorInState(String msg) {
        ws.showError();
        ws.showText(msg);
        ws.sleep(3);
        ws.clearText();
	}
	
	private boolean LabId() {
		curLab = null;
		String tmp = ws.getInput("Indtast operatornr:");
        int labNo = Integer.parseInt(tmp);
        
        //check labNo inside domain
        if(labNo < 1 || labNo > 999){
        	errorInState("Ugyldigt operatornr.");
        	return false;
        }

        try {
        	IUserDAO ud = UserDAO.getInstance();
            curLab = ud.getUser(labNo);
            
            //Ensure correct userID was entereed
            ws.showText("Velkommen "+ curLab.getUsrName());
            ws.sleep(3);
            ws.clearText();
            return ws.getConfirmation("Er brugeren korrekt?");     
        } catch (DALException e) {
        	errorInState("Ugyldigt operatornr.");
        }
        return false;
    }
	
	private boolean PbId() {
		curPb = null;
		String tmp = ws.getInput("Indtast produktbatch nr.:");
        int pbId = Integer.parseInt(tmp);
        
        //check pbNo inside domain
        if(pbId < 1 || pbId > 99999999){
        	errorInState("Ugyldigt produktbatch nr.");
            return false;
        }

        try {
        	IProductBatchDAO pbd = ProductBatchDAO.getInstance();
        	IRecipeDAO rd = RecipeDAO.getInstance();
            curPb = pbd.getProductBatch(pbId);
            tmp = rd.getRecipe(curPb.getRecept()).getRecipeName();
            
            //Ensure correct userID was entereed
            ws.showText("Recept: " + tmp);
            ws.sleep(3);
            ws.clearText();
            if(ws.getConfirmation("Korrekt produktbatch?")) {
            	if(curPb.getStatus().equals("2")) { //TODO CHANGE TO INTEGER
            		errorInState("Produktbatch er afsluttet");
                    return false;
            	} else {
            		curPb.setStatus("1"); //TODO CHANGE TO INTEGER
            		pbd.updateProductBatch(curPb);
            		return true;
            	}
        	} else {
            	return false;
            }
        } catch (DALException e) {
            errorInState("Ugyldigt produktbatch nr.");
        }
        return false;
	}

	private void RbId() {
		IRecipeCompDAO rcd = RecipeCompDAO.getInstance();
		List<RecipeCompDTO> rcList = rcd.getRecipeCompList(curPb.getRecept());
		RecipeCompDTO curRc = null;
		
		IIngredientBatchDAO ibd = IngredientBatchDAO.getInstance();
		IngredientBatchDTO curIb;
		
		IProductBatchCompDAO pbcd = ProductBatchCompDAO.getInstance();
		
		//Create new PBC with known values
		ProductBatchCompDTO curPbc = new ProductBatchCompDTO();
		curPbc.setpbID(curPb.getPbNr());
		curPbc.setUsrID(curLab.getUsrID());
		
        //tara registration
		if(!ws.getConfirmation("Er vægten ubelastet?"))
			while(!ws.getConfirmation("Er alt fjernet fra vægten?"));
        ws.tare();
        ws.haltProgress("Placer tara");
        curPbc.setTara(ws.tare());
       
        //Check if valid ibId
        try {
            curIb = ibd.getIngredientBatch(Integer.parseInt(ws.getInput("Indtast RaavareBatch nr.")));
        } catch (DALException e) {
        	errorInState("Ugyldigt raavarebatch nr.");
        	return;
        }
        
        //Check if ingredientBatch ingredientId match recipe
        boolean correctRbId = false;
        for (RecipeCompDTO rcTemp : rcList)
        	if(rcTemp.getIngredientId() == curIb.getIngbatchID()) {
        		correctRbId = true;
        		curRc = rcTemp;
        	}
        if(!correctRbId) {
        	errorInState("Ugyldigt raavarebatch nr.");
        	return;
        }
        curPbc.setibID(curIb.getIbID());
            
        //Check if PBC already done
        boolean alreadyDone = false;
        try {
			for(ProductBatchCompDTO pbcTemp : pbcd.getProductBatchCompList(curPb.getPbNr()))
				if(pbcTemp.getibID() == curIb.getIbID())
					alreadyDone = true;
		} catch (DALException e) {}
        
        if(alreadyDone) {
        	errorInState("Raavare allerede afvejet");
        	return;
        }
        
        //Get weight and check inside tolerance
        ws.haltProgress("Placer "+ curRc.getAmount()+"g");
        curPbc.setNetto(ws.getWeight() - curPbc.getTara());
        
        ws.tare();
        ws.haltProgress("Fjern alt");
        
        double diffWeight = Math.abs(curRc.getAmount() - curPbc.getNetto());
        if(diffWeight / curRc.getAmount() * 100 > curRc.getTolerance()) {
        	errorInState("Udenfor tolerence, Proev igen");
        	return;
        } else {
        	ws.showText("Godkendt, Afvejning gemt");
        	ws.sleep(3);
        	ws.clearText();
            try {
				pbcd.createProductBatchComp(curPbc);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
  
	private boolean moreIngredients() {
		IRecipeCompDAO rcd = RecipeCompDAO.getInstance();
		IProductBatchCompDAO pbcd = ProductBatchCompDAO.getInstance();
		IProductBatchDAO pbd = ProductBatchDAO.getInstance();
		boolean moreIngredients = false;
        try {
    		List<RecipeCompDTO> rcList = rcd.getRecipeCompList(curPb.getRecept());
    		List<ProductBatchCompDTO> pbcList = pbcd.getProductBatchCompList(curPb.getPbNr());
    		if(rcList.size() != pbcList.size())
    			moreIngredients = true;
		} catch (DALException e) {}
        
        if(moreIngredients) {
        	moreIngredients = ws.getConfirmation("Vil du afveje mere?");
        } else {
        	curPb.setStatus("2"); //TODO CHANGE TO INTEGER
        	pbd.updateProductBatch(curPb);
        }
        
		return moreIngredients;	
	}	
}
