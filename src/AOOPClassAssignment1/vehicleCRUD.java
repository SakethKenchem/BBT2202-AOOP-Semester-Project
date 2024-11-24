package AOOPClassAssignment1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class vehicleCRUD extends databaseConnection {

    public vehicleCRUD() {
        super();
    }

    
    public List<Vehicle> selectOperation() {
        List<Vehicle> vehicles = new ArrayList<>();
        String[] tables = {"trucks", "motorcycles", "tractors"};

        for (String table : tables) {
            String query = "SELECT * FROM " + table;
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Vehicle vehicle = null;
                    switch (table) {
                        case "trucks":
                            vehicle = new Truck(
                                rs.getInt("weight"),
                                rs.getString("color"),
                                rs.getInt("mv_id"),
                                rs.getString("model"),
                                rs.getString("make"),
                                rs.getInt("engine_capacity"),
                                rs.getString("owner"),
                                rs.getInt("mileage"),
                                rs.getInt("passenger_capacity"),
                                rs.getInt("number_of_wheels"),
                                rs.getInt("towing_capacity"),
                                rs.getDouble("load_capacity")
                            );
                            break;
                        case "motorcycles":
                            vehicle = new Motorcycle(
                                rs.getInt("weight"),
                                rs.getString("color"),
                                rs.getInt("mv_id"),
                                rs.getString("model"),
                                rs.getString("make"),
                                rs.getInt("engine_capacity"),
                                rs.getString("owner"),
                                rs.getInt("mileage"),
                                rs.getBoolean("nice_ride_quality"),
                                rs.getBoolean("cool_exhaust_sound"),
                                rs.getBoolean("has_sidecar")
                            );
                            break;
                        case "tractors":
                            vehicle = new Tractor(
                                rs.getInt("weight"),
                                rs.getString("color"),
                                rs.getInt("mv_id"),
                                rs.getString("model"),
                                rs.getString("make"),
                                rs.getInt("engine_capacity"),
                                rs.getString("owner"),
                                rs.getInt("mileage"),
                                rs.getBoolean("is_four_wheel_drive"),
                                rs.getBoolean("has_front_loader")
                            );
                            break;
                    }
                    if (vehicle != null) {
                        vehicles.add(vehicle);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vehicles;
    }

    
    public void insertOperation(Vehicle vehicle) {
        String table = vehicle.getClass().getSimpleName().toLowerCase() + "s";

        String commonQuery = "INSERT INTO " + table + " (mv_id, weight, color, model, make, " +
                "engine_capacity, owner, mileage";

        // Specific columns for subclasses
        String specificColumns = "";
        String specificValues = "";
        
        if (vehicle instanceof Truck) {
            specificColumns = ", passenger_capacity, number_of_wheels, towing_capacity, load_capacity";
            Truck truck = (Truck) vehicle;
            specificValues = String.format(", %d, %d, %d, %.2f",
                truck.getPassengerCapacity(), truck.getNumberOfWheels(), 
                truck.getTowingCapacity(), truck.getLoadCapacity());
            
        } 
        else if (vehicle instanceof Motorcycle) {
            specificColumns = ", nice_ride_quality, cool_exhaust_sound, has_sidecar";
            Motorcycle motorcycle = (Motorcycle) vehicle;
            specificValues = String.format(", %b, %b, %b",
                motorcycle.hasNiceRideQuality(), motorcycle.hasCoolExhaustSound(), 
                motorcycle.hasSidecar());
        } 
        else if (vehicle instanceof Tractor) {
            specificColumns = ", is_four_wheel_drive, has_front_loader";
            Tractor tractor = (Tractor) vehicle;
            specificValues = String.format(", %b, %b",
                tractor.isFourWheelDrive(), tractor.hasFrontLoader());
        }

        String query = commonQuery + specificColumns + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?" + specificValues + ")";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, vehicle.getMvID());
            pstmt.setInt(2, vehicle.getWeight());
            pstmt.setString(3, vehicle.getColor());
            pstmt.setString(4, vehicle.getModel());
            pstmt.setString(5, vehicle.getMake());
            pstmt.setInt(6, vehicle.getEngineCapacity());
            pstmt.setString(7, vehicle.getOwner());
            pstmt.setInt(8, vehicle.getMileage());
            pstmt.executeUpdate();
            
            
            System.out.println("Vehicle with ID: " + getMvID + " inserted successfully into the table " + truck);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateOperation(int id, String newOwner, String newColor) {
        String[] tables = {"trucks", "motorcycles", "tractors"};
        for (String table : tables) {
            String query = "UPDATE " + table + " SET owner = ?, color = ? WHERE mv_id = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, newOwner);
                pst.setString(2, newColor);
                pst.setInt(3, id);
                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Vehicle with ID " + id + " was updated successfully in " + table + "!");
                    return;
                }
            } catch (SQLException sqle) {
                System.out.println("Update Operation Failed for " + table + ": " + sqle.getMessage());
            }
        }
        System.out.println("No vehicle found with ID " + id);
    }

    
    public void deleteOperation(String vehicleType, int id) {
        String[] tables = {"trucks", "motorcycles", "tractors"};
        for (String table : tables) {
            String query = "DELETE FROM " + table + " WHERE mv_id = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, id);
                int rowsDeleted = pst.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Vehicle with ID " + id + " was deleted successfully from " + table + "!");
                    return;
                }
            } catch (SQLException sqle) {
                System.out.println("Delete Operation Failed for " + table + ": " + sqle.getMessage());
            }
        }
        System.out.println("No vehicle found with ID " + id);
    }
    
    
    
}
