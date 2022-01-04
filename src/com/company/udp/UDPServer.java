package com.company.udp;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class UDPServer {
    public final static int SERVICE_PORT = 10005;
    public final static int CLIENT_LISTENING_PORT = 10006;

    public static void main(String[] args) {
        // write your code here
        try{
            // Create datagram socket object
            DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);

            // Receiver address and port
            InetAddress clientAddress = InetAddress.getLocalHost();

            // Counter to count UDP packages
            int iCounter = 0;
            while (true){
                Date today = new Date();
                String sDataToSend = "Seq [" + iCounter + "] Data [" + today.toString() + "]";

                // Create datagram to send
                DatagramPacket datagramPacketOut = new DatagramPacket(sDataToSend.getBytes(),sDataToSend.length(),clientAddress,CLIENT_LISTENING_PORT);

                // Send the response datagram
                try {
                    serverSocket.send(datagramPacketOut);
                    String strOut = new String(datagramPacketOut.getData());
                    System.out.println("Sent UDP Data : " + strOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread.sleep(1000);
                iCounter++;
            }

            // Close the datagram socket
            //serverSocket.close();

        } catch (SocketException | UnknownHostException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
