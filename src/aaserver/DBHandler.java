/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aaserver;

import java.sql.Connection;

/**
 *
 * @author Chris
 */

//a class for handling database transactions
public abstract class DBHandler {
    
    private static Connection c;
    
    //these are just to simulate a bit of data in the database
    private static String[] clientIDs = { "ms001", 
                                 "ms002", 
                                 "sw001", 
                                 "sw002",
                                 "unit001",
                                 "unit002" };
    
    public static boolean verifyClientID(String ClientID) {
        for(String ID : clientIDs) {
            if(ID.equals(ClientID)) {
                return true;
            }
        }
        return false;
    }
    
}
