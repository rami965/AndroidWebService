/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Json.Utitlity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import myPackage.DBConnection;
import myPackage.UsersDBManager;
import oracle.jdbc.proxy.annotation.Post;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
//Path: http://localhost/<appln-folder-name>/register
//http://localhost:8084/AndroidWebService/restServlet/register/doRegister?email=dina&password=1
@Path("/register")
public class Register {

    DBConnection dbConnection;

//    @GET
//    @Path("/doRegister")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String register(@QueryParam("email") String userEmail, @QueryParam("password") String password) {
//        String response = "";
//        Users user = new Users();
//        user.setUserName(userEmail);
//        user.setPassword(password);
//        String query = UsersDBManager.insertUser(user);
//        dbConnection= new DBConnection();
//       
//        boolean insertedStatus = dbConnection.executeUpdate(query);
//       
//        response = Utitlity.constructJson("register", insertedStatus);
//        
//        dbConnection=null;
//        return response;
//    
    @GET
    @Path("/doRegister")

//http://localhost:8084/AndroidWebService/restServlet/register/doRegister?user=       
    @Produces(MediaType.APPLICATION_JSON)
    public String register(@QueryParam("user") String userString) {
        String response = "";
        try {

            JSONObject jObject = new JSONObject(userString);
            Users user = new Users();
            user.setUserName(jObject.optString("name"));
            user.setPassword(jObject.optString("password"));
            user.setEmail(jObject.optString("email"));
            String query = UsersDBManager.insertUser(user);
            dbConnection = new DBConnection();

            boolean insertedStatus = dbConnection.executeUpdate(query);

            response = Utitlity.constructJson("register", insertedStatus);

            dbConnection = null;
            return response;
        } catch (JSONException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
}
