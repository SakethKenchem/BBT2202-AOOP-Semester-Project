package RMI2;

import java.nio.channels.AlreadyBoundException;
import java.rmi.AccessException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Create the remote object (DatabaseInterfaceImplementation)
            DatabaseInterface2 databaseImplementation = new DatabaseInterfaceImplementation();

            
            Registry registry = LocateRegistry.createRegistry(1099);

            try {
                registry.bind("Test", (Remote) databaseImplementation);
            } catch (AlreadyBoundException | AccessException ex) {
                ex.getMessage();
            }

            System.out.println("RMI Server is running...");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}