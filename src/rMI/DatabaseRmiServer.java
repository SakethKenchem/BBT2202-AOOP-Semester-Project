package rMI;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.channels.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.AccessException;
public class DatabaseRmiServer {
    public static void main(String[] args) {
        try {
            
            DatabaseIntImpl dii = new DatabaseIntImpl();
            
            Registry registry = LocateRegistry.createRegistry(1099);
            
            try {
                registry.bind("DBOperations", (Remote) dii);
            }catch(AlreadyBoundException | AccessException ex) {
                ex.getMessage();
            }
            
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
