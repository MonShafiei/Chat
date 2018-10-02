package ir.mohsen.socket.main;

import ir.mohsen.socket.manager.server.MainServer;

/**
 * Created by Mohsen on 18/09/12.
 */
public class Main {

    public static void main(String[] args) {
        MainServer server =new MainServer(8585);

        server.runServer();
    }

}
