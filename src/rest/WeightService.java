package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ase.WeightThreadController;

@Path("WeightService")
public class WeightService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response startWeight(String ip) {
		String tmp = ip.replaceAll("\"", "");
		WeightThreadController wt = WeightThreadController.getInstance();
		if(!wt.isAlive()) {
			wt.newThread(tmp);
			return Response.status(200).build();
		}
		
		return Response.status(500).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkStatus() {
		WeightThreadController wt = WeightThreadController.getInstance();
		return wt.isAlive();
	}


}
