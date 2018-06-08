package data.dao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.DALException;
import data.dto.ProductBatchDTO;
import data.idao.IProductBatchDAO;

public class ProductBatchDAO extends StorageDAO implements IProductBatchDAO {
	
	private static ProductBatchDAO instance = new ProductBatchDAO();
	
	private ProductBatchDAO() {
		try {
			Map<Integer,ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<Integer, ProductBatchDTO>());
		}
	}
	
	public static ProductBatchDAO getInstance() {
		return instance;
	}
	
	@Override
	public ProductBatchDTO getProductBatch(int pbId) {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
			return productBatches.get(pbId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<ProductBatchDTO> getProductBatchList() {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
			return new ArrayList<ProductBatchDTO>(productBatches.values());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createProductBatch(ProductBatchDTO productBatch) throws DALException {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
			if(productBatches.containsKey(productBatch.getPbId())) {
				throw new DALException("ProductBatch with this ID already exists.");
			}
			else if (RecipeDAO.getInstance().getRecipe(productBatch.getRecipeId()) == null)
			{
				throw new DALException("Recipe for this ProductBatch does not exist.");
			}
			else
			{
				productBatches.put(productBatch.getPbId(), productBatch);
				super.save(productBatches);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductBatch(ProductBatchDTO productBatch) throws DALException {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
			if (productBatches.containsKey(productBatch.getPbId()))
			{
				productBatches.replace(productBatch.getPbId(), productBatch);
				super.save(productBatches);
			}
			else
			{
				throw new DALException("No ProductBatch with this ID exists.");
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
