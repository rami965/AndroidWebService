/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import beans.TripBean;
import beans.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author HP
 */
public class DBConnection {

    Connection con;

    public boolean connectToDB() {

        boolean isConnected = false;
        try {
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection(DBConfiguration.dbUrl, DBConfiguration.dbUser, DBConfiguration.dbPwd);
            isConnected = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            isConnected = false;
        }

        return isConnected;
    }

    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
        } catch (Exception ex) {
            con = null;
        }
    }

    public boolean executeSelect_SingleResult(String Query) {

        boolean result = false;
        boolean isConnected = connectToDB();
        if (isConnected) {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(Query);
                if (rs.next()) {
                    //user is authenticated
                    result = true;
                } else {
                    //user not authenticated
                    result = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("not connected");
        }

        closeConnection();
        return result;

    }

    public boolean executeUpdate(String query) {
        boolean insertStatus = false;
        boolean isConnected = connectToDB();
        if (isConnected) {
            try {
                Statement stmt = con.createStatement();
                int result = stmt.executeUpdate(query);
                System.out.println("result= " + result);
                if (result > 0) {
                    insertStatus = true;
                } else {
                    insertStatus = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                Logger.getLogger(ex.getMessage());
            }

        } else {
            System.out.println("not connected");
        }

        closeConnection();
        return insertStatus;

    }

    public List executeSelect_multipleResult(String Query) {
        ArrayList<TripBean> trips = new ArrayList<TripBean>();
        TripBean trip = new TripBean();
        boolean isConnected = connectToDB();
        if (isConnected) {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(Query);
                while (rs.next()) {
                    trip.setTripName(rs.getString(1));
                    trip.setTripStartPoint(rs.getString(2));
                    trip.setTripEndPoint(rs.getString(3));
                    trip.setDate(rs.getString(4));
                    trip.setTime(rs.getString(5));
                    trip.setNotes(rs.getString(6));
                    trip.setStatus(rs.getString(7));
                    trip.setPast(rs.getInt(8));
                    trip.setUserEmail(rs.getString(9));
                    
                    trips.add(trip);

                }
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        return trips;

    }
}