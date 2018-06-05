package data.idao;

import java.util.List;

import data.DALException;
import data.dto.ProductBatchCompDTO;

public interface IProductBatchCompDAO {
	ProductBatchCompDTO getProductBatchComp(int pbId, int ibId) throws DALException, DALException;
	List<ProductBatchCompDTO> getProductBatchCompList(int pbId) throws DALException;
	List<ProductBatchCompDTO> getProductBatchCompList() throws DALException;
	void createProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException;
	void updateProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException;
}
