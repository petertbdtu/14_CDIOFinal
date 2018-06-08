package rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebDAOExceptionMapper implements ExceptionMapper<WebDAOException> {
	
	@Override
	public Response toResponse(WebDAOException ex) {
		return Response.status(Status.BAD_REQUEST)
				.entity(new ErrorMsg(ex.getMessage()))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	public class ErrorMsg{
		String message;
		public ErrorMsg(String msg) {
			this.message = msg;
		}
		
		public String getMessage() {
			return this.message;
		}
	}
}
