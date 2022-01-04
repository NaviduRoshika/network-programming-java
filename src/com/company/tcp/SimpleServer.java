package com.company.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) {
        try {
            System.out.println("1. About to create the socket");
            ServerSocket socket =  new ServerSocket(50001); //  create socket nd listen
            System.out.println("2. Server Socket Created");

            System.out.println("3. About to accept the client");
            Socket client = socket.accept();
            System.out.println("4. Client accepted");

            System.out.println("5. About to write data to the client");
            DataOutputStream dos  = new DataOutputStream(client.getOutputStream());
            dos.writeBytes("Hello Socket\n");
            System.out.println("6. Finished writing data to the client");

            System.out.println("7. About to close client connection");
            client.close();
            System.out.println("8. Client connection closed");

        }catch (IOException e){
           e.printStackTrace();
        }
    }
}
