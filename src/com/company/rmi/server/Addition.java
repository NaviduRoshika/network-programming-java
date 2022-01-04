package com.company.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class Addition extends UnicastRemoteObject implements AdditionInterface {

    public Addition() throws RemoteException{
        super();
    }

    @Override
    public int Add(int a, int b) throws RemoteException{
        return a+b;
    }

}
