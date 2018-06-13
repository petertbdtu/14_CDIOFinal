package data.dao;

import data.DALException;
import data.dto.IngredientBatchDTO;
import data.dto.UserDTO;
import data.idao.IUserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO extends StorageDAO implements IUserDAO {

    private static UserDAO instance = new UserDAO();

    private UserDAO() {
        try {
            Map<Integer, UserDTO> users = (Map<Integer, UserDTO>) super.load();
        } catch (ClassNotFoundException | IOException e) {
            super.save(new HashMap<Integer, UserDTO>());
        }
    }

    public static UserDAO getInstance()
    {
        return instance;
    }
  
    @Override
    public synchronized UserDTO getUser(int id) throws DALException {
        try {
            Map<Integer,UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            if (users.containsKey(id))
                return users.get(id);
            else
                throw new DALException("User with this ID does not exist.");
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized List<UserDTO> getUserList() {
        try {
            Map<Integer, UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            return new ArrayList<>(users.values());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized void createUser(UserDTO userDTO) throws DALException {
        try {
            Map<Integer, UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            if (!users.containsKey(userDTO.getUsrId())) {
                users.put(userDTO.getUsrId(), userDTO);
                super.save(users);
            } else
                throw new DALException("User with this ID already exists.");
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void updateUser(UserDTO userDTO) throws DALException {
        try {
            Map<Integer, UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            if (users.containsKey(userDTO.getUsrId())) {
                users.replace(userDTO.getUsrId(), userDTO);
                super.save(users);
            } else
                throw new DALException("User with this ID does not exist.");     
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
