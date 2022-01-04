package com.company.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public final static int CLIENT_LISTENING_PORT = 10006;

    public static void main(String[] args) throws IOException {
        try {
            // Create Datagram Object
            DatagramSocket clientSocket = new DatagramSocket(CLIENT_LISTENING_PORT);
            System.out.println("Datagram Socket Created On : " + clientSocket.getLocalPort());

            boolean isFinished = false;
            while(!isFinished){
                //Create buffer for storing datagram data in datagram packet object
                byte[] buffReceiveData =  new byte[1024]; // for incoming data

                // Create datagram object for wrapping the incoming packet
                DatagramPacket datagramPacket = new DatagramPacket(buffReceiveData,buffReceiveData.length);

                // Receive incoming datagram in datagramPacket object
                try {
                    // RThis is a blocking system call
                    clientSocket.receive(datagramPacket);
                }catch (Exception e){
                    e.printStackTrace();
                }

                // Display received data
                String strReceived = new String(datagramPacket.getData());

                if(strReceived.charAt(0) == 'E' && strReceived.charAt(1) == 'N' && strReceived.charAt(2) == 'D'){
                    isFinished = true;
                    continue;
                }
                System.out.print("Client Received Data : ");

                for (int i = 0; i < strReceived.length(); i++) {
                    if ((int)strReceived.charAt(i) > 0){
                        System.out.print(strReceived.charAt(i));
                    }
                }
                System.out.println();
            }

            clientSocket.close(); // Close datagram Socket

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
