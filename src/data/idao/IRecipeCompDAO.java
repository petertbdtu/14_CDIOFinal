package data.idao;

import java.util.List;

import data.dto.RecipeCompDTO;

public interface IRecipeCompDAO {
	RecipeCompDTO getRecipeComp(int recipeId, int ingredientId);
	List<RecipeCompDTO> getRecipeCompList(int recipeId);
	List<RecipeCompDTO> getRecipeCompList();
	void createRecipeComp(RecipeCompDTO recipeComp);
	void updateRecipeComp(RecipeCompDTO recipeComp);
}
