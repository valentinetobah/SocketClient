/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testsocketclient;

/**
 *
 * @author valentinetobah
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;


@Stateless
public class SocketClient {
    
    
    /**Creating a req method that takes two parameters IpAddress and port 
     * connects to the server by means of a socket and 
     * returns the server response as a string
    * /

    /**
     *
     * @param ipAddress
     * @param port
     * @return
     */
    public String sockRequest(String ipAddress, int port){
        
        String formattedXML = "";
        
        try {
            
            System.out.println("Waiting for server connection...");
            
            //Creating a socket connection.
            Socket socket = new Socket(ipAddress.trim(), port);
            System.out.println("Connected to server");
            
            //Assigning an xml to a String
            String xmlFile = "<request><EventType>Authentication</EventType>"
                    + "<event><UserPin>12345</UserPin><DeviceId>12345</DeviceId>"
                    + "<DeviceSer>ABCDE</DeviceSer><DeviceVer>ABCDE</DeviceVer>"
                    + "<TransType>Users</TransType></event></request>";

            //Sending the String to the  server.
            OutputStreamWriter outputStreamReader = 
                    new OutputStreamWriter(socket.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputStreamReader);
            printWriter.println(xmlFile);
            
            //use the flush method of OutputStreamWriter to forcefully 
            //override message size limitation.
            outputStreamReader.flush();
            System.out.println("Sent message to server");
            
            System.out.println("Waiting for server response");
            
            //Fetching the response from the server.
            InputStreamReader inputStreamReader = 
                    new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader =
                    new BufferedReader(inputStreamReader);
            
            //Use String replaceAll method to make String response pretty.
            formattedXML = bufferedReader.readLine().replaceAll("><", ">\n<");
            System.out.println("Server Response:\n" + formattedXML);
            
        } catch (IOException ex) {
            formattedXML = ex.toString();
            Logger.getLogger(SocketClient.class.getName())
                    .log(Level.SEVERE, null, ex);
            
        }
       return formattedXML;
    }
 }
    
   
