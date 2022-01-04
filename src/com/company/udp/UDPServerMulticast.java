package com.company.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPServerMulticast {
    public final static int MCAST_PORT = 50001;
    public final static String MCAST_GROUP_IP = "224.0.0.1";

    public static void main(String[] args) {
        try {
            // Create the socket. but we don't bind it
            // because we are only going to send data
            DatagramSocket serverSocket = new DatagramSocket();

            // We don't have to join a multicast group because we are only sending data. not receiving

            // buffer for datagram data
            byte[] buffData = new byte[1024];

            // create datagram packet
            DatagramPacket packetOut = new DatagramPacket(buffData,buffData.length,
                    InetAddress.getByName(MCAST_GROUP_IP),MCAST_PORT);
            // send data
            while (true){
                packetOut.setData(new Date().toString().getBytes());
                System.out.println("Sending...." + new String(packetOut.getData()));
                serverSocket.send(packetOut); // We can set TTL here in MCast Sockets
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
