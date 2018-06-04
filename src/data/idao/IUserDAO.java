package data.idao;

import java.util.List;
import java.util.Map;

import data.DALException;
import data.dto.UserDTO;

public interface IUserDAO {
	UserDTO getUser(int oprId) throws DALException;
	List getUserList();
	void createUser(UserDTO opr) throws DALException;
	void updateUser(UserDTO opr);
}
