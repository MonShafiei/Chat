package ir.mohsen.socket.manager.server;

import ir.mohsen.socket.util.Print;
import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by Mohsen on 18/09/12.
 */
public class ThreadForManageClientInServer implements Runnable{
    private Socket socket;
    private MainServer server;


    public ThreadForManageClientInServer(MainServer server, Socket socket) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {

        System.out.println("One Thread for client created");
        Scanner scanner = new Scanner(System.in);
        Scanner in=null;
        try {
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Formatter out = null;
        String input="";
        try {
            out = new Formatter(socket.getOutputStream());
            out.format("Server : " + socket.getInetAddress() + " Please enter your name ?" + "%n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        input= in.nextLine();
        server.addClientToHashMap(input,this);
            Thread recived = new Thread(new ThreadReadTextData(server,socket));
            recived.start();



            while (input.toLowerCase().equals("exit")){
                input= scanner.nextLine();
                Print.print("Shod");
                sendtext(input);
            }

    }

    public void sendtext(String inputString){
        Formatter out = null;
        try {
            out = new Formatter(socket.getOutputStream());
            out.format("Server : " + socket.getInetAddress() + " " + inputString + "%n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
