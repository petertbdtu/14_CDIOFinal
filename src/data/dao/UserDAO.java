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
    public UserDTO getUser(int oprId) throws DALException {
        try {
            Map<Integer,UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            if (users.containsKey(oprId))
                return users.get(oprId);
            else
                throw new DALException("User with this ID does not exist.");
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getUserList() {
        try {
            Map<Integer, UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            return new ArrayList<UserDTO>(users.values());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(UserDTO opr) throws DALException {
        try {
            Map<Integer, UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            if (!users.containsKey(opr.getUsrID())) {
                users.put(opr.getUsrID(), opr);
                super.save(users);
            } else
                throw new DALException("Ingredient with this ID already exists.");
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserDTO opr) {
        try {
            Map<Integer, UserDTO> users = (HashMap<Integer, UserDTO>) super.load();
            users.replace(opr.getUsrID(), opr);
            super.save(users);
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
