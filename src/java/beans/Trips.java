/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Json.Utitlity;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import myPackage.DBConnection;
import myPackage.TripsDBManager;
import myPackage.UsersDBManager;

/**
 *
 * @author HP
 */
//http://localhost:8084/AndroidWebService/restServlet/trips/getTrips?email=dina
@Path("/trips")
public class Trips {
    
    DBConnection dbConnection;
    @GET
    @Path("/addTrip")
    @Produces(MediaType.APPLICATION_JSON)
    public String addTrip(@QueryParam("email") String email,@QueryParam("name") String tripName, @QueryParam("startPoint") String startPoint ,@QueryParam("endPoint") String endPoint,
            @QueryParam("date") String date,@QueryParam("time") String time,@QueryParam("notes") String notes,@QueryParam("status") String status,@QueryParam("past") int past) {
        String response = "";
        TripBean trip = new TripBean();
        trip.setDate(date);
        trip.setNotes(notes);
        trip.setPast(past);
        trip.setStatus(status);
        trip.setTime(time);
        trip.setTripEndPoint(endPoint);
        trip.setTripName(tripName);
        trip.setTripStartPoint(startPoint);
        trip.setUserEmail(email);
       
        String query = TripsDBManager.addTrip(trip);
        dbConnection= new DBConnection();
       
        boolean insertedStatus = dbConnection.executeUpdate(query);
       
        response = Utitlity.constructJson("register", insertedStatus);
        
        dbConnection=null;
        return response;
    }
    
    
     @GET
    @Path("/getTrips")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTrips(@QueryParam("email") String email) {
        String response = "";
       
        String query = TripsDBManager.getTrips(email);
        dbConnection= new DBConnection();
       
        ArrayList<TripBean> trips= (ArrayList<TripBean>) dbConnection.executeSelect_multipleResult(query);
       
        response = Utitlity.constructJsonwithList("MY_TRIPS", trips);
        
        dbConnection=null;
        return response;
    }
}
