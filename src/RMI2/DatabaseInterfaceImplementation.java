// Package: RMI2
package RMI2;

import AOOPClassAssignment1.Vehicle;
import AOOPClassAssignment1.vehicleCRUD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DatabaseInterfaceImplementation extends UnicastRemoteObject implements DatabaseInterface2 {

    private vehicleCRUD vehicleCrud;

    // Constructor
    protected DatabaseInterfaceImplementation() throws RemoteException {
        vehicleCrud = new vehicleCRUD(); // Initialize vehicleCRUD instance
    }

    @Override
    public List<Vehicle> selectOperation() throws RemoteException {
        return vehicleCrud.selectOperation(); // Call CRUD select() method to retrieve all vehicles
    }

    @Override
    public void insertOperation(Vehicle vehicle) throws RemoteException {
        vehicleCrud.insertOperation(vehicle); // Call CRUD insert() method to add a vehicle to the database
    }

    @Override
    public void updateOperation(int vehicleId, String newOwner, String newColor) throws RemoteException {
        vehicleCrud.updateOperation(vehicleId, newOwner, newColor); // Call CRUD update() method
    }

    @Override
    public void deleteOperation(int vehicleId) throws RemoteException {
        String vehicleType = ""; 
        vehicleCrud.deleteOperation(vehicleType, vehicleId);  
    }
}