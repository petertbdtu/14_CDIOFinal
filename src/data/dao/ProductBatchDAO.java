package data.dao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<Integer,ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
		return productBatches.get(pbId);
	}

	
	@Override
	public List<ProductBatchDTO> getProductBatchList() {
		Map<Integer,ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
		return (List<ProductBatchDTO>) productBatches.values();
	}

	
	@Override
	public void createProductBatch(ProductBatchDTO productBatch) {
		Map<Integer,ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
		if(!productBatches.containsKey(productBatch.getPbNr())) {
			productBatches.put(productBatch.getPbNr(), productBatch);
			super.save(productBatches);
		}
		
	}

	
	@Override
	public void updateProductBatch(ProductBatchDTO productBatch) {
		Map<Integer,ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
		productBatches.replace(productBatch.getPbNr(), productBatch);
		super.save(productBatches);
	}

}
