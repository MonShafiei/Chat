package ir.mohsen.socket.manager.client;

        import ir.mohsen.socket.manager.server.ThreadReadTextData;

        import java.io.IOException;
        import java.io.OutputStream;
        import java.net.Socket;
        import java.util.Formatter;
        import java.util.Scanner;

/**
 * Created by Mohsen on 18/09/12.
 */
public class ClientSendAndReadableByThread {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8585);
            OutputStream outputStream = socket.getOutputStream();
            Formatter formatter = new Formatter(outputStream);
            Scanner scanner = new Scanner(System.in);
            String str = "";
            String next = "";
            Thread recived = new Thread(new ThreadReadTextData(null,socket));
            recived.start();
            while (!str.equals("exit")) {
                str = scanner.nextLine();
                formatter.format(str + "%n");
                formatter.flush();
            }
            socket.close();
            System.out.println("finished ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
