package RMI2;

import AOOPClassAssignment1.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import static javafx.application.Application.launch;

public class VehicleManagementGUI extends Application {
    private DatabaseInterface2 dbInterface;

    // Table to display all vehicles
    private TableView<Vehicle> vehicleTable;
    private ObservableList<Vehicle> vehicleData;

    @Override
    public void start(Stage primaryStage) {
        // Connect to the RMI server
        try {
            Registry registry = LocateRegistry.getRegistry("10.35.1.30", 1099);
            dbInterface = (DatabaseInterface2) registry.lookup("Test");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Could not connect to the RMI server.", e);
        }

        // Root layout for the GUI
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        // Tabs for each vehicle type
        TabPane vehicleTabPane = new TabPane();
        vehicleTabPane.getTabs().add(createMotorcycleTab());
        vehicleTabPane.getTabs().add(createTractorTab());
        vehicleTabPane.getTabs().add(createTruckTab());

        // Create TableView to display vehicles
        vehicleData = FXCollections.observableArrayList();
        vehicleTable = new TableView<>(vehicleData);

        TableColumn<Vehicle, Integer> mvIDCol = new TableColumn<>("MV ID");
        TableColumn<Vehicle, String> makeCol = new TableColumn<>("Make");
        TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
        TableColumn<Vehicle, String> colourCol = new TableColumn<>("Colour");
        TableColumn<Vehicle, Integer> engineCapacityCol = new TableColumn<>("Engine Capacity");
        TableColumn<Vehicle, String> ownerCol = new TableColumn<>("Owner");
        TableColumn<Vehicle, Integer> mileageCol = new TableColumn<>("Mileage");

        mvIDCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getMvID()));
        makeCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getMake()));
        modelCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getModel()));
        engineCapacityCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getEngineCapacity()));
        colourCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getColor()));
        ownerCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getOwner()));
        mileageCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getMileage()));

        vehicleTable.getColumns().addAll(mvIDCol, makeCol, modelCol, colourCol,engineCapacityCol, ownerCol, mileageCol);

        // Add Refresh Button
        Button refreshButton = new Button("Refresh Vehicles");
        refreshButton.setOnAction(e -> loadAllVehicles());

        // Controls for updating vehicle data
        TextField updateIDField = new TextField();
        updateIDField.setPromptText("Vehicle ID");
        TextField newOwnerField = new TextField();
        newOwnerField.setPromptText("New Owner");
        TextField newColorField = new TextField();
        newColorField.setPromptText("New Color");
        Button updateBtn = new Button("Update Vehicle");

        updateBtn.setOnAction(e -> updateVehicle(updateIDField, newOwnerField, newColorField));

        // Controls for deleting vehicle data
        TextField deleteIDField = new TextField();
        deleteIDField.setPromptText("Vehicle ID to delete");
        Button deleteBtn = new Button("Delete Vehicle");

        deleteBtn.setOnAction(e -> deleteVehicle(deleteIDField));

        HBox updateDeleteBox = new HBox(15, updateIDField, newOwnerField, newColorField, updateBtn, deleteIDField, deleteBtn);

        // Add everything to root layout
        root.getChildren().addAll(vehicleTabPane, vehicleTable, refreshButton, updateDeleteBox);

        // Set up the scene
        Scene scene = new Scene(root, 1350, 700);
        // Apply the stylesheet
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Vehicle Management System");
        primaryStage.show();
    }

    // Load all vehicles from the server
    private void loadAllVehicles() {
        try {
            List<Vehicle> vehicles = dbInterface.selectOperation();
            vehicleData.setAll(vehicles);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error loading vehicles from server.", e);
        }
    }

    // Update vehicle info
    private void updateVehicle(TextField idField, TextField ownerField, TextField colorField) {
        try {
            int id = Integer.parseInt(idField.getText());
            String newOwner = ownerField.getText();
            String newColor = colorField.getText();
            dbInterface.updateOperation(id, newOwner, newColor);
            loadAllVehicles();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error updating vehicle.", e);
        }
    }

    // Delete vehicle by ID
    private void deleteVehicle(TextField idField) {
        try {
            int id = Integer.parseInt(idField.getText());
            dbInterface.deleteOperation(id);
            loadAllVehicles();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error deleting vehicle.", e);
        }
    }

    // Show error message via Alert
    private void showError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
        e.printStackTrace();
    }

    // Tab for adding Motorcycles
    private Tab createMotorcycleTab() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        // Fields for Motorcycle attributes
        TextField mvIDField = new TextField();
        TextField weightField = new TextField();
        TextField colorField = new TextField();
        TextField modelField = new TextField();
        TextField makeField = new TextField();
        TextField engineCapacityField = new TextField();
        TextField ownerField = new TextField();
        TextField mileageField = new TextField();

        CheckBox niceRideQualityField = new CheckBox();
        CheckBox coolExhaustSoundField = new CheckBox();
        CheckBox hasSidecarField = new CheckBox();

        form.add(new Label("MV ID"), 0, 0); 
        form.add(mvIDField, 1, 0);
        form.add(new Label("Weight"), 0, 1); 
        form.add(weightField, 1, 1);
        form.add(new Label("Color"), 0, 2); 
        form.add(colorField, 1, 2);
        form.add(new Label("Model"), 0, 3); 
        form.add(modelField, 1, 3);
        form.add(new Label("Make"), 0, 4); 
        form.add(makeField, 1, 4);
        form.add(new Label("Engine Capacity"), 0, 5); 
        form.add(engineCapacityField, 1, 5);
        form.add(new Label("Owner"), 0, 6); 
        form.add(ownerField, 1, 6);
        form.add(new Label("Mileage"), 0, 7); 
        form.add(mileageField, 1, 7);
        form.add(new Label("Nice Ride Quality"), 0, 8); 
        form.add(niceRideQualityField, 1, 8);
        form.add(new Label("Cool Exhaust Sound"), 0, 9); 
        form.add(coolExhaustSoundField, 1, 9);
        form.add(new Label("Sidecar"), 0, 10); 
        form.add(hasSidecarField, 1, 10);

        Button addMotorcycleBtn = new Button("Add Motorcycle");
        addMotorcycleBtn.setOnAction(event -> {
            try {
                int mvID = Integer.parseInt(mvIDField.getText());
                int weight = Integer.parseInt(weightField.getText());
                String color = colorField.getText();
                String model = modelField.getText();
                String make = makeField.getText();
                int engineCapacity = Integer.parseInt(engineCapacityField.getText());
                String owner = ownerField.getText();
                int mileage = Integer.parseInt(mileageField.getText());
                boolean niceRideQuality = niceRideQualityField.isSelected();
                boolean coolExhaustSound = coolExhaustSoundField.isSelected();
                boolean hasSidecar = hasSidecarField.isSelected();

                Motorcycle motorcycle = new Motorcycle(weight, color, mvID, model, make, engineCapacity, owner, mileage, niceRideQuality, coolExhaustSound, hasSidecar);
                dbInterface.insertOperation(motorcycle);
                loadAllVehicles();
                
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error adding motorcycle.", e);
            }
        });

        VBox formContainer = new VBox(10, form, addMotorcycleBtn);
        scrollPane.setContent(formContainer);
        return new Tab("Motorcycle", scrollPane);
    }

    // Tab for adding Tractors
    private Tab createTractorTab() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        // Fields for Tractor attributes
        TextField mvIDField = new TextField();
        TextField weightField = new TextField();
        TextField colorField = new TextField();
        TextField modelField = new TextField();
        TextField makeField = new TextField();
        TextField engineCapacityField = new TextField();
        TextField ownerField = new TextField();
        TextField mileageField = new TextField();

        CheckBox fourWheelDriveField = new CheckBox();
        CheckBox frontLoaderField = new CheckBox();

        form.add(new Label("MV ID"), 0, 0); 
        form.add(mvIDField, 1, 0);
        form.add(new Label("Weight"), 0, 1); 
        form.add(weightField, 1, 1);
        form.add(new Label("Color"), 0, 2); 
        form.add(colorField, 1, 2);
        form.add(new Label("Model"), 0, 3); 
        form.add(modelField, 1, 3);
        form.add(new Label("Make"), 0, 4); 
        form.add(makeField, 1, 4);
        form.add(new Label("Engine Capacity"), 0, 5); 
        form.add(engineCapacityField, 1, 5);
        form.add(new Label("Owner"), 0, 6); 
        form.add(ownerField, 1, 6);
        form.add(new Label("Mileage"), 0, 7); 
        form.add(mileageField, 1, 7);
        form.add(new Label("Four Wheel Drive"), 0, 8); 
        form.add(fourWheelDriveField, 1, 8);
        form.add(new Label("Front Loader"), 0, 9); 
        form.add(frontLoaderField, 1, 9);

        Button addTractorBtn = new Button("Add Tractor");
        addTractorBtn.setOnAction(event -> {
            try {
                int mvID = Integer.parseInt(mvIDField.getText());
                int weight = Integer.parseInt(weightField.getText());
                String color = colorField.getText();
                String model = modelField.getText();
                String make = makeField.getText();
                int engineCapacity = Integer.parseInt(engineCapacityField.getText());
                String owner = ownerField.getText();
                int mileage = Integer.parseInt(mileageField.getText());
                boolean isFourWheelDrive = fourWheelDriveField.isSelected();
                boolean hasFrontLoader = frontLoaderField.isSelected();

                Tractor tractor = new Tractor(weight, color, mvID, model, make, engineCapacity, owner, mileage, isFourWheelDrive, hasFrontLoader);
                dbInterface.insertOperation(tractor);
                loadAllVehicles();
                
                
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error adding tractor.", e);
            }
        });

        VBox formContainer = new VBox(10, form, addTractorBtn);
        scrollPane.setContent(formContainer);
        return new Tab("Tractor", scrollPane);
    }

    // Tab for adding Trucks
    private Tab createTruckTab() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        // Fields for Truck attributes
        TextField mvIDField = new TextField();
        TextField weightField = new TextField();
        TextField colorField = new TextField();
        TextField modelField = new TextField();
        TextField makeField = new TextField();
        TextField engineCapacityField = new TextField();
        TextField ownerField = new TextField();
        TextField mileageField = new TextField();

        TextField passengerCapacityField = new TextField();
        TextField numberOfWheelsField = new TextField();
        TextField towingCapacityField = new TextField();
        TextField loadCapacityField = new TextField();

        form.add(new Label("MV ID"), 0, 0); 
        form.add(mvIDField, 1, 0);
        form.add(new Label("Weight"), 0, 1); 
        form.add(weightField, 1, 1);
        form.add(new Label("Color"), 0, 2); 
        form.add(colorField, 1, 2);
        form.add(new Label("Model"), 0, 3); 
        form.add(modelField, 1, 3);
        form.add(new Label("Make"), 0, 4); 
        form.add(makeField, 1, 4);
        form.add(new Label("Engine Capacity"), 0, 5); 
        form.add(engineCapacityField, 1, 5);
        form.add(new Label("Owner"), 0, 6); 
        form.add(ownerField, 1, 6);
        form.add(new Label("Mileage"), 0, 7); 
        form.add(mileageField, 1, 7);
        form.add(new Label("Passenger Capacity"), 0, 8); 
        form.add(passengerCapacityField, 1, 8);
        form.add(new Label("Number of Wheels"), 0, 9); 
        form.add(numberOfWheelsField, 1, 9);
        form.add(new Label("Towing Capacity"), 0, 10); 
        form.add(towingCapacityField, 1, 10);
        form.add(new Label("Load Capacity"), 0, 11); 
        form.add(loadCapacityField, 1, 11);

        Button addTruckBtn = new Button("Add Truck");
        addTruckBtn.setOnAction(event -> {
            try {
                int mvID = Integer.parseInt(mvIDField.getText());
                int weight = Integer.parseInt(weightField.getText());
                String color = colorField.getText();
                String model = modelField.getText();
                String make = makeField.getText();
                int engineCapacity = Integer.parseInt(engineCapacityField.getText());
                String owner = ownerField.getText();
                int mileage = Integer.parseInt(mileageField.getText());
                int passengerCapacity = Integer.parseInt(passengerCapacityField.getText());
                int numberOfWheels = Integer.parseInt(numberOfWheelsField.getText());
                int towingCapacity = Integer.parseInt(towingCapacityField.getText());
                double loadCapacity = Double.parseDouble(loadCapacityField.getText());

                Truck truck = new Truck(weight, color, mvID, model, make, engineCapacity, owner, mileage, passengerCapacity, numberOfWheels, towingCapacity, loadCapacity);
                dbInterface.insertOperation(truck);
                loadAllVehicles();
                
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error adding truck.", e);
            }
        });

        VBox formContainer = new VBox(10, form, addTruckBtn);
        scrollPane.setContent(formContainer);
        return new Tab("Truck", scrollPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

