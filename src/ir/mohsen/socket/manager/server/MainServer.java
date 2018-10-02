package ir.mohsen.socket.manager.server;

import ir.mohsen.socket.util.Print;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Mohsen on 18/09/12.
 */
public class MainServer {
    private ServerSocket server;
    static Map<String, ThreadForManageClientInServer> map = new HashMap<>();
    private Integer portNumber;

    public MainServer() {

    }

    public MainServer(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public void runServer() {
        Socket socket;
        Print.print("*** Server Started ***");

        try {
            server = new ServerSocket(portNumber);

            do {
                socket = server.accept();
                Thread thread = new Thread(new ThreadForManageClientInServer(this, socket));
                thread.start();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addClientToHashMap(String name, ThreadForManageClientInServer clientManaggerInServer) {
        map.put(name, clientManaggerInServer);
    }

    public ThreadForManageClientInServer getClientFromHashMap(String name) {
        return map.get(name);
    }

    public Set<String> getListClientsFromHashMap() {

        return map.keySet();
    }

}
