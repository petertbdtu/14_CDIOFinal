package data.idao;

import java.util.List;

import data.dto.IngredientBatchDTO;

public interface IIngredientBatchDAO {
	IngredientBatchDTO getIngredientBatch(int ibId);
	List<IngredientBatchDTO> getIngredientBatchList();
	List<IngredientBatchDTO> getIngredientBatchList(int ingredientId);
	void createIngredientBatch(IngredientBatchDTO ingredientBatch);
	void updateIngredientBatch(IngredientBatchDTO ingredientBatch);
}
