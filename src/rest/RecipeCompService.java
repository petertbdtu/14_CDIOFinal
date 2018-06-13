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
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RecipeCompDTO> readRecipeCompList()
    {
        return new ArrayList<>(RecipeCompDAO.getInstance().getRecipeCompList());
    }
	
	@GET
	  @Path("{recipeid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RecipeCompDTO> readRecipeCompList(@PathParam("recipeid") int recipeid)
    {
        return new ArrayList<>(RecipeCompDAO.getInstance().getRecipeCompList(recipeid));
    }

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
