package com.company.threads.assignment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedDayTimeserver {

    public final static int SERVICE_PORT = 13;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socketClient = null;

        try {
            serverSocket = new ServerSocket(SERVICE_PORT);
            for (;;){
                socketClient = serverSocket.accept();
                RequestProcessorThread thread = new RequestProcessorThread(socketClient);
                System.out.println("Thread created and handed over the connection.\nThread [" +
                                   thread.toString() + "] Soc [" + socketClient.toString() + "]");
                Thread thread1 = new Thread(thread);
                thread1.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
