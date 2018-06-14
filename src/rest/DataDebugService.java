package rest;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.DALException;
import data.dao.IngredientBatchDAO;
import data.dao.IngredientDAO;
import data.dao.ProductBatchCompDAO;
import data.dao.ProductBatchDAO;
import data.dao.RecipeCompDAO;
import data.dao.RecipeDAO;
import data.dao.UserDAO;
import data.dto.IngredientBatchDTO;
import data.dto.IngredientDTO;
import data.dto.ProductBatchDTO;
import data.dto.RecipeCompDTO;
import data.dto.RecipeDTO;
import data.dto.UserDTO;

@Path("debug")
public class DataDebugService {

	/*
	 * Create debug data
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createData() throws WebDAOException {
		try {
			UserDTO usr = new UserDTO();
			usr.setUsrId(1);
			usr.setUsrName("TestBruger");
			usr.setIni("TB");
			usr.setCpr("XXXXXX-XXXX");
			usr.setLabTech(true);
			usr.setAdmin(true);
			usr.setPharma(true);
			usr.setProdLead(true);
			UserDAO.getInstance().createUser(usr);

			// Create recipe
			RecipeDTO rec = new RecipeDTO();
			rec.setRecipeId(1);
			rec.setRecipeName("KanelMadness");
			RecipeDAO.getInstance().createRecipe(rec);

			// Create recipe comp.
			RecipeCompDTO recComp = new RecipeCompDTO();
			recComp.setAmount(18);
			recComp.setIngredient(10);
			recComp.setRecipeId(1);
			recComp.setTolerance(2);
			RecipeCompDAO.getInstance().createRecipeComp(recComp);
			recComp.setAmount(18);
			recComp.setIngredient(11);
			recComp.setTolerance(1);
			RecipeCompDAO.getInstance().createRecipeComp(recComp);

			// Create ingredients
			IngredientDTO ing = new IngredientDTO();
			ing.setId(10);
			ing.setName("Kanel");
			IngredientDAO.getInstance().createIngredient(ing);
			ing.setId(11);
			ing.setName("Madness");
			IngredientDAO.getInstance().createIngredient(ing);

			// Create ingredient batch
			IngredientBatchDTO ingBat = new IngredientBatchDTO();
			ingBat.setIbId(100);
			ingBat.setAmount(1000);
			ingBat.setIngredientId(10);
			IngredientBatchDAO.getInstance().createIngredientBatch(ingBat);
			ingBat.setIbId(101);
			ingBat.setIngredientId(11);
			IngredientBatchDAO.getInstance().createIngredientBatch(ingBat);

			ProductBatchDTO pb = new ProductBatchDTO();
			pb.setStatus(1);
			pb.setPbId(1001);
			pb.setRecipeId(1);
			ProductBatchDAO.getInstance().createProductBatch(pb);

			return Response.status(200).build();
		} catch (DALException e) {
			throw new WebDAOException(e.getMessage());
		}
	}

	/*
	 * Delete current data
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteData() {
		String path = System.getProperty("user.home");
		String filePath = path + "/.weightData";
		File file = new File(filePath);
		File[] files = file.listFiles();
		for (File f : files) {
			f.delete();
		}

		UserDAO.getInstance().init();
		RecipeDAO.getInstance().init();
		IngredientDAO.getInstance().init();
		RecipeCompDAO.getInstance().init();
		ProductBatchDAO.getInstance().init();
		IngredientBatchDAO.getInstance().init();
		ProductBatchCompDAO.getInstance().init();
		
		return Response.status(200).build();
	}
}
