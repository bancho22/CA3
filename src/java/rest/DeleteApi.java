/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import facades.UserFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Bancho
 */
@Path("admin")
@RolesAllowed("Admin")
public class DeleteApi {
    
    private UserFacade uf;
    private Gson gson;
    
    public DeleteApi(){
        uf = new UserFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @DELETE
    @Produces("text/plain") 
    @Path("users/{id}")
    public Response deleteUsers(@PathParam("id") String id){
        uf.deleteUserByID(id);
        
        return Response.status(Response.Status.OK).build();
    }
}
