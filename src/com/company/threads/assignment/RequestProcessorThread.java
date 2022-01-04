package com.company.threads.assignment;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class RequestProcessorThread implements Runnable{
    private Socket socketClient;
    public RequestProcessorThread(Socket soc){socketClient = soc;}
    @Override
    public void run() {
        System.out.println(this.toString() + " thread started. processing client " + socketClient);
        try {
            Date today = new Date();
            PrintWriter out;
            out = new PrintWriter(socketClient.getOutputStream(),true);
            out.println(today);
            InputStream is = socketClient.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receivedData = br.readLine();
            System.out.println("Received from client : " + receivedData);
            socketClient.close();
        } catch (IOException e) {
            System.out.println("Caught Exception");
            e.printStackTrace();
        }
        System.out.println("Thread exiting...");
    }
}
