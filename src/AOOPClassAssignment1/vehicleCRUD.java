package AOOPClassAssignment1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vehicleCRUD extends databaseConnection {

    public vehicleCRUD() {
        super();
    }

    public void selectOperation() {
        try {
            String query = "SELECT * FROM vehicles";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                int weight = rs.getInt("weight");
                String color = rs.getString("color");
                String model = rs.getString("model");
                String make = rs.getString("make");
                int engineCapacity = rs.getInt("engine_capacity");
                String owner = rs.getString("owner");
                int mileage = rs.getInt("mileage");
                System.out.println(String.format("ID: %d, Type: %s, Weight: %d, Color: %s, Model: %s, Make: %s, Engine Capacity: %d, Owner: %s, Mileage: %d", id, type, weight, color, model, make, engineCapacity, owner, mileage));
            }
            
            pst.close();
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Select Operation Failed: " + sqle.getMessage());
        }
        
    }

    public void insertOperation(Vehicle vehicle) {
    try {
        String query = "INSERT INTO vehicles (id, type, weight, color, model, make, engine_capacity, owner, mileage, passenger_capacity, number_of_wheels, towing_capacity, load_capacity, current_load, four_wheel_drive, front_loader, has_sidecar, cool_exhaust_sound) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, vehicle.getMvID());
        pst.setString(2, vehicle.getClass().getSimpleName());
        pst.setInt(3, vehicle.getWeight());
        pst.setString(4, vehicle.getColor());
        pst.setString(5, vehicle.getModel());
        pst.setString(6, vehicle.getMake());
        pst.setInt(7, vehicle.getEngineCapacity());
        pst.setString(8, vehicle.getOwner());
        pst.setInt(9, vehicle.getMileage());

        if (vehicle instanceof Truck) {
            Truck t = (Truck) vehicle;
            pst.setInt(10, t.getPassengerCapacity());
            pst.setInt(11, t.getNumberOfWheels());
            pst.setInt(12, t.getTowingCapacity());
            pst.setDouble(13, t.getLoadCapacity());
            pst.setDouble(14, t.getCurrentLoad());
            pst.setNull(15, java.sql.Types.BOOLEAN);
            pst.setNull(16, java.sql.Types.BOOLEAN);
            pst.setNull(17, java.sql.Types.BOOLEAN);
            pst.setNull(18, java.sql.Types.BOOLEAN);
        } else if (vehicle instanceof Tractor) {
            Tractor t = (Tractor) vehicle;
            pst.setNull(10, java.sql.Types.INTEGER);
            pst.setNull(11, java.sql.Types.INTEGER);
            pst.setNull(12, java.sql.Types.INTEGER);
            pst.setNull(13, java.sql.Types.DOUBLE);
            pst.setNull(14, java.sql.Types.DOUBLE);
            pst.setBoolean(15, t.isFourWheelDrive());
            pst.setBoolean(16, t.hasFrontLoader());
            pst.setNull(17, java.sql.Types.BOOLEAN);
            pst.setNull(18, java.sql.Types.BOOLEAN);
        } else if (vehicle instanceof Motorcycle) {
            Motorcycle m = (Motorcycle) vehicle;
            pst.setNull(10, java.sql.Types.INTEGER);
            pst.setNull(11, java.sql.Types.INTEGER);
            pst.setNull(12, java.sql.Types.INTEGER);
            pst.setNull(13, java.sql.Types.DOUBLE);
            pst.setNull(14, java.sql.Types.DOUBLE);
            pst.setNull(15, java.sql.Types.BOOLEAN);
            pst.setNull(16, java.sql.Types.BOOLEAN);
            pst.setBoolean(17, m.hasSidecar());
            pst.setBoolean(18, m.hasCoolExhaustSound());
        }

        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new vehicle was inserted successfully into the database!");
        }
        pst.close();
    } catch (SQLException sqle) {
        sqle.printStackTrace(); // Prints the stack trace for detailed error debugging
    }
}

    public void deleteOperation(int id) {
        try {
            String query = "DELETE FROM vehicles WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Vehicle with ID " + id + " was deleted successfully!");
            } else {
                System.out.println("No vehicle found with ID " + id);
            }
            pst.close();
        } catch (SQLException sqle) {
            System.out.println("Delete Operation Failed: " + sqle.getMessage());
        }
    }

    public void updateOperation(int id, String newOwner, String color) {
    try {
        String query = "UPDATE vehicles SET owner = ?, color = ? WHERE id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, newOwner);
        pst.setString(2, color);
        pst.setInt(3, id);
        
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Vehicle with ID " + id + " was updated successfully!");
        } else {
            System.out.println("No vehicle found with ID " + id);
        }
        pst.close();
    } catch (SQLException sqle) {
        System.out.println("Update Operation Failed: " + sqle.getMessage());
    }
}
}
