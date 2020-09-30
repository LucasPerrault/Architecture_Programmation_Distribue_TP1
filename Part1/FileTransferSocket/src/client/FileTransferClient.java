package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileTransferClient {
    public final static String FILE_TO_SEND = "src/client/message.txt";

    public static void main(String argv[]) {
        try{
            Socket socketClient= new Socket("localhost",5550);
            System.out.println("Client: "+"Connection Established");

            OutputStream outputStream = socketClient.getOutputStream();

            /* Prepare the file to send */
            File file = new File(FILE_TO_SEND);
            InputStream inputStream = new FileInputStream(file);

            /* Send the message with output streamer */
            byte[] bytes = new byte[(int)file.length()];
            int count;
            while ((count = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, count);
            }

            /* Close all connections */
            outputStream.close();
            inputStream.close();
            socketClient.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
