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
public class AAServerThread extends Thread {
    private Socket socket = null;
    
    public AAServerThread (Socket socket) {
        super("AAServerThread");
        this.socket = socket;
    }
    
    public void run() {
        //try-with-resources, printwriter wrapped around outputstream, buffered reader wrapped around input stream
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            //create instance of client handler class
            ClientHandler clientHandler = new ClientHandler();
            //send it a null value first because server will communicate first
            outputLine = clientHandler.processData(null);
            out.println(outputLine);
            //while there are lines of input, send them to client handler and it will return the relevant output
            while ((inputLine = in.readLine()) != null) {
                outputLine = clientHandler.processData(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
