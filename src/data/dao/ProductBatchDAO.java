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
	
	/*
	 * Constructor which ensures that a file exists.
	 */
	private ProductBatchDAO() {
		init();
	}

	/**
	 * Used to ensure file existence.
	 */
	public void init() {
		try {
			Map<Integer,ProductBatchDTO> productBatches = (HashMap<Integer, ProductBatchDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<Integer, ProductBatchDTO>());
		}
	}
	
    /*
     * Used for static reference to instance.
     */
	public static ProductBatchDAO getInstance() {
		return instance;
	}
	
	/*
	 * Returns a specific Product batch with
	 * the specific ID
	 */
	@Override
	public synchronized ProductBatchDTO getProductBatch(int pbId) throws DALException {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (HashMap<Integer, ProductBatchDTO>) super.load();
			if (productBatches.containsKey(pbId))
			{
				return productBatches.get(pbId);
			}
			else
			{
				throw new DALException("No product batch with this ID exists");
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns a list with all Product batches
	 */
	@Override
	public synchronized List<ProductBatchDTO> getProductBatchList() {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (HashMap<Integer, ProductBatchDTO>) super.load();
			return new ArrayList<>(productBatches.values());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Puts a product batch to the map and saves
	 * to file.
	 * 
	 * ERROR if it already exists
	 */
	@Override
	public synchronized void createProductBatch(ProductBatchDTO productBatch) throws DALException {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (HashMap<Integer, ProductBatchDTO>) super.load();
			if(productBatches.containsKey(productBatch.getPbId())) {
				throw new DALException("ProductBatch with this ID already exists.");
			} else {
				productBatches.put(productBatch.getPbId(), productBatch);
				super.save(productBatches);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Replaces an already created product batch with
	 * the inputed one
	 */
	@Override
	public synchronized void updateProductBatch(ProductBatchDTO productBatch) throws DALException {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (HashMap<Integer, ProductBatchDTO>) super.load();
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
