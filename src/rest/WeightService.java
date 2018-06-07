package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import defaultPackage.runnableThread;


@Path("WeightService")
public class WeightService {
	
		@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void startWeight() {
			runnableThread runnable = new runnableThread();
			Thread thread = new Thread(runnable);
			thread.start();
		}
	
	
}
