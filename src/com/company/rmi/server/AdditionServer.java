package com.company.rmi.server;

import com.company.rmi.server.Addition;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AdditionServer {
    public static void main(String[] args){

        System.setProperty("java.security.policy","G:''\\Academic\\Level 03\\NP\\Coding\\src\\com\\company\\rmi\\security.policy");
        System.setSecurityManager(new RMISecurityManager());

        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            Addition addition = new Addition();
            Naming.rebind("rmi://localhost/AddServ",addition);
            System.out.println("Server is ready on rmi://localhost/AddServ");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
