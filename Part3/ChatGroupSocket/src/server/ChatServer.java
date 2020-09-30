package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer implements Runnable {
    public static Vector clients = new Vector();

    protected Socket socket = null;

    public ChatServer(Socket s) {
        this.socket = s;
    }

    public static void main(String argv[]) throws IOException {
        System.out.println("BipBopBipBop: Waiting to connected server..." );

        ServerSocket mysocket = new ServerSocket(5558);

        while(true) {
            Socket socket = mysocket.accept();
            System.out.println("Server is Running ! \n");
            ChatServer server = new ChatServer(socket);
            Thread thread = new Thread(server);
            thread.start();
        }

    }

    @Override
    public void run(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clients.add(writer);

            writer.write("*** Welcome to amazing local chat ***\r\n");
            writer.flush();
            writer.write("*** Pour discuter avec une nouvelle personne, génerer un autre client. Peut-être que vous tomberez sur Céline Dion ;) ***\r\n");
            writer.flush();

            while (true) {
                String message = reader.readLine().trim();
                System.out.println("Message received by : " + message);

                for (int i=0; i<clients.size() ; i++) {
                    try {
                        BufferedWriter bw = (BufferedWriter)clients.get(i);
                        bw.write(message);
                        bw.write("\r\n");
                        bw.flush();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
