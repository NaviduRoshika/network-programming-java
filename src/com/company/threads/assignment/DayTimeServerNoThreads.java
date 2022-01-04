package com.company.threads.assignment;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServerNoThreads {
    public final static int SERVICE_PORT = 13;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socketClient = null;
        try {
            serverSocket = new ServerSocket(SERVICE_PORT);
            for (;;){
                socketClient = serverSocket.accept();
                Date today = new Date();
                PrintWriter out;
                out = new PrintWriter(socketClient.getOutputStream(),true);
                out.println(today);
                InputStream is = socketClient.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String receivedData = br.readLine();
                System.out.println("Received from client : " + receivedData);
                socketClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
