package com.company.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NIODayTimeServer {
    public final static int SERVICE_PORT = 13;
    public static void main(String[] args){

        try {
            // server socket for non-blocking-io
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            // multiplexer - multiplexes messages from different clients
            Selector selector = Selector.open();

            // Set socket channel to be non blocking
            serverSocketChannel.configureBlocking(false);

            // Create socket address object with server address and port
            //InetAddress - consists IP address and host name
            //ISocketAddress - consists Ip address + (and/or hostname) + port number
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",SERVICE_PORT);

            // bind server socket to ip / port
            serverSocketChannel.socket().bind(inetSocketAddress);
            System.out.println("Server socket channel : " + serverSocketChannel.toString());

            // set of events that interested in for "selecting"
            int interestSet = SelectionKey.OP_ACCEPT;
            SelectionKey key = serverSocketChannel.register(selector,interestSet);

            while (true){
                // This line blocks until some events has occurred
                selector.select();

                // Once you have called one of the select() method and its return value has
                // Indicated that one or more channels are ready. we can access ready channels via selectedKetSet
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey selectionKey = (SelectionKey) iterator.next();
                    iterator.remove(); //  remove fetched key

                    if(!selectionKey.isValid()){continue;}

                    if (selectionKey.isAcceptable()){ // Connection accepted by Server socket Channel
                        OnConnectionAcceptable(selectionKey);
                    }
//                    if(selectionKey.isReadable()){} // A channel is ready for reading
//                    if(selectionKey.isWritable()){} // A channel is ready for writing
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void OnConnectionAcceptable(SelectionKey selectionKey){
        try {
            // Get the channel
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            // accept it
            SocketChannel socketChannel = channel.accept();
            System.out.println("Accepted new client connection. " + socketChannel.toString());

            // Send date time
            PrintWriter out = new PrintWriter(socketChannel.socket().getOutputStream(),true);
            Date now = new Date();
            out.println(now);

            // Make this new socket connection with client non-blocking
            // socketChannel.configureBlocking(false);
            // Register it with the selector
            // SelectionKey readKey = socketChannel.register(selector, SelectionKey.OP_READ,SelectionKey.OP_WRITE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
