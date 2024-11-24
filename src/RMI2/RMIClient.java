// Package: RMI2
package RMI2;

import AOOPClassAssignment1.Motorcycle;
import AOOPClassAssignment1.Truck;
import AOOPClassAssignment1.Vehicle;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIClient {
    public static void main(String[] args) {
        try {
            
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            
            DatabaseInterface2 dbInterface = (DatabaseInterface2) registry.lookup("Test");

            
            System.out.println("Fetching all vehicles from the server:");
            List<Vehicle> vehicles = dbInterface.selectOperation();
            vehicles.forEach(Vehicle::displayDetails);

            
            System.out.println("Adding a new motorcycle...");
            Motorcycle newBike = new Motorcycle(
                200, "Red", 1200, "GSX-R1000", "Suzuki", 999, "John Doe", 5000, true, true, false
            );
            dbInterface.insertOperation(newBike);

            // Update an existing vehicle’s owner and color
            System.out.println("Updating vehicle 1200...");
            dbInterface.updateOperation(1200, "Jane Doe", "Blue");

           
            System.out.println("Deleting vehicle 1200...");
            dbInterface.deleteOperation(1200);

        } catch (Exception e) {
            System.out.println("RMI Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}