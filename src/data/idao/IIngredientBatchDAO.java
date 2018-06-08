package data.idao;

import java.util.List;

import data.DALException;
import data.dto.IngredientBatchDTO;

public interface IIngredientBatchDAO {
	IngredientBatchDTO getIngredientBatch(int ibId) throws DALException;
	List<?> getIngredientBatchList() throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException;
	void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
	void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
}
