package com.globallogic.restjsonapp.resources;

import com.globallogic.restjsonapp.dto.Customer;
import com.globallogic.restjsonapp.services.CustomersService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/customers")
public class CustomersResorce {

    private CustomersService customersService = new CustomersService();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Set<Customer> getAllCustomers(){
        return customersService.selectAll();
    }

    @GET
    @Path("/{customerId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCustomerById(@PathParam("customerId") long id){
        Customer customer = customersService.selectById(id);
        if (customer == null)
            return Response.status(404).build();
        return Response.ok(customer).build();
    }

    @DELETE
    @Path("/{customerId}")
    public void removeProductById(@PathParam("customerId") long id){
        customersService.deleteById(id);
    }

}
