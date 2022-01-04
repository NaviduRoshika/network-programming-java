package com.company.threads;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class RequestProcessorTask implements Runnable{
    private Socket socketClient;

    public RequestProcessorTask(Socket soc){
        socketClient = soc;
    }

    @Override
    public void run() {
        System.out.println(this.toString() + " Thread Started. Processing Client : " + socketClient);

        try {
            Date date = new Date();
            PrintWriter out;
            out = new PrintWriter(socketClient.getOutputStream(),true);
            out.println(date);  // write data to the client

            // wait till the client send some data back to the server
            InputStream in = socketClient.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String clientData = br.readLine();

            System.out.println("Client Data : " + clientData);
            socketClient.close(); // always dont forget to close

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(this.toString() + " Thread Exiting");
    }
}
