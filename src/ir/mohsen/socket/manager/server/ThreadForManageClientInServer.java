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
public class ThreadForManageClientInServer implements Runnable {
    private Socket socket;
    private MainServer server;
    Scanner in = null;
    Formatter out = null;
    Thread takeTextFromClient;
    private boolean destroyThisObject=false;

    public ThreadForManageClientInServer(MainServer server, Socket socket) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {

        System.out.println("One Thread for client created");
        Scanner scanner = new Scanner(System.in);
        try {
            in = new Scanner(socket.getInputStream());
            out = new Formatter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] input = null;
        boolean login = false;
        while (!login) {
            if (in.hasNext()) {
                input = in.nextLine().split(Resource.SPLIT);
                if (input != null && input.length >= 2) {
                    if (input[2].equalsIgnoreCase(Resource.PASSWORD)) {
                        server.addClientToHashMap(input[1], this);
                        takeTextFromClient = new Thread(new ThreadReadTextData(server, socket));
                        takeTextFromClient.start();
                        login = true;
                    }
                }
            }
        }
        String inputFromCommandLine=null;
        while (!destroyThisObject) {
            inputFromCommandLine = scanner.nextLine();
            Print.print("From CommandLine");
            sendtext(inputFromCommandLine);
        }

        takeTextFromClient.destroy();
        in.close();
        out.close();

    }

    private void outputByFormatter(String inputString) {
        out.format(inputString + "%n");
        out.flush();
    }

    public void sendtext(String inputString) {
        Formatter out = null;
        try {
            out = new Formatter(socket.getOutputStream());
            out.format("Server : " + socket.getInetAddress() + " " + inputString + "%n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDestroyThisObject() {
        return destroyThisObject;
    }

    public void setDestroyThisObject(boolean destroyThisObject) {
        this.destroyThisObject = destroyThisObject;
    }
}
