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
import data.dto.ProductBatchCompDTO;

public class ProductBatchCompTest {
	ProductBatchCompDAO instance;
	ProductBatchCompDTO pbc1;
	ProductBatchCompDTO pbc2;
	ProductBatchCompDTO pbc3;
	ProductBatchCompDTO pbc4;
	ProductBatchCompDTO pbc5;
	ProductBatchCompDTO pbc6;
	
	@Before
	public void setUp() throws Exception {
		instance = ProductBatchCompDAO.getInstance();
		
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
			instance.createProductBatchComp(pbc1);
			instance.createProductBatchComp(pbc2);
			instance.createProductBatchComp(pbc3);
			instance.createProductBatchComp(pbc4);
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
	public void testGetProductBatchComp() {
		try {
			ProductBatchCompDTO actual = instance.getProductBatchComp(1, 2);
			ProductBatchCompDTO expected = pbc1;
			assertEquals(expected, actual);
		} catch(DALException e) {
			fail("No product batch with these IDs exists.");
		}
	}

	@Test
	public void testGetProductBatchCompListInt() {
		List<ProductBatchCompDTO> actual = instance.getProductBatchCompList(3);
		List<ProductBatchCompDTO> expected = new ArrayList<>();
		expected.add(pbc3);
		expected.add(pbc4);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetProductBatchCompList() {
		List<ProductBatchCompDTO> actual = instance.getProductBatchCompList();
		List<ProductBatchCompDTO> expected = new ArrayList<>();
		expected.add(pbc1);
		expected.add(pbc2);
		expected.add(pbc3);
		expected.add(pbc4);
		assertEquals(actual, expected);
	}

	@Test
	public void testCreateProductBatchComp() {
		try {
			instance.createProductBatchComp(pbc5);
			assertNotNull(pbc5);
		} catch(DALException e) {
			fail("Ingredient with this ID already exists.");
		}
		
		try {
			instance.createProductBatchComp(pbc6);
		} catch(DALException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testUpdateProductBatchComp() {
		try {
			pbc1.setNetto(66);
			instance.updateProductBatchComp(pbc1);
			assertTrue(pbc1.getNetto() == 66);
		} catch(DALException e) {
		fail("Update failed");
		}
	}

}
