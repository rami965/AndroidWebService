/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import beans.TripBean;

/**
 *
 * @author HP
 */
public class TripsDBManager {
    
    public static String addTrip(TripBean trip)
    {
        String query ="insert into trips values ('"+trip.getTripName()+"','"+trip.getTripStartPoint()+"','"+trip.getTripEndPoint()+"','"+trip.getDate()
                +"','"+trip.getTime()+"','"+trip.getNotes()+"','"+trip.getStatus()+"','"+trip.getPast()+"','"+trip.getUserEmail()+"')";
        
        System.out.println("add trip = "+query);
        return query;
    
    }
    
    public static String getTrips(String userEmail)
    {
        String query="select * from trips where lower(useremail)=lower('"+userEmail+"')";
        
        System.out.println("get trips = "+query);
        return query;
    
    }
    
}
