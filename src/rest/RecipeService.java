package rest;

import data.DALException;
import data.dao.RecipeDAO;
import data.dao.RecipeDAO;
import data.dto.RecipeDTO;
import data.dto.RecipeDTO;

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

@Path("recipe")
public class RecipeService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRecipe(RecipeDTO recipeDTO)
	{
		try {
			RecipeDAO.getInstance().createRecipe(recipeDTO);
			return Response.ok().build();
		} catch (DALException e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RecipeDTO> readRecipeList()
    {
        return new ArrayList<>(RecipeDAO.getInstance().getRecipeList());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipeDTO readRecipe(@PathParam("id") int id)
    {
        try {
			return RecipeDAO.getInstance().getRecipe(id);
		} catch (DALException e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
    }
}
