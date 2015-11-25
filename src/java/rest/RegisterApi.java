/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.PasswordHash;

/**
 *
 * @author user
 */
@Path("register")
public class RegisterApi {

    UserFacade uf = new UserFacade();

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerUser(String json) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JsonObject jsonObj = new JsonParser().parse(json).getAsJsonObject();
        entity.User u = new entity.User();
        u.setUserName(jsonObj.get("userName").getAsString());
        String password = jsonObj.get("password").getAsString();
        String hash = PasswordHash.createHash(password);
        u.setPasswordHash(hash);
        u.addRole("User");
        System.out.println("occured");
        uf.addUser(u);
        JsonObject returnObj = new JsonObject();
        returnObj.addProperty("id", u.getId());
        returnObj.addProperty("userName", u.getUserName());
        return Response.status(Response.Status.OK).entity(returnObj.toString()).type(MediaType.APPLICATION_JSON).build();
    }

//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response registerUser(String json) {
//        entity.User u = gson.fromJson(json, entity.User.class);
//        u.addRole("User");
//        uf.addUser(u);
//        System.out.println("occured");
//        return Response.status(Response.Status.OK).build();
//    }

}
