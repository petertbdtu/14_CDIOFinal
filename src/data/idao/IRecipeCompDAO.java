package data.idao;

import java.util.List;

import data.DALException;
import data.dto.RecipeCompDTO;

public interface IRecipeCompDAO {
	RecipeCompDTO getRecipeComp(int recipeId, int ingredientId) throws DALException;
	List<RecipeCompDTO> getRecipeCompList(int recipeId);
	List<RecipeCompDTO> getRecipeCompList();
	void createRecipeComp(RecipeCompDTO recipeComp) throws DALException;
	void updateRecipeComp(RecipeCompDTO recipeComp) throws DALException;
}
