package rest;

import data.DALException;
import data.dao.IngredientDAO;
import data.dto.IngredientDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import java.util.ArrayList;

// Obs. at bad request kastes på tre forskellige måder. Den første eller anden er nemmest.

@Path("ingredient")
public class IngredientService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredient(IngredientDTO ingredientDTO)
    {
        try {
            IngredientDAO.getInstance().createIngredient(ingredientDTO);
            return Response.ok().build();
        } catch (DALException e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
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
    public Response updateIngredient(IngredientDTO ingredientDTO)
    {
        try {
            IngredientDAO.getInstance().updateIngredient(ingredientDTO);
            return Response.ok().build();

        } catch (DALException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
