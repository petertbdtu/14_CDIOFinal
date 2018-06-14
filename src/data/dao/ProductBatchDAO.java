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
			Map<Integer,ProductBatchDTO> productBatches = (HashMap<Integer, ProductBatchDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<Integer, ProductBatchDTO>());
		}
	}
	
	public static ProductBatchDAO getInstance() {
		return instance;
	}
	
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
