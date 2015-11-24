/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.UserFacade;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author user
 */

@Path("register")
public class RegisterApi {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    UserFacade uf = new UserFacade();
    
   @POST 
  @Consumes("application/json")
    @Produces("application/json") 
  public Response registerUser(String json){
     entity.User u = gson.fromJson(json, entity.User.class);
     u.addRole("User");
     uf.addUser(u);
     return Response.status(Response.Status.OK).build();
  }  
    
    
    
}
