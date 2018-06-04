package data.dao;

import data.dto.RecipeCompDTO;
import data.idao.IRecipeCompDAO;

import java.util.List;

public class RecipeCompDAO implements IRecipeCompDAO {

    @Override
    public RecipeCompDTO getRecipeComp(int recipeId, int ingredientId) {
        return null;
    }

    @Override
    public List<RecipeCompDTO> getRecipeCompList(int recipeId) {
        return null;
    }

    @Override
    public List<RecipeCompDTO> getRecipeCompList() {
        return null;
    }

    @Override
    public void createRecipeComp(RecipeCompDTO recipeComp) {

    }

    @Override
    public void updateRecipeComp(RecipeCompDTO recipeComp) {

    }
}
