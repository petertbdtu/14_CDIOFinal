package data.idao;

import java.util.List;

import data.DALException;
import data.dto.ProductBatchDTO;

public interface IProductBatchDAO {
	ProductBatchDTO getProductBatch(int pbId) throws DALException;
	List<ProductBatchDTO> getProductBatchList();
	void createProductBatch(ProductBatchDTO productBatch) throws DALException;
	void updateProductBatch(ProductBatchDTO productBatch) throws DALException;
}
