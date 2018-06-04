package data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import data.dto.ProductBatchCompDTO;
import data.idao.IProductBatchCompDAO;

public class ProductBatchCompDAO implements IProductBatchCompDAO {

	private static ProductBatchCompDTO instance = new ProductBatchCompDTO(); 
	List<ProductBatchCompDTO> complist = new ArrayList<>();

	@Override
	public ProductBatchCompDTO getProductBatchComp(int pbId, int ibId) {
		for(ProductBatchCompDTO comp : complist)
			if (comp.getpbID() == pbId && comp.getibID() == ibId) {
				return comp;
			}
			else {
				throw new DALException("No product batch with this ID exists.");
			}
	}
}

@Override
public List<ProductBatchCompDTO> getProductBatchCompList(int pbId) {
	List<ProductBatchCompDTO> prodbatch = new ArrayList<ProductBatchCompDTO>();
	for (ProductBatchCompDTO complist : prodbatch)
	{
		if (complist.getpbID() == pbId) {
			prodbatch.add(complist);
		}
		else {
			throw new DALException("No product batch list containing this ID exists.");
		}
	}
	return prodbatch;

}

@Override
public List<ProductBatchCompDTO> getProductBatchCompList() {
	return complist;
}

@Override
public void createProductBatchComp(ProductBatchCompDTO productBatchComp) {
	if(!complist.contains(productBatchComp)) {
		complist.add(productBatchComp.getpbID(), productBatchComp);
	}
	else {
		throw new DALException("A product batch with this ID already exists.");
	}

}

@Override
public void updateProductBatchComp(ProductBatchCompDTO productBatchComp) {
	if(complist.contains(productBatchComp))
		complist.add(productBatchComp);
	else {
		throw new DALException("No product batch with this ID exists.");
	}
}

}
