/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Json.Utitlity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import myPackage.DBConnection;
import myPackage.UsersDBManager;

/**
 *
 * @author HP
 */
@Path("/login")
public class LogIn {
    
    DBConnection dbConnection;
    @GET
    @Path("/doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public String logIn(@QueryParam("email") String userEmail, @QueryParam("password") String password) {
        String response = "";
        String query = UsersDBManager.checkUser(userEmail, password);
        dbConnection= new DBConnection();
        boolean isUserAvailable = dbConnection.executeSelect_SingleResult(query);
        response = Utitlity.constructJson("login",isUserAvailable);
        dbConnection=null;
        return response;
    }
    
}
