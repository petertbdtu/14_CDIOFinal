package data.dao;
import data.DALException;
import data.dto.ProductBatchCompDTO;
import data.idao.IProductBatchCompDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

public class ProductBatchCompDAO extends StorageDAO implements IProductBatchCompDAO {

	private static ProductBatchCompDAO instance = new ProductBatchCompDAO();
	
	/*
	 * Constructor which ensures that a file exists.
	 */
	private ProductBatchCompDAO() {
		try {
			Map<String,ProductBatchCompDTO> complist = (Map<String, ProductBatchCompDTO>) super.load();
		} catch (ClassNotFoundException | IOException e) {
			super.save(new HashMap<String, ProductBatchCompDTO>());
		}
	}

	public static ProductBatchCompDAO getInstance() {
		return instance;
	}
	
	/*
	 * Returns a specific ProductBatchCompDTO with
	 * the specific IDs
	 */
	@Override
	public synchronized ProductBatchCompDTO getProductBatchComp(int pbId, int ibId) throws DALException {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			String compKey = generateKey(pbId, ibId);
			if(complist.containsKey(compKey))
				return complist.get(compKey);
			else
				throw new DALException("No product batch with these IDs exists.");
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns a list of ProductBatchCompDTOs which
	 * have the given pbID
	 */
	@Override
	public synchronized List<ProductBatchCompDTO> getProductBatchCompList(int pbId) {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
            List<ProductBatchCompDTO> batchesOfProduct = new ArrayList<>();
            for (ProductBatchCompDTO prodBatch : complist.values())
                if (prodBatch.getpbID() == pbId)
                    batchesOfProduct.add(prodBatch);
			return batchesOfProduct;
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * Returns a list with all ProductBatchCompDTOs
	 */
	@Override
	public synchronized List<ProductBatchCompDTO> getProductBatchCompList() {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			return new ArrayList<>(complist.values());
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Puts a productBatchComp to the map and saves
	 * to file.
	 * 
	 * ERROR if it already exists
	 */
	@Override
	public synchronized void createProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			String compString = generateKey(productBatchComp);
			if(!complist.containsKey(compString)) {
				complist.put(compString, productBatchComp);
				super.save(complist);
			}
			else {
				throw new DALException("A product batch with this ID already exists.");
			} 
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Replaces an already created productBatchComp with
	 * the inputted one
	 */
	@Override
	public synchronized void updateProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException {
		try {
			Map<String,ProductBatchCompDTO> complist = (HashMap<String, ProductBatchCompDTO>) super.load();
			String compString = generateKey(productBatchComp);
			if(complist.containsKey(compString)) {
				complist.replace(compString, productBatchComp);
				super.save(complist);
			} else {
				throw new DALException("No product batch with this ID exists.");
			} 
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Generates a string from 2 keys
	 */
	private String generateKey(int pbID, int ibID) {
		return pbID + "," + ibID;
	}
	
	/*
	 * Generates a string from 2 keys
	 */
	private String generateKey(ProductBatchCompDTO pbc) {
		return pbc.getpbID() + "," + pbc.getibID();
  }
}
