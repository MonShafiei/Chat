package ir.mohsen.socket.manager.server;

import ir.mohsen.socket.util.Print;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Mohsen on 18/09/12.
 */
public class SocketServerRecivedText {
    static ServerSocket server;
    static Map<String, ThreadClientManagerInServer> map = new HashMap<>();

    public void runServer() {
        Socket socket;
        Print.print("*** Server Started ***");

        try {
            server = new ServerSocket(8585);

            do {
                socket = server.accept();
                Thread thread = new Thread(new ThreadClientManagerInServer(this, socket));
                thread.start();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addClientToHashMap(String name, ThreadClientManagerInServer clientManaggerInServer) {
        map.put(name, clientManaggerInServer);
    }

    public ThreadClientManagerInServer getClientFromHashMap(String name) {
        return map.get(name);
    }

    public Set<String> getListClientsFromHashMap() {

        return map.keySet();
    }

}
