package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.DALException;
import data.dao.StorageDAO;
import data.dao.UserDAO;
import data.dto.UserDTO;
import data.idao.IUserDAO;

public class UserTest {
	
	IUserDAO uds = UserDAO.getInstance();

    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StorageDAO storageDAO = new StorageDAO();
		storageDAO.deleteFile(UserDAO.class.getSimpleName());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(UserDAO.class.getSimpleName());
	}
	
	@Test
	public void testCreateUser() throws DALException {
		//opret bruger (userDTO�r)
		UserDTO user1 = new UserDTO();
		user1.setUsrId(1);
				
		//Add to datalayer
		uds.createUser(user1);
				
		//Check if .createUser
		UserDTO tempUser = uds.getUser(user1.getUsrId());
		assertEquals(user1.getUsrId(), tempUser.getUsrId());
	}


	@Test
	public void testGetUser() throws DALException {
		//opret bruger (userDTO�r)
		UserDTO user2 = new UserDTO();
		user2.setUsrId(2);
		
		
		//Tilf�j dem til userDAO
		uds.createUser(user2);
		
		//GetUser(UserDTO)
		UserDTO uds1 = uds.getUser(user2.getUsrId());
		assertEquals(user2.getUsrId(), uds1.getUsrId());
	}				

	@Test
	public void testGetUserList() throws DALException {
		//opret bruger (userDTO�r)
		UserDTO user3 = new UserDTO();
		user3.setUsrId(3);
		UserDTO user4 = new UserDTO();
		user4.setUsrId(4);
				
		//tilf�j til dem til userdao
		uds.createUser(user3);
		uds.createUser(user4);
			
		//tjekker om getUserList returner en listen med brugere. 
		boolean test3 = false;
		boolean test4 = false;
		
		for (UserDTO tempUser : uds.getUserList()) {
			if (tempUser.getUsrId() == 3)
				test3=true;
			else if (tempUser.getUsrId() == 4)
				test4= true;
		}
		
		assertTrue(test3 && test4);
	}
	
	
	@Test
	public void testUpdateUser() throws DALException {
		//opret bruger (userDTO�r)
		UserDTO user5 = new UserDTO();
		user5.setUsrId(5);
		user5.setUsrName("Jens");
		
		//Tilf�j bruger til userdao m. navn 
		uds.createUser(user5);
		
		//opdatere brugeren
		user5.setUsrName("Hans");
		uds.updateUser(user5);
				
		//Tjekker om opdateringen er sket. 
		UserDTO tempUser5 = uds.getUser(user5.getUsrId());
		assertTrue(tempUser5.getUsrName().equals("Hans"));
	
			}
}
