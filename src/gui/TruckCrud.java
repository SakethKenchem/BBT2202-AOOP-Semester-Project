package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import AOOPClassAssignment1.Truck;
import AOOPClassAssignment1.databaseConnection;

import java.sql.Connection;

public class TruckCrud extends Application {

    private TextField mvIDField, weightField, colorField, modelField, makeField, engineCapacityField, ownerField, mileageField, passengerCapacityField, numberOfWheelsField, towingCapacityField, loadCapacityField, currentLoadField;
    private ListView<Truck> truckListView;
    private ObservableList<Truck> trucks = FXCollections.observableArrayList();
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        databaseConnection dbConn = new databaseConnection() {};
        connection = dbConn.getConnection();

        VBox vbox = new VBox(10);
        
        mvIDField = new TextField();
        mvIDField.setPromptText("Motor Vehicle ID");
        
        weightField = new TextField();
        weightField.setPromptText("Weight");
        
        colorField = new TextField();
        colorField.setPromptText("Color");
        
        modelField = new TextField();
        modelField.setPromptText("Model");
        
        makeField = new TextField();
        makeField.setPromptText("Make");
        
        engineCapacityField = new TextField();
        engineCapacityField.setPromptText("Engine Capacity");
        
        ownerField = new TextField();
        ownerField.setPromptText("Owner");
        
        mileageField = new TextField();
        mileageField.setPromptText("Mileage");
        
        passengerCapacityField = new TextField();
        passengerCapacityField.setPromptText("Passenger Capacity");
        
        numberOfWheelsField = new TextField();
        numberOfWheelsField.setPromptText("Number of Wheels");
        
        towingCapacityField = new TextField();
        towingCapacityField.setPromptText("Towing Capacity");
        
        loadCapacityField = new TextField();
        loadCapacityField.setPromptText("Load Capacity");
        
        currentLoadField = new TextField();
        currentLoadField.setPromptText("Current Load");
        
        truckListView = new ListView<>(trucks);
        truckListView = new ListView<>(trucks);
        truckListView.setOnMouseClicked(event -> populateFields());

        Button addButton = new Button("Add Truck");
        addButton.setOnAction(e -> addTruck());

        Button updateButton = new Button("Update Truck");
        updateButton.setOnAction(e -> updateTruck());

        Button deleteButton = new Button("Delete Truck");
        deleteButton.setOnAction(e -> deleteTruck());

        Button clearButton = new Button("Clear Fields");
        clearButton.setOnAction(e -> clearFields());

        vbox.getChildren().addAll(truckListView, mvIDField, weightField, colorField, modelField, makeField, engineCapacityField, ownerField, mileageField, passengerCapacityField, numberOfWheelsField, towingCapacityField, loadCapacityField, currentLoadField, addButton, updateButton, deleteButton, clearButton);

        Scene scene = new Scene(vbox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Truck CRUD Operations");
        primaryStage.show();

        Truck.loadTrucksFromDatabase(connection, trucks);
    }

    private void addTruck() {
        Truck truck = new Truck(
                Integer.parseInt(weightField.getText()),
                colorField.getText(),
                Integer.parseInt(mvIDField.getText()),
                modelField.getText(),
                makeField.getText(),
                Integer.parseInt(engineCapacityField.getText()),
                ownerField.getText(),
                Integer.parseInt(mileageField.getText()),
                Integer.parseInt(passengerCapacityField.getText()),
                Integer.parseInt(numberOfWheelsField.getText()),
                Integer.parseInt(towingCapacityField.getText()),
                Double.parseDouble(loadCapacityField.getText())
        );
        truck.setCurrentLoad(Double.parseDouble(currentLoadField.getText()));
        trucks.add(truck);
        truck.saveToDatabase(connection);
        clearFields();
    }

    private void updateTruck() {
        Truck selectedTruck = truckListView.getSelectionModel().getSelectedItem();
        if (selectedTruck != null) {
            selectedTruck.setWeight(Integer.parseInt(weightField.getText()));
            selectedTruck.setColor(colorField.getText());
            selectedTruck.setModel(modelField.getText());
            selectedTruck.setMake(makeField.getText());
            selectedTruck.setEngineCapacity(Integer.parseInt(engineCapacityField.getText()));
            selectedTruck.setOwner(ownerField.getText());
            selectedTruck.setMileage(Integer.parseInt(mileageField.getText()));
            selectedTruck.setPassengerCapacity(Integer.parseInt(passengerCapacityField.getText()));
            selectedTruck.setNumberOfWheels(Integer.parseInt(numberOfWheelsField.getText()));
            selectedTruck.setTowingCapacity(Integer.parseInt(towingCapacityField.getText()));
            selectedTruck.setLoadCapacity(Double.parseDouble(loadCapacityField.getText()));
            selectedTruck.setCurrentLoad(Double.parseDouble(currentLoadField.getText()));
            selectedTruck.updateInDatabase(connection);
            truckListView.refresh();
            clearFields();
        }
    }

    private void deleteTruck() {
        Truck selectedTruck = truckListView.getSelectionModel().getSelectedItem();
        if (selectedTruck != null) {
            trucks.remove(selectedTruck);
            selectedTruck.deleteFromDatabase(connection);
            clearFields();
        }
    }

    private void populateFields() {
        Truck selectedTruck = truckListView.getSelectionModel().getSelectedItem();
        if (selectedTruck != null) {
            mvIDField.setText(String.valueOf(selectedTruck.getMvID()));
            weightField.setText(String.valueOf(selectedTruck.getWeight()));
            colorField.setText(selectedTruck.getColor());
            modelField.setText(selectedTruck.getModel());
            makeField.setText(selectedTruck.getMake());
            engineCapacityField.setText(String.valueOf(selectedTruck.getEngineCapacity()));
            ownerField.setText(selectedTruck.getOwner());
            mileageField.setText(String.valueOf(selectedTruck.getMileage()));
            passengerCapacityField.setText(String.valueOf(selectedTruck.getPassengerCapacity()));
            numberOfWheelsField.setText(String.valueOf(selectedTruck.getNumberOfWheels()));
            towingCapacityField.setText(String.valueOf(selectedTruck.getTowingCapacity()));
            loadCapacityField.setText(String.valueOf(selectedTruck.getLoadCapacity()));
            currentLoadField.setText(String.valueOf(selectedTruck.getCurrentLoad()));
        }
    }

    private void clearFields() {
        mvIDField.clear();
        weightField.clear();
        colorField.clear();
        modelField.clear();
        makeField.clear();
        engineCapacityField.clear();
        ownerField.clear();
        mileageField.clear();
        passengerCapacityField.clear();
        numberOfWheelsField.clear();
        towingCapacityField.clear();
        loadCapacityField.clear();
        currentLoadField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
