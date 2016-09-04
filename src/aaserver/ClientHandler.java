/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aaserver;

/**
 *
 * @author Chris
 */

// class with the logic for handling different kinds of clients
public class ClientHandler {
    private static final int WAITING = 0;
    private static final int IDREQUESTED = 1;
    private static final int MOTIONSENSOR = 2;
    private static final int SMARTWATCH = 3;
    private static final int ANDROIDUNIT = 4;

    private int state = WAITING;
    
    //initial processing method to identify client type
    public String processData(String theInput) {
        String theOutput = null;
        if (state == WAITING) {
            theOutput = "Hello. Please state ClientID";
            state = IDREQUESTED;
        }
        else if (state == IDREQUESTED) {
            if (DBHandler.verifyClientID(theInput)) {
                if(theInput.startsWith("ms")) {
                    //client is motion sensor
                    state = MOTIONSENSOR;
                    theOutput = handleMotionSensor(theInput);
                } else if(theInput.startsWith("sw")) {
                    //client is smartwatch
                    state = SMARTWATCH;
                    theOutput = handleSmartWatch(theInput);
                } else {
                    //client must be Android unit
                    state = ANDROIDUNIT;
                    theOutput = handleAndroidUnit(theInput);
                }
            } else {
                //client ID not in database
                theOutput = "Bye.";
            }
        }
        return theOutput;
    }
    
    public String handleSmartWatch(String theInput) {
        //do some logic and/or call DBHandler and then....
        return "Some output";
    }
    
    public String handleMotionSensor(String theInput) {
        //do some logic and/or call DBHandler and then....
        return "Some output";
    }
    
    public String handleAndroidUnit(String theInput) {
        //do some logic and/or call DB Handler and then....
        return "Some output";
    }
    
}
