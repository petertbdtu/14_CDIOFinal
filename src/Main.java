import ase.WeightController;
import data.DALException;
import data.dao.*;
import data.dto.*;

public class Main {

    public static void main(String[] args) throws DALException {
    /*
        UserDTO usr = new UserDTO(); //USR
        usr.setUsrID(1);
        usr.setUsrName("Joachim");
        usr.setIni("j");
        usr.setCpr("sjov meme");
        usr.setRoles((byte)0b00001111);
        UserDAO.getInstance().createUser(usr);

        RecipeDTO rec = new RecipeDTO(); //RECIPE
        rec.setRecipeId(2);
        rec.setRecipeName("Urin");
        RecipeDAO.getInstance().createRecipe(rec);

        RecipeCompDTO recComp = new RecipeCompDTO(); //RECIPECOMP
        recComp.setAmount(500);
        recComp.setIngredient(10);
        recComp.setRecipeId(2);
        recComp.setTolerance(1); // TODO ÆNDRE TIL DOUBLE
        RecipeCompDAO.getInstance().createRecipeComp(recComp);
        recComp.setAmount(500);
        recComp.setIngredient(11);
        recComp.setTolerance(1); // TODO ÆNDRE TIL DOUBLE
        RecipeCompDAO.getInstance().createRecipeComp(recComp);

        IngredientDTO ing = new IngredientDTO(); //INGREDIENT
        ing.setId(10);
        ing.setName("Mountain Dew");
        ing.setAmount(100);
        IngredientDAO.getInstance().createIngredient(ing);
        ing.setId(11);
        ing.setName("Kaffe");
        ing.setAmount(100);
        IngredientDAO.getInstance().createIngredient(ing);

        IngredientBatchDTO ingBat = new IngredientBatchDTO(); //INGBATCH
        ingBat.setIbID(100);
        ingBat.setSupplier("peps");
        ingBat.setAmount(1000);
        ingBat.setIngbatchID(10);
        IngredientBatchDAO.getInstance().createIngredientBatch(ingBat);
        ingBat.setIbID(101);
        ingBat.setSupplier("carls");
        ingBat.setIngbatchID(11);
        IngredientBatchDAO.getInstance().createIngredientBatch(ingBat);

        ProductBatchDTO pb = new ProductBatchDTO();
        pb.setStatus("1");
        pb.setPbNr(1001);
        pb.setRecept(2);
        ProductBatchDAO.getInstance().createProductBatch(pb);
    */

        wc.run();

        for(ProductBatchCompDTO asd : ProductBatchCompDAO.getInstance().getProductBatchCompList(1000)){
            System.out.println(asd.getpbID());
        }
        for(ProductBatchCompDTO asd : ProductBatchCompDAO.getInstance().getProductBatchCompList(1001)){
            System.out.println(asd.getpbID());
        }
    }
}
