package data.idao;

import java.util.List;

import data.dto.ProductBatchCompDTO;

public interface IProductBatchCompDAO {
	ProductBatchCompDTO getProductBatchComp(int pbId, int ibId);
	List<ProductBatchCompDTO> getProductBatchCompList(int pbId);
	List<ProductBatchCompDTO> getProductBatchCompList();
	void createProduktBatchKomp(ProductBatchCompDTO productBatchComp);
	void updateProduktBatchKomp(ProductBatchCompDTO productBatchComp);
}
