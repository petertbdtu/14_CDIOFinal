package data.dao;

import java.util.*;
import java.util.List;
import java.util.Iterator;

import data.dto.ProductBatchCompDTO;
import data.idao.IProductBatchCompDAO;

public class ProductBatchCompDAO implements IProductBatchCompDAO {

	private static ProductBatchCompDAO instance = new ProductBatchCompDAO();

	private ProductBatchCompDAO() {
		try {
			Map<Integer,ProductBatchCompDTO> complist = (Map<Integer, ProductBatchCompDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<Integer, ProductBatchCompDTO>());
		}
	}

	@Override
	public ProductBatchCompDTO getProductBatchComp(int pbId, int ibId) {
		try {
			Map<Integer,ProductBatchCompDTO> complist = (HashMap<Integer, ProductBatchCompDTO>) super.load();
			ProductBatchCompDTO comp = new ProductBatchCompDTO();
			for(int i=0; i<complist.size(); i++) {
				if (comp.getpbID() == pbId && comp.getibID() == ibId) {
					return comp;
				}
				else {
					throw new DALException("No product batch with this ID exists.");
				}
			}
		}
		catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

@Override
public List<ProductBatchCompDTO> getProductBatchCompList(int pbId) {
	try {
		Map<Integer,ProductBatchCompDTO> complist = (HashMap<Integer, ProductBatchCompDTO>) super.load();
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
	catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

@Override
public List<ProductBatchCompDTO> getProductBatchCompList() {
	try {
		Map<Integer,ProductBatchCompDTO> complist = (HashMap<Integer, ProductBatchCompDTO>) super.load();
		return new ArrayList<ProductBatchCompDTO>(complist.values());
	}
	catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@Override
public void createProductBatchComp(ProductBatchCompDTO productBatchComp) {
	try {
		Map<Integer,ProductBatchCompDTO> complist = (HashMap<Integer, ProductBatchCompDTO>) super.load();
		String compString = productBatchComp.generateKey();
		if(!complist.containsKey(compString)) {
			complist.replace(compString, productBatchComp);
		}
		else {
			throw new DALException("A product batch with this ID already exists.");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateProductBatchComp(ProductBatchCompDTO productBatchComp) {
		try {
			Map<Integer,ProductBatchCompDTO> complist = (HashMap<Integer, ProductBatchCompDTO>) super.load();
			String compString = productBatchComp.generateKey();
			if(!complist.containsKey(compString)) {
				complist.replace(compString, productBatchComp);
			}
			else {
				throw new DALException("No product batch with this ID exists.");
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
