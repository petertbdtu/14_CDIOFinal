package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.DALException;
import data.dao.UserDAO;
import data.dto.UserDTO;
import data.idao.IUserDAO;

import java.io.File;

public class UserTest {
	
	IUserDAO uds;

	@Before
	public void setUp() throws Exception {
		uds = UserDAO.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		//File file = new File([DAO].getPath());
        //if(!file.delete) {
        //      System.out.println("Failed to delete the file");
        //  }
	}

	@Test
	public void testGetUser() throws DALException {
		//1. Oprettes nogle UserDTOer
		//2. Tilføje dem til userDAO
		//3. GetUser(UserDTO)
		
    	UserDTO ud1 = new UserDTO();
    	ud1.setUsrId(1);
		
    	//KORREKT
		assertEquals(ud1.getUsrId(), uds.getUser(1).getUsrId());
		
    	//FORKERT
		assertEquals(ud1, uds.getUser(1));
	}

	@Test
	public void testGetUserList() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

}
