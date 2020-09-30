package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

public class ChatClient implements Runnable {

    public JTextField tx;
    public JTextArea ta;
    public String login = "Imed";
    BufferedWriter writer;
    BufferedReader reader;

    public static void main(String argv[]) {
        String[] randomLogins = {"Miranda", "Numérobis", "Titus", "Bernarbé", "Hubert", "Karis", "Agathe", "Cruella", "Miss France"};
        Random random = new Random();

        try {
            System.out.println("Welcome to the client side..");
            ChatClient chatClient = new ChatClient(randomLogins[random.nextInt(6)]);
            Thread thread = new Thread(chatClient);
            thread.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public ChatClient(String l) {
        login = l;

        JFrame chatWindow = new JFrame("Welcome to the amazing chat !");
        chatWindow.setSize(400, 400);

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());

        tx = new JTextField();
        p1.add(tx, BorderLayout.CENTER);

        JButton b1 = new JButton("Send");
        p1.add(b1, BorderLayout.EAST);

        ta = new JTextArea();
        p2.add(ta, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.SOUTH);

        chatWindow.setContentPane(p2);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String s = login + " : " + tx.getText();
                tx.setText("");

                try {
                    writer.write(s);
                    writer.write("\r\n");
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        chatWindow.setVisible(true);

        connectWithServer();
    }

    private void connectWithServer() {
        try {
            Socket socketClient = new Socket("localhost", 5558);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String serverMsg="";
            while((serverMsg = reader.readLine()) != null){
                System.out.println("from server: " + serverMsg);
                ta.append(serverMsg+"\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
