package server;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer {
    public static void main(String argv[]) throws Exception {
        System.out.println(" File Transfer Server is Running ");
        ServerSocket mysocket = new ServerSocket(5550);

        while (true) {
            Socket connectionSocket = mysocket.accept();

            /* Get the file text stream by the client */
            InputStream inputStream = connectionSocket.getInputStream();

            /* Create new file */
            OutputStream fileOutputStream = new FileOutputStream("message-received.txt");

            /* Copy the message received in the new file */
            byte[] bytes = new byte[16 * 1024];
            int count;
            while ((count = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, count);
            }

            connectionSocket.close();
            fileOutputStream.close();
            inputStream.close();
        }
    }
}
