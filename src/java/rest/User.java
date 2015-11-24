package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("demouser")
@RolesAllowed("User")
public class User {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}";
  }
 
  @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addPerson(String json){
        JsonObject jsonP = new JsonParser().parse(json).getAsJsonObject();
        User p = JSONConverter.getPersonFromJson(jsonP, false);
        p = pf.addPerson(p);
        jsonP = JSONConverter.getJsonFromPerson(p);
        return jsonP.toString();
        
    }
  
}