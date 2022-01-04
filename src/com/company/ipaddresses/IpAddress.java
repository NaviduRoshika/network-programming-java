package com.company.ipaddresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddress {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.getHostName() + " - " + address.getHostAddress());

            InetAddress address1 = InetAddress.getByName("www.google.com");
            System.out.println(address1.getHostName() + " - " + address1.getHostAddress());

            InetAddress[] address2 = InetAddress.getAllByName("www.google.com");
            for (int i = 0; i < address2.length; i++) {
                System.out.println(address2[i].getHostName() + " - " + address2[i].getHostAddress());
            }

            // Exercise
            InetAddress addrGoog = InetAddress.getByName("www.google.com");   // OK
            InetAddress addrIpAsName = InetAddress.getByName("172.217.174.68");    // ?
            InetAddress addrByIp = InetAddress.getByAddress(new byte[]{98, (byte)139, (byte)183,24}); // ?
            InetAddress addrGoogByIp = InetAddress.getByAddress(new byte[]{(byte)172, (byte)217, (byte)174,68}); // ?
            InetAddress addrLocal = InetAddress.getLocalHost(); // OK
            InetAddress addrLocalByIp = InetAddress.getByName("127.0.0.1"); // OK

            System.out.println("-----------------------------------------------------");
            System.out.println("Google\t" + addrGoog.getHostName() + " - " + addrGoog.getHostAddress());
            System.out.println("172.217.174.68(N)\t" + addrIpAsName.getHostName() + " - " + addrIpAsName.getHostAddress());
            System.out.println("98.139.183.24(I)\t" + addrByIp.getHostName() + " - " + addrByIp.getHostAddress());
            System.out.println("Google Ip\t" + addrGoogByIp.getHostName() + " - " + addrGoogByIp.getHostAddress());
            System.out.println("Local (N)\t" + addrLocal.getHostName() + " - " + addrLocal.getHostAddress());
            System.out.println("Local (I) " + addrLocalByIp.getHostName() + " - " + addrLocalByIp.getHostAddress());



        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
