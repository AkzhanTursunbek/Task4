package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    Integer pollElem() throws RemoteException;
    void addElem(int num) throws RemoteException;

    void Calculate(int num) throws RemoteException;
}