package data.dao;
import java.util.*;

import data.dto.RecipeCompDTO;
import data.idao.IRecipeCompDAO;

public class RecipeCompDAO implements IRecipeCompDAO {

	@Override
	public RecipeCompDTO getRecipeComp(int recipeId, int ingredientId) {
	}

	@Override
	public List<RecipeCompDTO> getRecipeCompList(int recipeId) {
	}

	@Override
	public List<RecipeCompDTO> getRecipeCompList() {
	}

	@Override
	public void createRecipeComp(RecipeCompDTO recipeComp) {
		if(components.containsKey(ingredient));
	}

	@Override
	public void updateRecipeComp(RecipeCompDTO recipeComp) {
	}

}
