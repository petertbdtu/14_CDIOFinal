package data.dao;
import java.io.IOException;
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
			return (List<ProductBatchDTO>) productBatches.values();
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
			if(!productBatches.containsKey(productBatch.getPbId())) {
				productBatches.put(productBatch.getPbId(), productBatch);
				super.save(productBatches);
			}
			else
			{
				throw new DALException("ProductBatch with this ID already exists.");
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new DALException("Mystery server exception that Joachim knows about");
		}
	}

	@Override
	public void updateProductBatch(ProductBatchDTO productBatch) {
		try {
			Map<Integer, ProductBatchDTO> productBatches = (Map<Integer, ProductBatchDTO>) super.load();
			productBatches.replace(productBatch.getPbId(), productBatch);
			super.save(productBatches);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
