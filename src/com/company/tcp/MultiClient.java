package com.company.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {
    public static void main(String[] args) {
        Socket socket = null;
        for (int i = 0; i < 115; i++) {
            System.out.println(i);
//            for (float j = 0; j <100000000; j++) {
////                System.out.print(j);
//                int x = (int) (j+10);
//            }
//            System.out.println("--");
            try{
                socket = new Socket("localhost",13);
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String receivedData = br.readLine();
                System.out.println("Received Data : " + receivedData);
                PrintWriter out;
                out = new PrintWriter(socket.getOutputStream(),true);
                out.println("Thank you");
                socket.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
