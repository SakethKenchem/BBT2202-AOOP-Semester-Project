// Package: RMI2
package RMI2;

import AOOPClassAssignment1.Vehicle;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseInterface2 extends Remote {
    List<Vehicle> selectOperation() throws RemoteException;

    void insertOperation(Vehicle vehicle) throws RemoteException;

    void updateOperation(int vehicleId, String newOwner, String newColor) throws RemoteException;

    void deleteOperation(int vehicleId) throws RemoteException;
}