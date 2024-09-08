package AOOPClassAssignment1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class Tractor extends MotorVehicle {
    private boolean isFourWheelDrive;
    private boolean hasFrontLoader;

    public Tractor(int weight, String color, int mvID, String model, String make, int engineCapacity, String owner, int mileage, boolean isFourWheelDrive, boolean hasFrontLoader) {
        super(weight, color, mvID, model, make, engineCapacity, owner, mileage);
        this.isFourWheelDrive = isFourWheelDrive;
        this.hasFrontLoader = hasFrontLoader;
    }

    @Override
    public void start() {
        System.out.println("Tractor has started");
    }

    @Override
    public void repair() {
        System.out.println("Tractor has been repaired");
    }

    @Override
    public void stopEngine() {
        System.out.println(getMake() + " " + getModel() + " tractor has stopped");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Four Wheel Drive: " + (isFourWheelDrive ? "Yes" : "No"));
        System.out.println("Has Front Loader: " + (hasFrontLoader ? "Yes" : "No"));
    }

    public void attachPlow() {
        System.out.println("Plow attached");
    }

    public void detachPlow() {
        System.out.println("Plow detached");
    }

    // Getters and Setters
    public boolean isFourWheelDrive() {
        return isFourWheelDrive;
    }

    public void setFourWheelDrive(boolean isFourWheelDrive) {
        this.isFourWheelDrive = isFourWheelDrive;
    }

    public boolean hasFrontLoader() {
        return hasFrontLoader;
    }

    public void setHasFrontLoader(boolean hasFrontLoader) {
        this.hasFrontLoader = hasFrontLoader;
    }

    public static void loadTractorsFromDatabase(Connection connection, ObservableList<Tractor> tractors) {
        String query = "SELECT * FROM tractors";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Tractor tractor = new Tractor(
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
                tractors.add(tractor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToDatabase(Connection connection) {
        String query = "INSERT INTO tractors (mv_id, weight, color, model, make, engine_capacity, owner, mileage, is_four_wheel_drive, has_front_loader) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getMvID());
            pstmt.setInt(2, getWeight());
            pstmt.setString(3, getColor());
            pstmt.setString(4, getModel());
            pstmt.setString(5, getMake());
            pstmt.setInt(6, getEngineCapacity());
            pstmt.setString(7, getOwner());
            pstmt.setInt(8, getMileage());
            pstmt.setBoolean(9, isFourWheelDrive());
            pstmt.setBoolean(10, hasFrontLoader());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInDatabase(Connection connection) {
        String query = "UPDATE tractors SET weight = ?, color = ?, model = ?, make = ?, engine_capacity = ?, owner = ?, mileage = ?, is_four_wheel_drive = ?, has_front_loader = ? WHERE mv_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getWeight());
            pstmt.setString(2, getColor());
            pstmt.setString(3, getModel());
            pstmt.setString(4, getMake());
            pstmt.setInt(5, getEngineCapacity());
            pstmt.setString(6, getOwner());
            pstmt.setInt(7, getMileage());
            pstmt.setBoolean(8, isFourWheelDrive());
            pstmt.setBoolean(9, hasFrontLoader());
            pstmt.setInt(10, getMvID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(Connection connection) {
        String query = "DELETE FROM tractors WHERE mv_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getMvID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
