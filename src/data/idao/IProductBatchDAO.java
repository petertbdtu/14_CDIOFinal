package data.idao;

import java.util.List;

import data.DALException;
import data.dto.ProductBatchDTO;

public interface IProductBatchDAO {
	ProductBatchDTO getProductBatch(int pbId);
	List<ProductBatchDTO> getProductBatchList();
	void createProductBatch(ProductBatchDTO productBatch) throws DALException; // Added DALException even though it is not given.
	void updateProductBatch(ProductBatchDTO productBatch);
}
