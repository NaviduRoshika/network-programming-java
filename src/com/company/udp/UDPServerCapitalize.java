package com.company.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Locale;

public class UDPServerCapitalize {
    public final static int SERVICE_PORT = 50001;

    public static void main(String[] args) {
        try {
            DatagramSocket serverDatagramSocket =  new DatagramSocket(SERVICE_PORT);

            // Buffers for store datagram data in datagramPacket object
            byte[] receivedDataBuff = new byte[1024];
            byte[] sendDataBuff = new byte[1024];

            DatagramPacket packetIn = new DatagramPacket(receivedDataBuff,receivedDataBuff.length);

            System.out.println("Waiting for a request....");
            // Receive incoming datagram
            // blocking system call
            try {
                serverDatagramSocket.receive(packetIn);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String strInData = new String(packetIn.getData());
            System.out.println("Server Received Data : " + strInData);
            sendDataBuff = strInData.toUpperCase(Locale.ROOT).getBytes();

            // Find sender's address and port from received packet
            InetAddress inAddress = packetIn.getAddress();
            int inPort = packetIn.getPort();

            // Create datagram to send
            DatagramPacket packetOut = new DatagramPacket(sendDataBuff,sendDataBuff.length,inAddress,inPort);

            // Send response datagram
            try {
                serverDatagramSocket.send(packetOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
            serverDatagramSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
