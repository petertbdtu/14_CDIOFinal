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
import data.dao.RecipeCompDAO;
import data.dto.RecipeCompDTO;

@Path("recipeComp")
public class RecipeCompService {

	/*
	 * Used to call DAO.createRecipeComp with
	 * input RecipeCompDTO
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRecipeComp(RecipeCompDTO recipeCompDTO)
	{
		try {
			RecipeCompDAO.getInstance().createRecipeComp(recipeCompDTO);
			return Response.ok().build();
		} catch (DALException e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
	}
	
	/*
	 * Used to call DAO.createRecipeComp on
	 * each element in compList
	 */
	@Path("list")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRecipeComponents(ArrayList <RecipeCompDTO> compList)
	{
		try {
			for (RecipeCompDTO comp : compList ) {
				RecipeCompDAO.getInstance().createRecipeComp(comp);
			}
			return Response.ok().build();
		} catch (DALException e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
	}
	
	/*
	 * Used to return all recipeCompDTOs
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RecipeCompDTO> readRecipeCompList()
    {
        return new ArrayList<>(RecipeCompDAO.getInstance().getRecipeCompList());
    }
	
	/*
	 * Used to return a list of all recipeCompDTOs
	 * with specific recipeID
	 */
	@GET
	  @Path("{recipeid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RecipeCompDTO> readRecipeCompList(@PathParam("recipeid") int recipeid)
    {
        return new ArrayList<>(RecipeCompDAO.getInstance().getRecipeCompList(recipeid));
    }

	/*
	 * Used to return specific recipeCompDTO
	 * with recipeID and ingredientID
	 */
    @GET
    @Path("{recipeid}+{ingredientid}")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipeCompDTO readRecipeComp(@PathParam("recipeid") int recipeid, @PathParam("ingredientid") int ingredientid)
    {
        try {
			return RecipeCompDAO.getInstance().getRecipeComp(recipeid, ingredientid);
		} catch (DALException e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
    }
}
