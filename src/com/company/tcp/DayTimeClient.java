package com.company.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class DayTimeClient {
    public final static int SERVICE_PORT = 13;
    public static long total = 0;
    public static int n = 0;
    public static long max = 0;
    public static long min = 0;

    public static void main(String[] args) {
//        System.out.println("Warm up...");
//        connectServer(10,1);
//        printStat();
        System.out.println("\nPerf Measure");
        connectServer(100,10);
        printStat();
    }

    public static void printStat(){
        System.out.println("\nAverage : " + total/n);
        System.out.println("Max : " + max);
        System.out.println("Min : " + min);
    }

    public static void connectServer(int iterations,int reqPerIte){


        for (int i = 0; i < iterations; i++) {
            Date dStart = new Date();

            for (int j = 0; j < reqPerIte; j++) {
                try{
                    Socket socket = new Socket("localhost",SERVICE_PORT);
                    InputStream is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String time = br.readLine();

                    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                    out.println("Bye...");
                    socket.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (i > 0){
                Date dEnd = new Date();
                long diffInMills = dEnd.getTime() - dStart.getTime();
                total = total + diffInMills;
                n++;
                if (diffInMills > max){
                    max = diffInMills;
                }
                if (min < 1){
                    min = diffInMills;
                }
                if (diffInMills < min){
                    min = diffInMills;
                }
                System.out.print(diffInMills + " ,");
            }

        }
    }

}
