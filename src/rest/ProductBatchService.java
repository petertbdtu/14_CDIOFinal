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
import data.dao.ProductBatchDAO;
import data.dto.ProductBatchDTO;

@Path("productbatch")
public class ProductBatchService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIngredient(ProductBatchDTO productBatchDTO)
    {
        try {
        	ProductBatchDAO.getInstance().createProductBatch(productBatchDTO);
            return Response.ok().build();
        } catch (DALException e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ProductBatchDTO> readProductBatchList()
    {
        return new ArrayList<>(ProductBatchDAO.getInstance().getProductBatchList());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductBatchDTO readIngredient(@PathParam("id") int id)
    {
        return ProductBatchDAO.getInstance().getProductBatch(id);
    }
}
