package data.idao;

import java.util.List;

import data.DALException;
import data.dto.IngredientDTO;

public interface IIngredientDAO {
	IngredientDTO getIngredient(int ingredientId);
	List<IngredientDTO> getIngredientList();
	void createIngredient(IngredientDTO ingredient) throws DALException;
	void updateIngredient(IngredientDTO ingredient) throws DALException;
}
