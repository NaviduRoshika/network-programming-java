package com.company.threads.assignment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledDayTimeServer {
    public final static int SERVICE_PORT = 13;public static int requests = 0;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        ServerSocket serverSocket = null;
        Socket socketClient = null;
        try {
            serverSocket = new ServerSocket(SERVICE_PORT);
            for (;;){
                requests++;
                socketClient = serverSocket.accept();
                RequestProcessorThread processorThread = new RequestProcessorThread(socketClient);

                System.out.println("Thread created and handed over the connection.\nThread [" +
                        processorThread.toString() + "] Soc [" + socketClient.toString() + "]");
                executorService.execute(processorThread);
                System.out.println("REQUESTS : " + requests);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){}
        System.out.println("Finished all threads");
    }}
