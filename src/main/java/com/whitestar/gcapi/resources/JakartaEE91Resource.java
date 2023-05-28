package com.whitestar.gcapi.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("jakartaee9")
public class JakartaEE91Resource {
    
    @GET
    @Path("ping")
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
