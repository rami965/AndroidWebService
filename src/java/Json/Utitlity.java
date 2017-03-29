/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import beans.TripBean;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author HP
 */
public class Utitlity {
    
    
    
    public static boolean notNull(String text)
    {
        return text!=null && text.trim().length()>=0 ? true:false;
    }
    
   
    public static String constructJson(String tag,boolean status)
    {   
        JSONObject obj=new JSONObject();
        obj.put("tag",tag);
        obj.put("status",new Boolean(status));
        
        return obj.toString();
    }

    public static String constructJsonwithList(String my_trips, ArrayList<TripBean> trips) {
        
        JSONObject obj=new JSONObject();
        JSONArray  ar=new JSONArray();
        JSONObject formDetailsJson;
        for(TripBean trip:trips)
        {
              formDetailsJson = new JSONObject();
              formDetailsJson.put("Trip_name",trip.getTripName());
              formDetailsJson.put("Trip_startPoint",trip.getTripStartPoint());
              ar.add(formDetailsJson);
        }
        
        obj.put("trips",ar);
        
        return obj.toString();
        
      
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
