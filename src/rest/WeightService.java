package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ase.runnableThread;

@Path("WeightService")
public class WeightService {
	Thread thread;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response startWeight() {
		if(thread == null){
			thread = new Thread(new runnableThread());
		}

		if(thread.isAlive() == false) {
			thread.start();
			return Response.status(200).build();
		}
		
		return Response.status(500).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkStatus() {
		if(thread == null){return false;}
		return thread.isAlive();
	}


}
