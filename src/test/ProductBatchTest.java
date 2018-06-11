package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import data.DALException;
import data.dao.ProductBatchDAO;
import data.dao.StorageDAO;
import data.dto.ProductBatchDTO;
import data.idao.IProductBatchDAO;

public class ProductBatchTest {

	IProductBatchDAO pbd = ProductBatchDAO.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(ProductBatchDAO.class.getSimpleName());
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(ProductBatchDAO.class.getSimpleName());
	}

	@Test
	public void testGetProductBatch() throws DALException {
		ProductBatchDTO ProductBatch1337 = new ProductBatchDTO();
		ProductBatch1337.setPbId(1337);
		
		pbd.createProductBatch(ProductBatch1337);
		
		ProductBatchDTO testProductBatch = pbd.getProductBatch(ProductBatch1337.getPbId());
		assertEquals(ProductBatch1337.getPbId(), testProductBatch.getPbId());
	}

	@Test
	public void testGetProductBatchList() throws DALException{
		//Create test ingredients
		ProductBatchDTO ProductBatch666 = new ProductBatchDTO();
		ProductBatchDTO ProductBatch777 = new ProductBatchDTO();
		ProductBatch666.setPbId(666);
		ProductBatch777.setPbId(777);
		pbd.createProductBatch(ProductBatch666);
		pbd.createProductBatch(ProductBatch777);
		
		boolean pb666 = false;
		boolean pb777 = false;
		for (ProductBatchDTO tempProductBatch : pbd.getProductBatchList()) {
			if (tempProductBatch.getPbId() == 666)
				pb666 = true;
			else if(tempProductBatch.getPbId() == 777)
				pb777 = true;
		}
		
		assertTrue(pb666 && pb777);
	}

	@Test
	public void testCreateProductBatch() throws DALException {
			ProductBatchDTO productBatch123 = new ProductBatchDTO();
			productBatch123.setPbId(1234);
			
			pbd.createProductBatch(productBatch123);
		
			ProductBatchDTO testProductBatch = pbd.getProductBatch(productBatch123.getPbId());
			assertEquals(productBatch123.getPbId(), testProductBatch.getPbId());
	}

	@Test
	public void testUpdateProductBatch() throws DALException {
		ProductBatchDTO ProductBatch10 = new ProductBatchDTO();
		ProductBatch10.setPbId(100);
		ProductBatch10.setStatus(0);
			pbd.createProductBatch(ProductBatch10);
			
			ProductBatch10.setStatus(1);
			pbd.updateProductBatch(ProductBatch10);
			
			ProductBatchDTO testProductBatch = pbd.getProductBatch(ProductBatch10.getPbId());
			assertTrue(testProductBatch.getStatus() == 1);
		
		//initiate new product batch, but won't create it.
		ProductBatchDTO ProductBatch66 = new ProductBatchDTO();
		ProductBatch66.setPbId(66);
		ProductBatch66.setStatus(0);	
		//trying to update the product batch that isnt created.
			try {
				ProductBatch66.setStatus(1);
				pbd.updateProductBatch(ProductBatch66);
			} catch(DALException e) {
				assertTrue(true);
			}
	}

}
