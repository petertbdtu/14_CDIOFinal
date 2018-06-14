package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import data.DALException;
import data.dao.UserDAO;
import data.dto.UserDTO;



@Path("useradmin")
public class UserAdminService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO userDTO) throws WebDAOException
    {
    	try {
    		UserDAO.getInstance().createUser(userDTO);
    		return Response.ok().build();  //laver en response 200 ok og bygger den
    	}catch (DALException e) {
    		throw new WebDAOException(e.getMessage());
    	}
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<UserDTO> readUserList()
    {
        return UserDAO.getInstance().getUserList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO readUser(@PathParam("id") int id) throws WebDAOException
    {
        try {
			return UserDAO.getInstance().getUser(id);
		} catch (DALException e) {
			throw new WebDAOException(e.getMessage());
		}
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(UserDTO userDTO) throws WebDAOException
    {
        try {
            UserDAO.getInstance().updateUser(userDTO);
            return Response.ok().build();
        } catch (DALException e) {
            throw new WebDAOException(e.getMessage());
        }
    }
}
