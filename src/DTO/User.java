/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.net.Socket;
import java.util.List;

/**
 *
 * @author mthuan
 */
public class User {
    private Socket socket;
    private String username;
    private int status; //0 waiting; 1 wait response; 2 wait connect;3 connected
    private Socket connectWith;
    List<String> rejectUser;

    public User(Socket socket, String username, int status, Socket connectWith, List<String> rejectUser) {
        this.socket = socket;
        this.username = username;
        this.status = status;
        this.connectWith = connectWith;
        this.rejectUser = rejectUser;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Socket getConnectWith() {
        return connectWith;
    }

    public void setConnectWith(Socket connectWith) {
        this.connectWith = connectWith;
    }

    public List<String> getRejectUser() {
        return rejectUser;
    }

    public void setRejectUser(List<String> rejectUser) {
        this.rejectUser = rejectUser;
    }

    @Override
    public String toString() {
        return "User{" + "socket=" + socket + ", username=" + username + ", status=" + status + ", connectWith=" + connectWith + ", rejectUser=" + rejectUser + '}';
    }
    
            
}
