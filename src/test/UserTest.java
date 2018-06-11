package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.DALException;
import data.dao.StorageDAO;
import data.dao.UserDAO;
import data.dto.UserDTO;
import data.idao.IUserDAO;

public class UserTest {
	
	IUserDAO uds = UserDAO.getInstance();

    @Before
	public void setUp() throws Exception {
		StorageDAO storageDAO = new StorageDAO();
		storageDAO.deleteFile(UserDAO.class.getSimpleName());
	}

	@After
	public void tearDown() throws Exception {
		StorageDAO sd = new StorageDAO();
		sd.deleteFile(UserDAO.class.getSimpleName());
		
//		File file = new File([DAO].getPath());
//        if(!file.delete) {
//              System.out.println("Failed to delete the file");
//          }
	}
	
	@Test
	public void testCreateUser() throws DALException {
		//opret bruger (userDTOér)
		UserDTO user1 = new UserDTO();
		user1.setUsrId(1);
				
		//Add to datalayer
		uds.createUser(user1);
				
		//Check if .getIngredient returns ingredient1
		UserDTO tempUser = uds.getUser(user1.getUsrId());
		assertEquals(user1.getUsrId(), tempUser.getUsrId());
	}


	@Test
	public void testGetUser() throws DALException {
		//opret bruger (userDTOér)
		UserDTO user1 = new UserDTO();
		user1.setUsrId(1);
		
		
		//Tilføj dem til userDAO
		uds.createUser(user1);
		
		//GetUser(UserDTO)
		UserDTO uds1 = uds.getUser(user1.getUsrId());
		assertEquals(user1.getUsrId(), uds1.getUsrId());
	}				

	@Test
	public void testGetUserList() throws DALException {
		//opret bruger (userDTOér)
		UserDTO user3 = new UserDTO();
		user3.setUsrId(3);
		UserDTO user4 = new UserDTO();
		user4.setUsrId(4);
				
		//tilføj til dem til userdao
		uds.createUser(user3);
		uds.createUser(user4);
			
		//tjekker om getUserList returner en listen med brugere. 
		boolean user30 = false;
		boolean user40 = false;
		
		for (UserDTO tempUser : uds.getUserList()) {
			if (testUsers.getUsrId()== 30)
				user30=true;
			else if (testUsers.getUsrId()==40)
				user40= true;
		}
		assertTrue(user30 && user40);
	}
	
	
	@Test
	public void testUpdateUser() throws DALException {
		//opret bruger (userDTOér)
		UserDTO user5 = new UserDTO();
		user5.setUsrId(5);
		user5.setUsrName("Jens");
		
		//Tilføj bruger til userdao m. navn 
		uds.createUser(user5);
		
		//opdatere brugeren
		user5.setUsrName("Hans");
		uds.updateUser(user5);
				
		//Tjekker om opdateringen er sket. 
		UserDTO tempUser5 = uds.getUser(user5.getUsrId());
		assertTrue(tempUser5.getUsrName().equals("Hans"));
	
			}
}
