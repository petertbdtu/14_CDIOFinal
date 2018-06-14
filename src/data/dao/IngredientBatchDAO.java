package data.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import data.DALException;
import data.dto.IngredientBatchDTO;
import data.dto.IngredientDTO;
import data.idao.IIngredientBatchDAO;

public class IngredientBatchDAO extends StorageDAO implements IIngredientBatchDAO {
	
	private static IngredientBatchDAO instance = new IngredientBatchDAO();
	
	/*
	 * Constructor which ensures that a file exists.
	 */
	private IngredientBatchDAO() {
		try {
			Map<Integer,IngredientBatchDTO> ingredientBatches = (Map<Integer, IngredientBatchDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<Integer, IngredientBatchDTO>());
		}
	}
	
    /*
     * Used for static reference to instance.
     */
	public static IngredientBatchDAO getInstance()
	{
		return instance;
	}
	
	/*
	 * Returns a specific ingredient batch with
	 * the specific ID
	 */
	@Override
	public synchronized IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		Map<Integer,IngredientBatchDTO> ingredientBatches = null;
		try {
			ingredientBatches = (Map<Integer, IngredientBatchDTO>) super.load();
			if (ingredientBatches.containsKey(ibId))
				return ingredientBatches.get(ibId);
			else
				throw new DALException("Ingredient with this ID does not exist.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns a list with all ingredient batches
	 */
	@Override
	public synchronized List<IngredientBatchDTO> getIngredientBatchList() {
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			return new ArrayList<>(ingredientBatches.values());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns a list of ingredient batches which
	 * have the given ingredientId
	 */
	@Override
	public synchronized List<IngredientBatchDTO> getIngredientBatchList(int ingredientId)  throws DALException{
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			Map<Integer, IngredientBatchDTO> batchesOfIngredient = new HashMap<Integer, IngredientBatchDTO>();
			for (IngredientBatchDTO ingbatch : ingredientBatches.values())
				if (ingbatch.getIngredientId() == ingredientId)
					batchesOfIngredient.put(ingbatch.getIbId(), ingbatch);
			return new ArrayList<>(batchesOfIngredient.values());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Puts a ingredient batch to the map and saves
	 * to file.
	 * 
	 * ERROR if it already exists
	 */
	@Override
	public synchronized void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			if (!ingredientBatches.containsKey(ingredientBatch.getIbId())) {
				ingredientBatches.put(ingredientBatch.getIbId(),ingredientBatch);
				super.save(ingredientBatches);
			} else
				throw new DALException("Ingredient with this ID already exists.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Replaces an already created ingredient batch with
	 * the inputed one
	 */
	@Override
	public synchronized void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			ingredientBatches.replace(ingredientBatch.getIbId(), ingredientBatch);
			super.save(ingredientBatches);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
