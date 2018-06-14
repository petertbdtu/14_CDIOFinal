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

	/*
	 * Used to call DAO.createIngredient with
	 * input ingredientDTO
	 */
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

    /*
     * Used to return af list of all
     * ingredients
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<IngredientDTO> readIngredientList()
    {
        return new ArrayList<>(IngredientDAO.getInstance().getIngredientList());
    }

    /*
     * Used to return a specific ingredient
     * with param id
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public IngredientDTO readIngredient(@PathParam("id") int id)
    {
        return IngredientDAO.getInstance().getIngredient(id);
    }

    /*
     * Used to call DAO.updateIngredient
     * with input ingredientDTO
     */
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
