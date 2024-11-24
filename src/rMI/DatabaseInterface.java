package rMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DatabaseInterface extends Remote {
    void selectOperation() throws RemoteException;
    void insertOperation(int id, String brand, String model, int numberOfEngines) throws RemoteException;
    void deleteOperation(int id) throws RemoteException;
    void updateOperation(int id, String newBrand, String newModel, int newNumberOfEngines) throws RemoteException;
}
