package AOOPClassAssignment1;

import java.sql.*;
public abstract class databaseConnection {
    public Connection con = getConnection();
    
    public Connection getConnection() {
        Connection connection = null;
        
        // Step 1: Load driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: class not found: " + cnfe.getMessage());
        }
        
        // Step 2: Establish connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoopassignment", "root", "");
            System.out.println("Connection established");
        } catch (SQLException sqle) {
            System.out.println("Could not establish connection: " + sqle.getMessage());
        }
        
        return connection;
    }
}
