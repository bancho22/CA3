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
import entity.CurrencyRate;
import facades.UserFacade;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Bancho
 */
@Path("admin")
@RolesAllowed("Admin")
public class GetAllUsersApi {
    
    private UserFacade uf;
    private Gson gson;
    
    public GetAllUsersApi(){
        uf = new UserFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("users")
    public Response getTodaysUsers(){
        
        List<entity.User> users = uf.getAllUsers();
        JsonArray usersJson = new JsonArray();
        for (entity.User u : users) {
            usersJson.add(new JsonParser().parse(gson.toJson(u)));
        }
        return Response.status(Response.Status.OK).entity(usersJson.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
