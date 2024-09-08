package AOOPClassAssignment1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class Motorcycle extends MotorVehicle {

    private boolean niceRideQuality;
    private boolean coolExhaustSound;
    private boolean hasSidecar;

    public Motorcycle(int weight, String color, int mvID, String model, String make, int engineCapacity, String owner, int mileage, boolean niceRideQuality, boolean coolExhaustSound, boolean hasSidecar) {
        super(weight, color, mvID, model, make, engineCapacity, owner, mileage);
        this.niceRideQuality = niceRideQuality;
        this.coolExhaustSound = coolExhaustSound;
        this.hasSidecar = hasSidecar;
    }

    public boolean hasNiceRideQuality() {
        return niceRideQuality;
    }

    public void setHasNiceRideQuality(boolean niceRideQuality) {
        this.niceRideQuality = niceRideQuality;
    }

    public boolean hasCoolExhaustSound() {
        return coolExhaustSound;
    }

    public void setCoolExhaustSound(boolean coolExhaustSound) {
        this.coolExhaustSound = coolExhaustSound;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    public void saveToDatabase(Connection connection) {
        String query = "INSERT INTO motorcycles (weight, color, mv_id, model, make, engine_capacity, owner, mileage, nice_ride_quality, cool_exhaust_sound, has_sidecar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getWeight());
            pstmt.setString(2, getColor());
            pstmt.setInt(3, getMvID());
            pstmt.setString(4, getModel());
            pstmt.setString(5, getMake());
            pstmt.setInt(6, getEngineCapacity());
            pstmt.setString(7, getOwner());
            pstmt.setInt(8, getMileage());
            pstmt.setBoolean(9, hasNiceRideQuality());
            pstmt.setBoolean(10, hasCoolExhaustSound());
            pstmt.setBoolean(11, hasSidecar());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInDatabase(Connection connection) {
        String query = "UPDATE motorcycles SET weight = ?, color = ?, model = ?, make = ?, engine_capacity = ?, owner = ?, mileage = ?, nice_ride_quality = ?, cool_exhaust_sound = ?, has_sidecar = ? WHERE mv_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getWeight());
            pstmt.setString(2, getColor());
            pstmt.setString(3, getModel());
            pstmt.setString(4, getMake());
            pstmt.setInt(5, getEngineCapacity());
            pstmt.setString(6, getOwner());
            pstmt.setInt(7, getMileage());
            pstmt.setBoolean(8, hasNiceRideQuality());
            pstmt.setBoolean(9, hasCoolExhaustSound());
            pstmt.setBoolean(10, hasSidecar());
            pstmt.setInt(11, getMvID());

            System.out.println("Executing update: " + pstmt);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Motorcycle updated in database successfully.");
            } else {
                System.out.println("No rows affected. Update might have failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(Connection connection) {
        String query = "DELETE FROM motorcycles WHERE mv_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getMvID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadMotorcyclesFromDatabase(Connection connection, ObservableList<Motorcycle> motorcycles) {
        String query = "SELECT * FROM motorcycles";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int weight = rs.getInt("weight");
                String color = rs.getString("color");
                int mvID = rs.getInt("mv_id");
                String model = rs.getString("model");
                String make = rs.getString("make");
                int engineCapacity = rs.getInt("engine_capacity");
                String owner = rs.getString("owner");
                int mileage = rs.getInt("mileage");
                boolean niceRideQuality = rs.getBoolean("nice_ride_quality");
                boolean coolExhaustSound = rs.getBoolean("cool_exhaust_sound");
                boolean hasSidecar = rs.getBoolean("has_sidecar");

                Motorcycle motorcycle = new Motorcycle(weight, color, mvID, model, make, engineCapacity, owner, mileage, niceRideQuality, coolExhaustSound, hasSidecar);
                motorcycles.add(motorcycle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
