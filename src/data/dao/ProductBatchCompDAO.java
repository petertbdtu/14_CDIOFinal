package data.dao;

import data.dto.ProductBatchCompDTO;
import data.idao.IProductBatchCompDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductBatchCompDAO extends StorageDAO implements IProductBatchCompDAO {

	private static ProductBatchCompDAO instance = new ProductBatchCompDAO();

	private ProductBatchCompDAO() {
		try {
			Map<String,ProductBatchCompDTO> complist = (Map<String, ProductBatchCompDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<String, ProductBatchCompDTO>());
		}
	}

	@Override
	public ProductBatchCompDTO getProductBatchComp(int pbId, int ibId) {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
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


	@Override
	public List<ProductBatchCompDTO> getProductBatchCompList(int pbId) {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			return (List<ProductBatchCompDTO>) complist.values();

		}
		catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ProductBatchCompDTO> getProductBatchCompList() {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			return (List<ProductBatchCompDTO>) complist.values();
		}
		catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void createProductBatchComp(ProductBatchCompDTO productBatchComp) {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			String compString = productBatchComp.generateKey();
			if(!complist.containsKey(compString)) {
				complist.replace(compString, productBatchComp);
				super.save(complist);
			}
			else {
				throw new DALException("A product batch with this ID already exists.");
			} 
		}
		catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void updateProductBatchComp(ProductBatchCompDTO productBatchComp) {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			String compString = productBatchComp.generateKey();
			if(!complist.containsKey(compString)) {
				complist.replace(compString, productBatchComp);
				super.save(complist);
			}
			else {
				throw new DALException("No product batch with this ID exists.");
			} 
		}
		catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
