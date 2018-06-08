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
	
	private IngredientBatchDAO() {
		try {
			Map<Integer,IngredientBatchDTO> ingredientBatches = (Map<Integer, IngredientBatchDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<Integer, IngredientBatchDTO>());
		}
	}
	
	public static IngredientBatchDAO getInstance()
	{
		return instance;
	}
	
	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		Map<Integer,IngredientBatchDTO> ingredientBatches = null;
		try {
			ingredientBatches = (Map<Integer, IngredientBatchDTO>) super.load();
			if (ingredientBatches.containsKey(ibId))
				return ingredientBatches.get(ibId);
			else
				throw new DALException("Ingredient with this ID does not exist.");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() {
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			return new ArrayList<>(ingredientBatches.values());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId)  throws DALException{
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			Map<Integer, IngredientBatchDTO> batchesOfIngredient = new HashMap<Integer, IngredientBatchDTO>();
			for (IngredientBatchDTO ingbatch : ingredientBatches.values())
				if (ingbatch.getIngredientId() == ingredientId)
					batchesOfIngredient.put(ingbatch.getIbID(), ingbatch);
			return new ArrayList<>(batchesOfIngredient.values());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			if (!ingredientBatches.containsKey(ingredientBatch.getIbID())) {
				ingredientBatches.put(ingredientBatch.getIbID(),ingredientBatch);
				super.save(ingredientBatches);
			} else
				throw new DALException("Ingredient with this ID already exists.");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		try {
			Map<Integer, IngredientBatchDTO> ingredientBatches = (HashMap<Integer, IngredientBatchDTO>) super.load();
			ingredientBatches.replace(ingredientBatch.getIbID(), ingredientBatch);
			super.save(ingredientBatches);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
