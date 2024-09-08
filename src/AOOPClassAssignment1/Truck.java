package AOOPClassAssignment1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class Truck extends MotorVehicle {
    private int passengerCapacity;
    private int numberOfWheels;
    private int towingCapacity;
    private double loadCapacity;
    private double currentLoad;

    public Truck(int weight, String color, int mvID, String model, String make, int engineCapacity, String owner, int mileage, int passengerCapacity, int numberOfWheels, int towingCapacity, double loadCapacity) {
        super(weight, color, mvID, model, make, engineCapacity, owner, mileage);
        this.passengerCapacity = passengerCapacity;
        this.numberOfWheels = numberOfWheels;
        this.towingCapacity = towingCapacity;
        this.loadCapacity = loadCapacity;
        this.currentLoad = 0;
    }

    @Override
    public void start() {
        System.out.println("Truck has started");
    }

    @Override
    public void repair() {
        System.out.println("Truck has been repaired");
    }

    @Override
    public void stopEngine() {
        System.out.println(getMake() + " " + getModel() + " truck has stopped");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Wheels: " + numberOfWheels);
        System.out.println("Towing Capacity: " + towingCapacity + " kg");
        System.out.println("Load Capacity: " + loadCapacity + " kg");
        System.out.println("Current Load: " + currentLoad + " kg");
    }

    public void loadCargo(int weight) {
        if (this.currentLoad + weight > this.loadCapacity) {
            System.out.println("Cannot load cargo: exceeds load capacity");
        } else {
            this.currentLoad += weight;
            System.out.println("Cargo loaded. Current load: " + this.currentLoad + " kg");
        }
    }

    public void unloadCargo(int weight) {
        if (weight > this.currentLoad) {
            System.out.println("Cannot unload cargo: not enough load");
        } else {
            this.currentLoad -= weight;
            System.out.println("Cargo unloaded. Current load: " + this.currentLoad + " kg");
        }
    }

    public void attachTrailer() {
        System.out.println("Trailer attached");
    }

    public void detachTrailer() {
        System.out.println("Trailer detached");
    }

    // Getters and Setters
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    public static void loadTrucksFromDatabase(Connection connection, ObservableList<Truck> trucks) {
        String query = "SELECT * FROM trucks";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Truck truck = new Truck(
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
                truck.setCurrentLoad(rs.getDouble("current_load"));
                trucks.add(truck);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToDatabase(Connection connection) {
        String query = "INSERT INTO trucks (mv_id, weight, color, model, make, engine_capacity, owner, mileage, passenger_capacity, number_of_wheels, towing_capacity, load_capacity, current_load) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getMvID());
            pstmt.setInt(2, getWeight());
            pstmt.setString(3, getColor());
            pstmt.setString(4, getModel());
            pstmt.setString(5, getMake());
            pstmt.setInt(6, getEngineCapacity());
            pstmt.setString(7, getOwner());
            pstmt.setInt(8, getMileage());
            pstmt.setInt(9, getPassengerCapacity());
            pstmt.setInt(10, getNumberOfWheels());
            pstmt.setInt(11, getTowingCapacity());
            pstmt.setDouble(12, getLoadCapacity());
            pstmt.setDouble(13, getCurrentLoad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInDatabase(Connection connection) {
        String query = "UPDATE trucks SET weight = ?, color = ?, model = ?, make = ?, engine_capacity = ?, owner = ?, mileage = ?, passenger_capacity = ?, number_of_wheels = ?, towing_capacity = ?, load_capacity = ?, current_load = ? WHERE mv_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getWeight());
            pstmt.setString(2, getColor());
            pstmt.setString(3, getModel());
            pstmt.setString(4, getMake());
            pstmt.setInt(5, getEngineCapacity());
            pstmt.setString(6, getOwner());
            pstmt.setInt(7, getMileage());
            pstmt.setInt(8, getPassengerCapacity());
            pstmt.setInt(9, getNumberOfWheels());
            pstmt.setInt(10, getTowingCapacity());
            pstmt.setDouble(11, getLoadCapacity());
            pstmt.setDouble(12, getCurrentLoad());
            pstmt.setInt(13, getMvID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(Connection connection) {
        String query = "DELETE FROM trucks WHERE mv_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getMvID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
