package com.acme.customers.api.rest.v1.resources;

import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.acme.customers.api.rest.v1.services.CustomerService;
import com.acme.customers.lib.v1.Customer;

/**
 * Resource mapping 
 */
@Path("/customers")
// Application Scope since we want API Stateless, so that we don't need 
// more than one instance of each resource 
@ApplicationScoped   
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	@Inject
	private CustomerService customerService;

    /**
     * Get a list of customers using pagination
     * 
     * @param limit
     * @param offset
     * @return
     * @throws SQLException
     */
    @GET
    public Response getCustomers(@QueryParam("limit") Integer limit,
                                 @QueryParam("offset") Integer offset) throws SQLException {
        return Response.ok(customerService.findCustomers(limit, offset)).header("X-Total-Count", 0).build();
    }

    /**
     * Get a single customer
     * 
     * @param id
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") String id) throws SQLException {
        return Response.ok(customerService.findCustomerById(id)).build();
    }

    /**
     * Create a new customer
     * 
     * @param newCustomer
     * @return
     * @throws SQLException
     */
    @POST
    public Response createCustomer(Customer customer) throws SQLException {
        return Response.ok(customerService.createCustomer(customer)).build();
    }
    
    /**
     * Update an existing customer
     * 
     * @param id
     * @param customer
     * @return
     * @throws SQLException
     */
    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") String id, Customer customer) throws SQLException {
    	return Response.ok(customerService.updateCustomer(id, customer)).build();
    }
    
    /**
     * Delete an existing customer
     *  
     * @param id
     * @param customer
     * @return
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") String id) throws SQLException {
    	customerService.deleteCustomerById(id);
    	return Response.noContent().build();
    }
}
