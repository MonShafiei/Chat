package ir.mohsen.socket.manager.server;

import ir.mohsen.socket.util.Print;
import ir.mohsen.socket.util.Resource;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by Mohsen on 18/09/12.
 */
public class ThreadReadTextData implements Runnable {
    Socket socket;
    ThreadForManageClientInServer threadForManageClientInServer;
    MainServer server;
    Scanner in;
    Formatter out = null;
    //public final String split = "::";
    //String next;

    public ThreadReadTextData(MainServer server, Socket socket) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("One SubThread Created");
        String input = "";
        try {
            in = new Scanner(socket.getInputStream());
            out = new Formatter(socket.getOutputStream());

            while (true) {
                if (in.hasNext()) {
                    input = in.nextLine();
                    if (input.equalsIgnoreCase("send")) {

                        outputByFormatter("Server : " + socket.getInetAddress() + " Please enter who is name ?");

                        input = in.nextLine();
                        threadForManageClientInServer = server.getClientFromHashMap(input);
                        if (threadForManageClientInServer != null) {
                            out.format("Server : " + socket.getInetAddress() + " Please enter your text ?" + "%n");
                            out.flush();
                            input = in.nextLine();
                            threadForManageClientInServer.sendtext(input);
                        }
                    } else if (input.equalsIgnoreCase(Resource.LIST)) {

                    } else if (input.equalsIgnoreCase(Resource.CONNECT)) {

                    } else if (input.equalsIgnoreCase(Resource.EXIT)) {

                    } else if (input.equalsIgnoreCase(Resource.CLOSE)) {

                    }
                    Print.print(input);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void outputByFormatter(String inputString) {
        out.format(inputString + "%n");
        out.flush();
    }
}
