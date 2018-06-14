package data.idao;

import java.util.List;

import data.DALException;
import data.dto.RecipeDTO;

public interface IRecipeDAO {
	RecipeDTO getRecipe(int RecipeId) throws DALException;
	List<RecipeDTO> getRecipeList();
	void createRecipe(RecipeDTO Recipe) throws DALException;
	void updateRecipe(RecipeDTO Recipe) throws DALException;
}
