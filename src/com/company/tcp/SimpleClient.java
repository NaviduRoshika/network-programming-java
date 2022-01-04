package com.company.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost",13);
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receivedData = br.readLine();
            System.out.println("Received Data : " + receivedData);

            PrintWriter out;
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("Thank you");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
