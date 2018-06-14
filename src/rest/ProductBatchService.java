package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.DALException;
import data.dao.ProductBatchDAO;
import data.dao.RecipeDAO;
import data.dto.ProductBatchDTO;

@Path("productbatch")
public class ProductBatchService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProductBatch(ProductBatchDTO productBatchDTO) throws WebDAOException
    {
        try {
        	// RecipeDAO getRecipe throws DALException if recipe does not exist.
        	RecipeDAO.getInstance().getRecipe(productBatchDTO.getRecipeId());
        	ProductBatchDAO.getInstance().createProductBatch(productBatchDTO);
            return Response.ok().build();
        } catch (DALException e) {
            throw new WebDAOException(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductBatchDTO> readProductBatchList()
    {
        return ProductBatchDAO.getInstance().getProductBatchList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductBatchDTO readProductBatch(@PathParam("id") int id) throws WebDAOException
    {
        try {
			return ProductBatchDAO.getInstance().getProductBatch(id);
		} catch (DALException e) {
			throw new WebDAOException(e.getMessage());
		}
    }
}
