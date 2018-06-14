package rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import data.DALException;
import data.dao.IngredientBatchDAO;
import data.dto.IngredientBatchDTO;

@Path("ingredientbatch")
public class IngredientBatchService {

	/*
	 * Used to call DAO.createIngredientBatch with
	 * input IngredientBatchDTO
	 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredientBatch(IngredientBatchDTO IngredientBatchDTO)
    {
        try {
        	IngredientBatchDAO.getInstance().createIngredientBatch(IngredientBatchDTO);
            return Response.ok().build();
        } catch (DALException e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    /*
     * Used to returns ALL ingredientBatches
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<IngredientBatchDTO> readIngredientBatchList()
    {
        return new ArrayList<>(IngredientBatchDAO.getInstance().getIngredientBatchList());
    }

    /*
     * Used to return specific ingredientBatch
     * given the id
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public IngredientBatchDTO readIngredient(@PathParam("id") int id)
    {
    	  try {
    		  return IngredientBatchDAO.getInstance().getIngredientBatch(id);
          } catch (DALException e) {
              e.printStackTrace();
              throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
          }
    }
}
