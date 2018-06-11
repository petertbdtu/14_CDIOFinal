package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.DALException;
import data.dao.ProductBatchCompDAO;
import data.dao.StorageDAO;
import data.dto.IngredientBatchDTO;
import data.dto.ProductBatchCompDTO;
import data.idao.IProductBatchCompDAO;

public class ProductBatchCompTest {
	IProductBatchCompDAO pbcd;
	ProductBatchCompDTO pbc1;
	ProductBatchCompDTO pbc2;
	ProductBatchCompDTO pbc3;
	ProductBatchCompDTO pbc4;
	ProductBatchCompDTO pbc5;
	ProductBatchCompDTO pbc6;
	
	@Before
	public void setUp() throws Exception {
		pbcd = ProductBatchCompDAO.getInstance();
		
		pbc1 = new ProductBatchCompDTO();
		pbc1.setibID(2);
		pbc1.setpbID(1);
		pbc1.setUsrID(3);
		pbc1.setTara(5.4);
		pbc1.setNetto(12.1);
		
		pbc2 = new ProductBatchCompDTO();
		pbc2.setibID(2);
		pbc2.setpbID(2);
		pbc2.setUsrID(3);
		pbc2.setTara(5.4);
		pbc2.setNetto(12.1);
		
		pbc3 = new ProductBatchCompDTO();
		pbc3.setibID(1);
		pbc3.setpbID(3);
		pbc3.setUsrID(3);
		pbc3.setTara(5.4);
		pbc3.setNetto(12.1);
		
		pbc4 = new ProductBatchCompDTO();
		pbc4.setibID(5);
		pbc4.setpbID(3);
		pbc4.setUsrID(5);
		pbc4.setTara(5.4);
		pbc4.setNetto(12.1);
		
		pbc5 = new ProductBatchCompDTO();
		pbc5.setibID(99);
		pbc5.setpbID(95);
		pbc5.setUsrID(94);
		pbc5.setTara(6.4);
		pbc5.setNetto(93);
		
		pbc6 = new ProductBatchCompDTO();
		pbc6.setibID(99);
		pbc6.setpbID(95);
		pbc6.setUsrID(94);
		pbc6.setTara(6.4);
		pbc6.setNetto(93);
		
		try {
			pbcd.createProductBatchComp(pbc1);
			pbcd.createProductBatchComp(pbc2);
			pbcd.createProductBatchComp(pbc3);
			pbcd.createProductBatchComp(pbc4);
		} catch (DALException e) {
			
		}
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(ProductBatchCompDAO.class.getSimpleName());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(ProductBatchCompDAO.class.getSimpleName());
	}

	@Test
	public void testGetProductBatchComp() throws DALException {
		ProductBatchCompDTO tempPbc = pbcd.getProductBatchComp(1, 2);
		boolean key1 = (pbc1.getibID() == tempPbc.getibID());
		boolean key2 = (pbc1.getpbID() == tempPbc.getpbID());
		assertTrue(key1 && key2);
	}

	@Test
	public void testGetProductBatchCompListInt() throws DALException {
		//Check if .testGetIngredientList returns ingredient10/20
		boolean test1 = false;
		boolean test2 = false;
		
		for (ProductBatchCompDTO tempPbc : pbcd.getProductBatchCompList(3)) {
			if (tempPbc.getibID() == 1)
				test1 = true;
			else if(tempPbc.getibID() == 5)
				test2 = true;
		}
		
		assertTrue(test1 && test2);
	}

	@Test
	public void testGetProductBatchCompList() throws DALException {
		//Check if .testGetIngredientList returns ingredient10/20
		boolean test1 = false;
		boolean test2 = false;
		boolean test3 = false;
		boolean test4 = false;
		
		for (ProductBatchCompDTO tempPbc : pbcd.getProductBatchCompList()) {
			if (tempPbc.getpbID() == 1 && tempPbc.getibID() == 2)
				test1 = true;
			else if (tempPbc.getpbID() == 2 && tempPbc.getibID() == 2)
				test2 = true;
			else if (tempPbc.getpbID() == 3 && tempPbc.getibID() == 1)
				test3 = true;
			else if (tempPbc.getpbID() == 3 && tempPbc.getibID() == 5)
				test4 = true;
		}
		
		
		assertTrue(test1 && test2 && test3 && test4);
	}

	@Test
	public void testCreateProductBatchComp() throws DALException {
		pbcd.createProductBatchComp(pbc5);
		ProductBatchCompDTO tempPbc = pbcd.getProductBatchComp(pbc5.getpbID(), pbc5.getibID());
		
		boolean key1 = (pbc5.getibID() == tempPbc.getibID());
		boolean key2 = (pbc5.getpbID() == tempPbc.getpbID());
		assertTrue(key1 && key2);
	}

	@Test
	public void testUpdateProductBatchComp() throws DALException {
		pbc1.setNetto(66);
		pbcd.updateProductBatchComp(pbc1);
		ProductBatchCompDTO tempPbc = pbcd.getProductBatchComp(pbc1.getpbID(), pbc1.getibID());
		assertTrue(66.0 == tempPbc.getNetto());
	}

}
