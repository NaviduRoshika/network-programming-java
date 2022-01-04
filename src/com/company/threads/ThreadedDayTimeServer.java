package com.company.threads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ThreadedDayTimeServer {

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

                RequestProcessorTask requestProcessorTask = new RequestProcessorTask(socketClient);
                System.out.println("Thread Created and handed over.");

                Thread t = new Thread(requestProcessorTask);
                t.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

