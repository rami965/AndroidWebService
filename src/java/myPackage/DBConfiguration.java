/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

/**
 *
 * @author HP
 */
public class DBConfiguration {
    
    public static String dbClass = "oracle.jdbc.OracleDriver";
    //private static String dbName= "users";
    public static String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    public static String dbUser = "tripPlanner";
    public static String dbPwd = "iti";
    
}
