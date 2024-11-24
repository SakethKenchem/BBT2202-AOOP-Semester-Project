package rMI;

import database.DBOperations;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DatabaseIntImpl extends UnicastRemoteObject implements DatabaseInterface {
    private DBOperations dbOperations;

    // Constructor
    public DatabaseIntImpl() throws RemoteException {
        super();
        dbOperations = new DBOperations();  // Initialize DBOperations object
    }

    @Override
    public void selectOperation() throws RemoteException {
        dbOperations.selectOperation();  // Perform the select operation
    }

    @Override
    public void insertOperation(int id, String brand, String model, int numberOfEngines) throws RemoteException {
        dbOperations.insertOperation(id, brand, model, numberOfEngines);  // Insert operation
    }

    @Override
    public void deleteOperation(int id) throws RemoteException {
        dbOperations.deleteOperation(id);  // Delete operation
    }

    @Override
    public void updateOperation(int id, String newBrand, String newModel, int newNumberOfEngines) throws RemoteException {
        dbOperations.updateOperation(id, newBrand, newModel, newNumberOfEngines);  // Update operation
    }
}
