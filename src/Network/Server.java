/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import DTO.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author mthuan
 */
public class Server {

    ArrayList<User> listUser = new ArrayList<>();
    ServerSocket server = null;
    ArrayBlockingQueue<Runnable> workQueue = null;
    RejectedExecutionHandler handler = null;
    ThreadPoolExecutor executor = null;

    public Server() {
        try {
            System.out.println("Server is starting...");
            server = new ServerSocket(Config.PORT);
            workQueue = new ArrayBlockingQueue<>(100);
            handler = new ThreadPoolExecutor.CallerRunsPolicy();
            executor = new ThreadPoolExecutor(Config.corePoolSize, Config.maximumPoolSize, Config.keepAliveTime, Config.unit, workQueue, handler);

            while (server != null) {
                Socket s = server.accept();
                System.out.println("Client connected");
                executor.execute(new IOThread(s));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Doi khi tat ca cong viec ket thuc
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    private class conectTwoClient implements Runnable {

        public conectTwoClient() {
        }

        @Override
        public synchronized void run() {

            if (listUser == null) {
                listUser = new ArrayList<>();
                return;
            }
            try {
                for (User u1 : listUser) {
                    if (u1 != null && u1.getStatus() == 0) {

                        //Find second client
                        for (User u2 : listUser) {
                            if (u2 != null && u1 != u2 && u2.getStatus() == 0) {
                                if (checkReject(u1.getUsername(), u2.getRejectUser()) || checkReject(u2.getUsername(), u1.getRejectUser())) {
                                    continue;
                                }
                                u1.setStatus(1);
                                u2.setStatus(1);

                                u1.setConnectWith(u2.getSocket());
                                u2.setConnectWith(u1.getSocket());

                                sendInvite(u1, u2);
                                sendInvite(u2, u1);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private boolean checkReject(String username, List<String> rejectUser) {

            for (String str : rejectUser) {
                if (str.equals(username)) {
                    return true;
                }
            }
            return false;
        }

        private void sendInvite(User from, User to) {
            System.out.println("Send invite");
            System.out.println(from);
            System.out.println(to);
            System.out.println("\n\n");
            Socket socket = to.getSocket();
            String sender = from.getUsername();
            try {
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                JSONObject json = new JSONObject();
                json.put("cmd", "INVITE_CONNECT");
                json.put("data", sender);

                output.write(json.toString());
                output.newLine();
                output.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class IOThread implements Runnable {

        private Socket socket;
        private BufferedReader input;
        private BufferedWriter output;

        public IOThread(Socket s) {
            this.socket = s;
        }

        @Override
        public synchronized void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String inputStr = input.readLine();
                    if (inputStr == null) {
                        break;
                    }
                    System.out.println(inputStr);
                    JSONObject json = new JSONObject(inputStr);
                    String result = executeCmd(json);
                    if (result.equals("")) {
                        continue;
                    }

                    output.write(result);
                    output.newLine();
                    output.flush();
                }

                throw new Exception();
                /*==================================================*/
            } catch (Exception e) {
                try {
                    System.out.println("Client has disconnect");
                    output.close();
                    input.close();
                    //Set conect other user status to 0
                    for (User u : listUser) {
                        if (u != null && u.getSocket() == socket) {
                            for (User u2 : listUser) {
                                if (u2 != null && u != u2 && u.getConnectWith() == u2.getSocket()) {
                                    u2.setStatus(0);
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    //Remove current user from list
                    Iterator<User> iter = listUser.iterator();

                    while (iter.hasNext()) {
                        User u = iter.next();

                        if (u.getSocket() == socket) {
                            iter.remove();
                        }
                    }

                    if (listUser == null) {
                        listUser = new ArrayList<>();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        private String executeCmd(JSONObject json) {
            switch (json.getString("cmd")) {
                case "USER_LOGIN": {
                    JSONObject returnJson = new JSONObject();
                    returnJson.put("cmd", "USER_LOGIN");

                    //if username has already used
                    for (User u : listUser) {
                        if (u.getUsername().equalsIgnoreCase(json.getString("data"))) {
                            returnJson.put("data", "USER_ALREADY_USE");
                            return returnJson.toString();
                        }
                    }

                    //Add new user to list
                    listUser.add(new User(socket, json.getString("data"), 0, null, new ArrayList<>()));
                    returnJson.put("data", "SUCCESS");
                    executor.execute(new conectTwoClient());

                    return returnJson.toString();
                }
                case "REJECT_CONNECT": {
                    User currentUser = getUser(socket);
                    if (currentUser == null) {
                        return "";
                    }

                    //add connectClient connect to block list
                    User connectUser = getUser(currentUser.getConnectWith());
                    if (connectUser != null) {
                        currentUser.getRejectUser().add(connectUser.getUsername());
                        if (connectUser.getStatus() == 2) {
                            try {
                                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connectUser.getSocket().getOutputStream()));
                                JSONObject returnJson = new JSONObject();
                                returnJson.put("cmd", "CLIENT_REJECT_CONNECT");
                                output.write(returnJson.toString());
                                output.newLine();
                                output.flush();

                                connectUser.setStatus(0);
                                connectUser.setConnectWith(null);
                            } catch (Exception e) {
                            }
                        }
                    }
                    currentUser.setStatus(0);
                    currentUser.setConnectWith(null);

                    System.out.println("After Reject connect from to");
                    System.out.println(currentUser);
                    System.out.println("\n");

                    executor.execute(new conectTwoClient());
                    break;
                }
                case "ACCEPT_CONNECT": {
                    User currentUser = getUser(socket);
                    if (currentUser == null) {
                        return "";
                    }

                    //check connectClient 
                    User connectUser = getUser(currentUser.getConnectWith());
                    if (connectUser == null) {
                        currentUser.setStatus(0);
                        currentUser.setConnectWith(null);
                        JSONObject returnJson = new JSONObject();
                        returnJson.put("cmd", "CLIENT_NULL_CONNECT");

                        executor.execute(new conectTwoClient());
                        return returnJson.toString();
                    } else if (connectUser.getStatus() == 0) {
                        currentUser.setStatus(0);
                        currentUser.setConnectWith(null);
                        currentUser.getRejectUser().add(connectUser.getUsername());
                        JSONObject returnJson = new JSONObject();
                        returnJson.put("cmd", "CLIENT_REJECT_CONNECT");
                        executor.execute(new conectTwoClient());
                        return returnJson.toString();
                    } else if (connectUser.getStatus() == 1) {
                        currentUser.setStatus(2);
                        JSONObject returnJson = new JSONObject();
                        returnJson.put("cmd", "CLIENT_WAIT_CONNECT");
                        return returnJson.toString();
                    } else if (connectUser.getStatus() == 2) {
                        //Gui trang thai chap nhan qua ben nguoi dung thu 2
                        currentUser.setStatus(3);
                        connectUser.setStatus(3);
                        System.out.println("Connect two user");
                            System.out.println(currentUser);
                            System.out.println(connectUser + "\n\n");
                        JSONObject connectJson = new JSONObject();
                        connectJson.put("cmd", "CLIENT_CONNECTED");
                        try {
                            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connectUser.getSocket().getOutputStream()));
                            output.write(connectJson.toString());
                            output.newLine();
                            output.flush();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        /*=========================================*/
                        JSONObject returnJson = new JSONObject();
                        returnJson.put("cmd", "CLIENT_CONNECTED");
                        return returnJson.toString();
                    }

                    System.out.println("After Accept connect from to");
                    System.out.println(currentUser);
                    System.out.println("\n");
                    break;

                }
                case "CLOSE_CHAT": {
                    User currentUser = getUser(socket);
                    User connectUser = (currentUser == null) ? null : getUser(currentUser.getConnectWith());
                    if (connectUser == null) {
                        break;
                    }
                    //Set 2 user ve trang thai ket noi ban dau
                    currentUser.setStatus(0);
                    currentUser.setConnectWith(null);
                    currentUser.getRejectUser().add(connectUser.getUsername());

                    connectUser.setStatus(0);
                    connectUser.setConnectWith(null);
                    connectUser.getRejectUser().add(currentUser.getUsername());
                    //Gui tinh hieu thoat chat
                    try {
                        BufferedWriter ouput = new BufferedWriter(new OutputStreamWriter(connectUser.getSocket().getOutputStream()));
                        JSONObject returnJson = new JSONObject();
                        returnJson.put("cmd", "END_CHAT");
                        ouput.write(returnJson.toString());
                        ouput.newLine();
                        ouput.flush();
                    } catch (Exception e) {
                    }
                }
                case "CHAT_MESSAGE": {
                    System.out.println("Send mess from to");
                            
                            
                    User currentUser = getUser(socket);
                    if (currentUser != null) {
                        System.out.println(currentUser + "\n");
                        User connectUser = getUser(currentUser.getConnectWith());
                        if (connectUser != null) {
                            System.out.println(connectUser + "\n");
                           
                            JSONObject returnJson = new JSONObject();
                            returnJson.put("cmd", "ADD_NEW_MESS");
                            returnJson.put("mess", json.getString("data"));
                            returnJson.put("username", currentUser.getUsername());

                            try {
                                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connectUser.getSocket().getOutputStream()));
                                output.write(returnJson.toString());
                                output.newLine();
                                output.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
            return "";
        }

        private User getUser(Socket socket) {
            for (User u : listUser) {
                if (u.getSocket() == socket) {
                    return u;
                }
            }
            return null;
        }
    }

}
