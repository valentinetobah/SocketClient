/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testsocketclient;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author valentinetobah
 */
//Making this Class a Managed bean using @ManagedBean annotaion and 
//naming it socket. 
@ManagedBean(name = "socket")
@RequestScoped
public class SocketManagedBean implements Serializable {

    //Injecting the SocketClient Ejb class
    @EJB
    private SocketClient socketClient;

    private String ipAddress;
    private Integer port;
    private String responseMessage = "Waiting server response...";
    private  final String IPADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    /**
     *
     * @return
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     *
     * @param ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     *
     * @return
     */
    public Integer getPort() {
        return port;
    }

    /**
     *
     * @param port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     *
     * @return
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     *
     * @param responseMessage
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public  String getIPADDRESS_PATTERN() {
        return IPADDRESS_PATTERN;
    }

    public String displaySocketResponse() {
        responseMessage = socketClient.sockRequest(ipAddress, port);
        System.out.println(responseMessage);
        return null;
    }
}
