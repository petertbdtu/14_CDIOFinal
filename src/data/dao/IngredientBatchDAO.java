package data.dao;

import java.util.ArrayList;
import java.util.List;

import data.dto.IngredientBatchDTO;
import data.idao.IIngredientBatchDAO;

public class IngredientBatchDAO implements IIngredientBatchDAO {
	
	private static IngredientBatchDAO instance = new IngredientBatchDAO();
	private List<IngredientBatchDTO> ingredientBatches;
	
	private IngredientBatchDAO()
	{
		// Load from file
		ingredientBatches = new ArrayList<IngredientBatchDTO>();
	}
	
	public static IngredientBatchDAO getInstance()
	{
		return instance;
	}
	
	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) {
		IngredientBatchDTO ingbatch = ingredientBatches.get(ibId);
		if (ingbatch != null)
			return ingredientBatches.get(ibId);
		else
			throw new DALException("Ingredient with this ID does not exist.");
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() {
		return ingredientBatches;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) {
		List<IngredientBatchDTO> batchesOfIngredient = new ArrayList<IngredientBatchDTO>();
		for (IngredientBatchDTO ingbatch : ingredientBatches)
		{
			if (ingbatch.getIbID() == ingredientId)
				batchesOfIngredient.add(ingbatch);
		}
		return batchesOfIngredient;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		if (!ingredientBatches.contains(ingredientBatch))
			ingredientBatches.add(ingredientBatch.getIbID(), ingredientBatch);
		else
			throw new DALException("Ingredient with this ID already exists.");
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		if (!ingredientBatches.contains(ingredientBatch))
			ingredientBatches.add(ingredientBatch.getIbID(), ingredientBatch);
		else
			throw new DALException("Ingredient with this ID does not exist.");
		
	}

}
