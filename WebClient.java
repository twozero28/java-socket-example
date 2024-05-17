import java.io.*;
import java.net.Socket;

public class WebClient {
    public static void main(String[] args) {
        File recvFile = new File("client_recv.txt");
        File sendFile = new File("client_send.txt");
        try (
            Socket socket = new Socket("127.0.0.1", 8001);


            FileInputStream fis = new FileInputStream(sendFile);
            FileOutputStream fos = new FileOutputStream(recvFile);
        ) {
            int ch;
            // send the content of client_send.txt to server
            OutputStream output = socket.getOutputStream();
            while ((ch = fis.read()) != -1) {
                output.write(ch);
            }
            // "Transmit 0 to indicate termination."
            output.write(0);
            // print the return from the server to client_recv.txt
            InputStream input = socket.getInputStream();
            while ((ch = input.read()) != -1) {
                fos.write(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
