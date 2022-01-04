package com.company.threads;

import java.io.*;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer {
    public final static int SERVICE_PORT = 13;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socketClient = null;

        try{
            serverSocket = new ServerSocket(SERVICE_PORT);
            System.out.println("Server socket to string..." + serverSocket.toString());

            for (;;){
                socketClient = serverSocket.accept();
                System.out.println("Client connected..." + socketClient.toString());

                Date today = new Date();
                PrintWriter out = new PrintWriter(socketClient.getOutputStream(),true);

                out.println(today);  // write data to the client

                // wait till the client send some data back to the server
                InputStream in = socketClient.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String clientData = br.readLine();

                System.out.println("Client Data : " + clientData);
                socketClient.close(); // always dont forget to close


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
