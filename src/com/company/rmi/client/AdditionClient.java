package com.company.rmi.client;

import com.company.rmi.server.AdditionInterface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class AdditionClient {
    public static void main(String[] args) {
        AdditionInterface additionRmtObj;
        System.setProperty("java.security.policy","G:''\\Academic\\Level 03\\NP\\Coding\\src\\com\\company\\rmi\\security.policy");
        System.setSecurityManager(new RMISecurityManager());

        try {
            additionRmtObj = (AdditionInterface) Naming.lookup("rmi://localhost/AddServe");
            int iResult = additionRmtObj.Add(2,19);
            System.out.println("Result is : " + iResult);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
