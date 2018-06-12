import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ase.WeightController;
import data.DALException;
import data.dao.*;
import data.dto.*;

public class Main {

    public static void main(String[] args) throws DALException {
    	/*WeightController wc = new WeightController();
    	wc.run();
    	/*
    	//Create Test User
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

        //Create recipe
        RecipeDTO rec = new RecipeDTO();
        rec.setRecipeId(1);
        rec.setRecipeName("KanelMadness");
        RecipeDAO.getInstance().createRecipe(rec);

        //Create recipe comp.
        RecipeCompDTO recComp = new RecipeCompDTO();
        recComp.setAmount(500);
        recComp.setIngredient(10);
        recComp.setRecipeId(1);
        recComp.setTolerance(2);
        RecipeCompDAO.getInstance().createRecipeComp(recComp);
        recComp.setAmount(500);
        recComp.setIngredient(11);
        recComp.setTolerance(1);
        RecipeCompDAO.getInstance().createRecipeComp(recComp);

        //Create ingredients
        IngredientDTO ing = new IngredientDTO();
        ing.setId(10);
        ing.setName("Kanel");
        IngredientDAO.getInstance().createIngredient(ing);
        ing.setId(11);
        ing.setName("Madness");
        IngredientDAO.getInstance().createIngredient(ing);

        //Create ingredient batch
        IngredientBatchDTO ingBat = new IngredientBatchDTO();
        ingBat.setIbID(100);
        ingBat.setAmount(1000);
        ingBat.setIngredientId(10);
        IngredientBatchDAO.getInstance().createIngredientBatch(ingBat);
        ingBat.setIbID(101);
        ingBat.setIngredientId(11);
        IngredientBatchDAO.getInstance().createIngredientBatch(ingBat);

        ProductBatchDTO pb = new ProductBatchDTO();
        pb.setStatus(1);
        pb.setPbId(1001);
        pb.setRecipeId(1);
        ProductBatchDAO.getInstance().createProductBatch(pb);
        */
    }
}
