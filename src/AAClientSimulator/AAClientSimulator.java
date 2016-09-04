/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AAClientSimulator;

import java.io.*;
import java.net.*;

/**
 *
 * @author Chris
 */

//Just a class for testing so we can simulate inputs to server
public class AAClientSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*if (args.length != 2) {
            System.err.println(
                "Usage: java AAClientSimulator <host name> <port number>");
            System.exit(1);
        }*/

        /*String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);*/
        String hostName = "127.0.0.1";
        int portNumber = 8000;

        try (
            Socket aaSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(aaSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(aaSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;
                
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
    
}
