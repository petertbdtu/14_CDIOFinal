package rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.DALException;
import data.dao.IngredientDAO;
import data.dto.IngredientDTO;

@Path("ingredient")
public class IngredientService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredient(IngredientDTO ingredientDTO) throws WebDAOException
    {
        try {
            IngredientDAO.getInstance().createIngredient(ingredientDTO);
            return Response.ok().build();
        } catch (DALException e) {
            throw new WebDAOException(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<IngredientDTO> readIngredientList()
    {
        return new ArrayList<>(IngredientDAO.getInstance().getIngredientList());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public IngredientDTO readIngredient(@PathParam("id") int id)
    {
        return IngredientDAO.getInstance().getIngredient(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIngredient(IngredientDTO ingredientDTO) throws WebDAOException
    {
        try {
            IngredientDAO.getInstance().updateIngredient(ingredientDTO);
            return Response.ok().build();

        } catch (DALException e) {
        	throw new WebDAOException(e.getMessage());
        }
    }
}
