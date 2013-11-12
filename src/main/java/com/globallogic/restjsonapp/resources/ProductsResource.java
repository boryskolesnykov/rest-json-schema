package com.globallogic.restjsonapp.resources;

import com.globallogic.restjsonapp.dto.Product;
import com.globallogic.restjsonapp.services.ProductsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/products")
public class ProductsResource {

    private ProductsService productsproductsService = new ProductsService();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Set<Product> getAllProducts(){
        return productsproductsService.selectAll();
    }

    @GET
    @Path("/{productId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductById(@PathParam("productId") long id){
        Product product = productsproductsService.selectById(id);
        if (product == null)
            return Response.status(404).build();
        return Response.ok(product).build();
    }

    @DELETE
    @Path("/{productId}")
    public void removeProductById(@PathParam("productId") long id){
        productsproductsService.deleteById(id);
    }

}
