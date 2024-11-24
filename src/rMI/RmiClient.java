package rMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    public static void main(String[] args) {
        try {
            // Connect to the RMI registry on localhost at port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Look up the remote object by the name "DBOperations"
            DatabaseInterface di = (DatabaseInterface) registry.lookup("DBOperations");
            
            di.selectOperation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
