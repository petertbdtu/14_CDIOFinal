package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.DALException;
import data.dao.ProductBatchDAO;
import data.dao.RecipeCompDAO;
import data.dao.StorageDAO;
import data.dto.ProductBatchDTO;
import data.idao.IProductBatchDAO;

public class ProductBatchTest {

	IProductBatchDAO data = ProductBatchDAO.getInstance();
	
	@Before
	public void setUpBeforeClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(RecipeCompDAO.class.getSimpleName());
	}

	@After
	public void tearDown() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(ProductBatchDAO.class.getSimpleName());
	}

	@Test
	public void testGetProductBatch() throws DALException {
		ProductBatchDTO ProductBatch1337 = new ProductBatchDTO();
		ProductBatch1337.setPbId(1337);
		
		data.createProductBatch(ProductBatch1337);
		
		ProductBatchDTO testProductBatch = data.getProductBatch(ProductBatch1337.getPbId());
		assertEquals(ProductBatch1337.getPbId(), testProductBatch.getPbId());
	}

	@Test
	public void testGetProductBatchList() throws DALException{
		//Create test ingredients
		ProductBatchDTO ProductBatch666 = new ProductBatchDTO();
		ProductBatchDTO ProductBatch777 = new ProductBatchDTO();
		ProductBatch666.setPbId(666);
		ProductBatch777.setPbId(777);
		data.createProductBatch(ProductBatch666);
		data.createProductBatch(ProductBatch777);
		
		boolean pb666 = false;
		boolean pb777 = false;
		for (ProductBatchDTO tempProductBatch : data.getProductBatchList()) {
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
			
			data.createProductBatch(productBatch123);
		
			ProductBatchDTO testProductBatch = data.getProductBatch(productBatch123.getPbId());
			assertEquals(productBatch123.getPbId(), testProductBatch.getPbId());
	}

	@Test
	public void testUpdateProductBatch() throws DALException {
		ProductBatchDTO ProductBatch10 = new ProductBatchDTO();
		ProductBatch10.setPbId(100);
		ProductBatch10.setStatus(0);
			data.createProductBatch(ProductBatch10);
			
			ProductBatch10.setStatus(1);
			data.updateProductBatch(ProductBatch10);
			
			ProductBatchDTO testProductBatch = data.getProductBatch(ProductBatch10.getPbId());
			assertTrue(testProductBatch.getStatus() == 1);
	}

}
