package com.globallogic.restjsonapp.resources;

import com.globallogic.restjsonapp.schema_constructor.JsonSchema;
import com.globallogic.restjsonapp.schema_constructor.JsonSchemaMock;
import com.globallogic.restjsonapp.services.SchemasService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/schemas")
public class SchemasResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<JsonSchemaMock> getAllSchemas(){
        return new SchemasService().getAllSchemasMocks("com.globallogic.restjsonapp.dto");
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getSchema(@PathParam("id") String id){
        JsonSchema schema = new SchemasService().getSchemaById(id);
        if (schema == null)
            return Response.status(404).build();
        return Response.ok(schema.getJsonSchema()).build();
    }

}
