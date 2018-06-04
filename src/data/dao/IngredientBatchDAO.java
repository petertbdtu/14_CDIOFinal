package data.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.dto.IngredientBatchDTO;
import data.idao.IIngredientBatchDAO;

public class IngredientBatchDAO extends StorageDAO implements IIngredientBatchDAO {
	
	private static IngredientBatchDAO instance = new IngredientBatchDAO();
	
	private IngredientBatchDAO() {}
	
	public static IngredientBatchDAO getInstance()
	{
		return instance;
	}
	
	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) {
		List<IngredientBatchDTO> ingredientBatches = (ArrayList<IngredientBatchDTO>) super.load();
		IngredientBatchDTO ingbatch = ingredientBatches.get(ibId);
		if (ingbatch != null)
			return ingredientBatches.get(ibId);
		else
			throw new DALException("Ingredient with this ID does not exist.");
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() {
		try {
			List<IngredientBatchDTO> ingredientBatches = (ArrayList<IngredientBatchDTO>) super.load();
			return ingredientBatches;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) {
		try {
			List<IngredientBatchDTO> ingredientBatches = (ArrayList<IngredientBatchDTO>) super.load();
			List<IngredientBatchDTO> batchesOfIngredient = new ArrayList<IngredientBatchDTO>();
			for (IngredientBatchDTO ingbatch : ingredientBatches)
			{
				if (ingbatch.getIbID() == ingredientId)
					batchesOfIngredient.add(ingbatch);
			}
			return batchesOfIngredient;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		List<IngredientBatchDTO> ingredientBatches = (ArrayList<IngredientBatchDTO>) super.load();
		if (!ingredientBatches.contains(ingredientBatch)) {
			ingredientBatches.add(ingredientBatch.getIbID(), ingredientBatch);
			super.save(ingredientBatches);
		} else
			throw new DALException("Ingredient with this ID already exists.");
		
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		List<IngredientBatchDTO> ingredientBatches = (ArrayList<IngredientBatchDTO>) super.load();
		if (!ingredientBatches.contains(ingredientBatch)) {
			ingredientBatches.add(ingredientBatch.getIbID(), ingredientBatch);
			super.save(ingredientBatches);

		} else
			throw new DALException("Ingredient with this ID does not exist.");
		
	}

}
