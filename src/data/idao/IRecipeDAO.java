package data.idao;

import java.util.List;

import data.dto.RecipeDTO;

public interface IRecipeDAO {
	RecipeDTO getRecipe(int RecipeId);
	List<RecipeDTO> getRecipeList();
	void createRecipe(RecipeDTO Recipe);
	void updateRecipe(RecipeDTO Recipe);
}
