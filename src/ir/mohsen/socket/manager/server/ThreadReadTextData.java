package ir.mohsen.socket.manager.server;

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
    //public final String split = "::";
    //String next;

    public ThreadReadTextData(MainServer server, Socket socket) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("Ye method dorost shod");
        //String[] stringArray = new String[2];
        String input = "";
        try {
            Scanner in = new Scanner(socket.getInputStream());
            Formatter out = null;
            while (true) {
                if (in.hasNext()) {
                    input = in.nextLine(); //     this is test git
                    if (input.toLowerCase().equals("send")) {
                        try {
                            out = new Formatter(socket.getOutputStream());
                            out.format("Server : " + socket.getInetAddress() + " Please enter who is name ?" + "%n");
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        input = in.nextLine();
                        threadForManageClientInServer = server.getClientFromHashMap(input);
                        if(threadForManageClientInServer != null){
                            out.format("Server : " + socket.getInetAddress() + " Please enter your text ?" + "%n");
                            out.flush();
                            input = in.nextLine();
                            threadForManageClientInServer.sendtext(input);
                        }
                    }
                    System.out.println(input);
                    //stringArray = input.split(split);
                    //Socket newSocket = new Socket("stringArray[0]", 8585);
                    //OutputStream outputStream = newSocket.getOutputStream();
                    //Formatter out = new Formatter(outputStream);
                    //out.format("Resend from : " + socket.getInetAddress() + " " + stringArray[1] + "%n");
                    //out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
