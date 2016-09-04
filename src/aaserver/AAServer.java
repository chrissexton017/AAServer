/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aaserver;

import java.io.*;
import java.nio.file.*;
import java.net.*;

/**
 *
 * @author Chris
 */
public class AAServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
    /*if (args.length != 1) {
        System.err.println("Usage: java AAServer <port number>");
        System.exit(1);
    }*/

        //int portNumber = Integer.parseInt(args[0]);
        int portNumber = 8000;
        boolean listening = true;
        //try-with-resources new server socket, start a new server thread
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
	            new AAServerThread(serverSocket.accept()).start();
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
    
}
