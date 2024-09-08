package database;
import java.sql.*;

public class DBOperations extends DBConnection {

    @Override
    public void selectOperation() {
        try {
            // Step 3: Create a statement
            String query = "SELECT * FROM aeroplanes";
            PreparedStatement pst = con.prepareStatement(query);
            
            // Step 4: Execute statement
            ResultSet rs = pst.executeQuery();
            
            // Step 5: Process ResultSet
            while (rs.next()) {
                int id = rs.getInt("ID");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int numberOfEngines = rs.getInt("number_of_engines");
                
                System.out.println("ID: " + id + " Brand: " + brand + " Model: " + model + " Number of Engines: " + numberOfEngines);
            }
            // Step 6: Close statement
            pst.close();
            rs.close();
        } catch (SQLException sqle) {
            System.out.println("Select Operation Failed: " + sqle.getMessage());
        }
    }

    @Override
    public void insertOperation(int id, String brand, String model, int numberOfEngines) {
        try {
            // Step 3: Create a statement
            String query = "INSERT INTO aeroplanes (ID, brand, model, number_of_engines) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, brand);
            pst.setString(3, model);
            pst.setInt(4, numberOfEngines);
            
            // Step 4: Execute statement
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new aeroplane was inserted successfully into the database!");
            }
            // Step 6: Close statement
            pst.close();
        } catch (SQLException sqle) {
            System.out.println("Insert Operation Failed: " + sqle.getMessage());
        }
    }

    @Override
    public void deleteOperation(int id) {
        try {
            // Step 3: Create a statement
            String query = "DELETE FROM aeroplanes WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            
            // Step 4: Execute statement
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Aeroplane with ID " + id + " was deleted successfully!");
            } else {
                System.out.println("No aeroplane found with ID " + id);
            }
            // Step 6: Close statement
            pst.close();
        } catch (SQLException sqle) {
            System.out.println("Delete Operation Failed: " + sqle.getMessage());
        }
    }

    @Override
    public void updateOperation(int id, String newBrand, String newModel, int newNumberOfEngines) {
        try {
            // Step 3: Create a statement
            String query = "UPDATE aeroplanes SET brand = ?, model = ?, number_of_engines = ? WHERE ID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, newBrand);
            pst.setString(2, newModel);
            pst.setInt(3, newNumberOfEngines);
            pst.setInt(4, id);
            
            // Step 4: Execute statement
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Aeroplane with ID " + id + " was updated successfully!");
            } else {  
                System.out.println("No aeroplane found with ID " + id + " !");
            }
            // Step 6: Close statement
            pst.close();
        } catch (SQLException sqle) {
            System.out.println("Update Operation Failed: " + sqle.getMessage());
        }
    }
}
