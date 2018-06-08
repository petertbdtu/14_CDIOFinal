package data.idao;

import java.util.List;
import java.util.Map;
import data.DALException;
import data.dto.IngredientBatchDTO;

public interface IIngredientBatchDAO {
	IngredientBatchDTO getIngredientBatch(int ibId) throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList() throws DALException;
	Map<Integer, IngredientBatchDTO> getIngredientBatchList(int ibId) throws DALException;
	void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
	void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
}
