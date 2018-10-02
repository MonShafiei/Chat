package ir.mohsen.socket.main;

import ir.mohsen.socket.manager.server.SocketServerRecivedText;

/**
 * Created by Mohsen on 18/09/12.
 */
public class Main {

    public static void main(String[] args) {
        SocketServerRecivedText server =new SocketServerRecivedText();

        server.runServer();
    }

}
