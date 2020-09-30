package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    protected Socket socket = null;

    public Server(Socket s) {
        this.socket = s;
    }

    public static void main(String argv[]) throws Exception
    {
        System.out.println(" Server is Running " );
        ServerSocket mysocket = new ServerSocket(5550);

        while(true) {
            Socket socket = mysocket.accept();
            Server server = new Server(socket);
            Thread thread =new Thread(server);
            thread.start();
        }
    }

    @Override
    public void run(){
        try{
            BufferedReader reader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write("HI\n");
            writer.write("*** Welcome to the Calculation Server (Addition Only) ***\r\n");

            writer.write("*** Please type in the first number and press Enter : \n");
            writer.flush();
            String data1 = reader.readLine().trim();

            writer.write("*** Please type in the second number and press Enter :\n");
            writer.flush();

            String data2 = reader.readLine().trim();

            int num1 = Integer.parseInt(data1);
            int num2 = Integer.parseInt(data2);

            int result = num1+num2;
            System.out.println("Addition operation done " );

            writer.write("\r\n=== Result is : "+result);
            writer.flush();

            socket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
