package data.dao;

import data.idao.IProductBatchDAO;

import java.util.List;

import data.dto.ProductBatchDTO;

public class ProductBatchDAO implements IProductBatchDAO {
	public ProductBatchDAO instance = new ProductBatchDAO();
	public List<ProductBatchDTO> productBatches;
	
	
	public ProductBatchDAO() {
		productBatches= new ArrayList<ProductBatchDTO>();
	}
	
	
	public productBatchDAO getInstance() {
		return instance;
	}
	
	
	@Override
	public ProductBatchDTO getProductBatch(int pbId) {
		ProductBatchDTO productBatch = productBatches.get(pbId);
	}

	
	@Override
	public List<ProductBatchDTO> getProductBatchList() {
		return productBatches;
	}

	
	@Override
	public void createProductBatch(ProductBatchDTO productBatch) {
		if(productBatches.contains(productBatch) != true) {
			productBatches.add(productBatch.getPbNr(), productBatch);
		}
		
	}

	
	@Override
	public void updateProductBatch(ProductBatchDTO productBatch) {
		if (productBatches.contains(productBatch) != true) {
			productBatches.add(productBatch.getPbNr(), productBatch);
		}
		
	}

}
