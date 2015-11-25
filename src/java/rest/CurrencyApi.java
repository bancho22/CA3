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
import facades.CurrencyFacade;
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
@Path("currency")
@RolesAllowed("User")
public class CurrencyApi {
    
    private CurrencyFacade cf;
    private Gson gson;
    
    public CurrencyApi(){
        cf = new CurrencyFacade();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dailyrates")
    public Response getTodaysRates(){
        Calendar today = Calendar.getInstance(TimeZone.getTimeZone("Europe/Copenhagen"));
        List<CurrencyRate> rates = cf.getCurrencyRatesByDate(today.getTime());
        JsonArray ratesJson = new JsonArray();
        for (CurrencyRate rate : rates) {
            ratesJson.add(new JsonParser().parse(gson.toJson(rate)));
        }
        return Response.status(Response.Status.OK).entity(ratesJson.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
