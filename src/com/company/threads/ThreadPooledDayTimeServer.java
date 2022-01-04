package com.company.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledDayTimeServer {

    public final static int SERVICE_PORT = 13;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
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

                executorService.execute(requestProcessorTask);  // Thread t = new Thread(requestProcessorTask);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        executorService.shutdown(); // This will make executor accept no new threads
        while (!executorService.isTerminated()){} // wait until all threads are finished

    }

}
