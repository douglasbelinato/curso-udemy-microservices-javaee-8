package com.acme.customers.api.rest.v1.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource mapping 
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	/**
	 * End point 
	 */
	@GET
	public Response hello() {
		return Response.ok("Hello").build();
	}
}
