package com.company.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPClientMcast {
    public final static int MCAST_PORT = 50001;
    public final static String MCAST_GROUP_IP = "224.0.0.1";

    public static void main(String[] args) {
        try {
            // create socket and bind to listening port
            MulticastSocket clientMulticastSocket = new MulticastSocket(MCAST_PORT);

            // join (subscribe) to multi cast group
            clientMulticastSocket.joinGroup(InetAddress.getByName(MCAST_GROUP_IP));

            // buffer for data
            byte[] buffData = new byte[1024];

            DatagramPacket datagramPacket = new DatagramPacket(buffData,buffData.length);

            clientMulticastSocket.receive(datagramPacket);

            String strReceived = new String(datagramPacket.getData());
            System.out.println("Client Received Data : " + strReceived);

            // unsubscribe group
            clientMulticastSocket.leaveGroup(InetAddress.getByName(MCAST_GROUP_IP));

            clientMulticastSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
