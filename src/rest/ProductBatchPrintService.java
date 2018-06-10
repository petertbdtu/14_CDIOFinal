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
import data.dto.ProductBatchPrintCompDTO;
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
				newComp.setPart(1); // Nobody knows what this is

				int ingredientId = recipeComp.getIngredientId();
				newComp.setIngredientId(ingredientId);
				newComp.setAmount(recipeComp.getAmount());
				newComp.setTolerance(recipeComp.getTolerance());

				newComp.setName(IngredientDAO.getInstance().getIngredient(ingredientId).getName());

				// I hate this. Searches for product batch component with matching ingredient ID
				// in a list.
				for (ProductBatchCompDTO pbc : productBatchComponents) {
					// AAUUUGHHH
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
		} 
		catch (NullPointerException e) {
			/* Can theoretically occur if any of the following are nonexistent:
			 * ProductBatch for the inputted ID.
			 * Recipe for the ProductBatch (ProductBatch should not exist if this is the case)
			 * 
			 */
			
			throw new WebDAOException("No product batch with this ID exists");
		}
		catch (DALException e) {
			e.printStackTrace();
			throw new WebDAOException(e.getMessage());
		}
	}
}
