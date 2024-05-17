import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) {
        File recvFile = new File("server_recv.txt");
        File sendFile = new File("server_send.txt");
        try (
            ServerSocket server = new ServerSocket(8001);

            FileOutputStream fos = new FileOutputStream(recvFile);
            FileInputStream fis = new FileInputStream(sendFile);
        ) {

            System.out.println("Waiting for client connection...");
            Socket socket = server.accept();
            System.out.println("Client connected");

            int ch;
            // print the content received from the client to server_recv.txt
            InputStream input = socket.getInputStream();
            // the client send "0" as a close mark
            while ((ch = input.read()) != 0) {
                fos.write(ch);
            }

            // send the content of sever_send.txt to the client
            OutputStream output = socket.getOutputStream();
            while ((ch = fis.read()) != -1) {
                output.write(ch);
            }

            socket.close();
            System.out.println("The communication has ended");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
