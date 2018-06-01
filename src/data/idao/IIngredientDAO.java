package data.idao;

import java.util.List;

import data.dto.IngredientDTO;

public interface IIngredientDAO {
	IngredientDTO getIngredient(int ingredientId);
	List<IngredientDTO> getIngredientList();
	void createIngredient(IngredientDTO ingredient);
	void updateIngredient(IngredientDTO ingredient);
}
