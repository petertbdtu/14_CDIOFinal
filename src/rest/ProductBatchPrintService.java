package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.DALException;
import data.dao.IngredientBatchDAO;
import data.dao.IngredientDAO;
import data.dao.ProductBatchCompDAO;
import data.dao.ProductBatchDAO;
import data.dao.RecipeCompDAO;
import data.dao.RecipeDAO;
import data.dao.UserDAO;
import data.dto.ProductBatchCompDTO;
import data.dto.ProductBatchDTO;
import data.dto.RecipeCompDTO;
import data.dto.RecipeDTO;

/**
 * Combines data from just about every DTO into one monstrosity.
 */
@Path("printproductbatch")
public class ProductBatchPrintService {
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductBatchPrintCompDTO> readProductBatch(@PathParam("id") int pbId) throws WebDAOException {
		try {
			ProductBatchDTO productBatch = ProductBatchDAO.getInstance().getProductBatch(pbId);
			
			RecipeDTO recipe = RecipeDAO.getInstance().getRecipe(productBatch.getRecipeId());
			
			List<RecipeCompDTO> recipeComponents = RecipeCompDAO.getInstance()
					.getRecipeCompList(productBatch.getRecipeId());
			
			List<ProductBatchCompDTO> productBatchComponents = ProductBatchCompDAO.getInstance()
					.getProductBatchCompList(pbId);

			ArrayList<ProductBatchPrintCompDTO> pbinfoList = new ArrayList<ProductBatchPrintCompDTO>();

			for (RecipeCompDTO recipeComp : recipeComponents) {
				ProductBatchPrintCompDTO newComp = new ProductBatchPrintCompDTO();
				
				//TODO Implement splitting recept components into parts
				newComp.setPart(1);

				int ingredientId = recipeComp.getIngredientId();
				newComp.setIngredientId(ingredientId);
				newComp.setAmount(recipeComp.getAmount());
				newComp.setTolerance(recipeComp.getTolerance());

				newComp.setName(IngredientDAO.getInstance().getIngredient(ingredientId).getName());

				// Searches for product batch component with
				// matching ingredient ID in a list.
				for (ProductBatchCompDTO pbc : productBatchComponents) {
					if (recipeComp.getIngredientId() == IngredientBatchDAO.getInstance()
							.getIngredientBatch(pbc.getibID()).getIngredientId()) {
						newComp.setIbID(pbc.getibID());
						newComp.setTara(pbc.getTara());
						newComp.setNetto(pbc.getNetto());
						newComp.setIni(UserDAO.getInstance().getUser(pbc.getUsrID()).getIni());
					}
				}

				pbinfoList.add(newComp);
			}
			return pbinfoList;
		} catch (DALException e) {
			throw new WebDAOException(e.getMessage());
		}
	}

	private class ProductBatchPrintCompDTO {

		private String name; // Name of the ingredient
		private int ingredientId;
		private int amount; // Amount in the batch
		private double tolerance;
		private int ibId; // The ingredient batch it is from
		private double tara;
		private double netto;
		private String ini; // Initials of the operator that weighed it

		private int part;

		public ProductBatchPrintCompDTO() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIngredientId() {
			return ingredientId;
		}

		public void setIngredientId(int ingredientId) {
			this.ingredientId = ingredientId;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public double getTolerance() {
			return tolerance;
		}

		public void setTolerance(double tolerance) {
			this.tolerance = tolerance;
		}

		public int getIbID() {
			return ibId;
		}

		public void setIbID(int ibID) {
			this.ibId = ibID;
		}

		public double getTara() {
			return tara;
		}

		public void setTara(double tara) {
			this.tara = tara;
		}

		public double getNetto() {
			return netto;
		}

		public void setNetto(double netto) {
			this.netto = netto;
		}

		public String getIni() {
			return ini;
		}

		public void setIni(String ini) {
			this.ini = ini;
		}

		public int getPart() {
			return part;
		}

		public void setPart(int part) {
			this.part = part;
		}

	}
}
