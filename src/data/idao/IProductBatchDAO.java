package data.idao;

import java.util.List;

import data.dto.ProductBatchDTO;

public interface IProductBatchDAO {
	ProductBatchDTO getProductBatch(int pbId);
	List<ProductBatchDTO> getProductBatchList();
	void createProductBatch(ProductBatchDTO productBatch);
	void updateProductBatch(ProductBatchDTO productBatch);
}
