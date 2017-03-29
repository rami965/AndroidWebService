/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import beans.Users;

/**
 *
 * @author HP
 */
public class UsersDBManager {
    
    public static String checkUser(String userEmail , String password)
    {
        String query="select * from users where lower(email)=lower('";
        query+=userEmail+"')and trim(password)=lower('";
        query+=password+"')";
        
        System.out.println("login statment "+query);
        return query;
    
    }
    
     public static String insertUser(Users user)
    {
        //insert user into DB
        String query="insert into users values('";
        query+=user.getEmail()+"','"+user.getPassword()+"','"+user.getUserName()+"')";
        
        //Print query to make sure
        System.out.println("login statment "+query);
        return query;
    
    }
    
}
