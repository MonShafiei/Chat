package ir.mohsen.socket.manager.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by Mohsen on 18/09/12.
 */
public class SendTextData {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8585);
            OutputStream outputStream = socket.getOutputStream();
            Scanner in = new Scanner(socket.getInputStream());
            Formatter formatter = new Formatter(outputStream);
            Scanner scanner = new Scanner(System.in);
            String str = "";
            String next = "";
            while (!str.equals("exit")) {
                str = scanner.nextLine();
                formatter.format(str + "%n");
                formatter.flush();
                //next = in.nextLine();
                //System.out.println(next);
            }
            socket.close();
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
