package data.idao;

import java.util.List;
import data.dto.UserDTO;

public interface IUserDAO {
	UserDTO getUser(int oprId);
	List<UserDTO> getUserList();
	void createUser(UserDTO opr);
	void updateUser(UserDTO opr);
}
