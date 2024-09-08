package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import AOOPClassAssignment1.Motorcycle;
import AOOPClassAssignment1.databaseConnection;

import java.sql.Connection;

public class MotorcycleCrud extends Application {

    private TextField mvIDField, weightField, colorField, modelField, makeField, engineCapacityField, ownerField, mileageField;
    private CheckBox rideQualityCheckBox, exhaustSoundCheckBox, sidecarCheckBox;
    private ListView<Motorcycle> motorcycleListView;
    private ObservableList<Motorcycle> motorcycles = FXCollections.observableArrayList();
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
        
        rideQualityCheckBox = new CheckBox("Has Nice Ride Quality");
        exhaustSoundCheckBox = new CheckBox("Cool Exhaust Sound");
        sidecarCheckBox = new CheckBox("Has Sidecar");
        
        motorcycleListView = new ListView<>(motorcycles);
        motorcycleListView.setOnMouseClicked(event -> populateFields());

        Button addButton = new Button("Add Motorcycle");
        addButton.setOnAction(e -> addMotorcycle());
        
        Button updateButton = new Button("Update Motorcycle");
        updateButton.setOnAction(e -> updateMotorcycle());
        
        Button deleteButton = new Button("Delete Motorcycle");
        deleteButton.setOnAction(e -> deleteMotorcycle());
        
        Button clearButton = new Button("Clear Fields");
        clearButton.setOnAction(e -> clearFields());
        
        vbox.getChildren().addAll(motorcycleListView, mvIDField, weightField, colorField, modelField, makeField, engineCapacityField, ownerField, mileageField, rideQualityCheckBox, exhaustSoundCheckBox, sidecarCheckBox, addButton, updateButton, deleteButton, clearButton);
        
        Scene scene = new Scene(vbox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Motorcycle CRUD Operations");
        primaryStage.show();

        Motorcycle.loadMotorcyclesFromDatabase(connection, motorcycles);
    }

    private void addMotorcycle() {
        try {
            Motorcycle motorcycle = new Motorcycle(
                    Integer.parseInt(weightField.getText()),
                    colorField.getText(),
                    Integer.parseInt(mvIDField.getText()),
                    modelField.getText(),
                    makeField.getText(),
                    Integer.parseInt(engineCapacityField.getText()),
                    ownerField.getText(),
                    Integer.parseInt(mileageField.getText()),
                    rideQualityCheckBox.isSelected(),
                    exhaustSoundCheckBox.isSelected(),
                    sidecarCheckBox.isSelected()
            );
            motorcycles.add(motorcycle);
            motorcycle.saveToDatabase(connection);
            System.out.println("Motorcycle added successfully.");
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMotorcycle() {
        try {
            Motorcycle selectedMotorcycle = motorcycleListView.getSelectionModel().getSelectedItem();
            if (selectedMotorcycle != null) {
                System.out.println("Selected motorcycle ID: " + selectedMotorcycle.getMvID());

                // Printing current and new values 
                System.out.println("Current Weight: " + selectedMotorcycle.getWeight() + ", New Weight: " + weightField.getText());
                System.out.println("Current Color: " + selectedMotorcycle.getColor() + ", New Color: " + colorField.getText());
                System.out.println("Current Model: " + selectedMotorcycle.getModel() + ", New Model: " + modelField.getText());
                System.out.println("Current Make: " + selectedMotorcycle.getMake() + ", New Make: " + makeField.getText());
                System.out.println("Current Engine Capacity: " + selectedMotorcycle.getEngineCapacity() + ", New Engine Capacity: " + engineCapacityField.getText());
                System.out.println("Current Owner: " + selectedMotorcycle.getOwner() + ", New Owner: " + ownerField.getText());
                System.out.println("Current Mileage: " + selectedMotorcycle.getMileage() + ", New Mileage: " + mileageField.getText());
                System.out.println("Current Nice Ride Quality: " + selectedMotorcycle.hasNiceRideQuality() + ", New Nice Ride Quality: " + rideQualityCheckBox.isSelected());
                System.out.println("Current Cool Exhaust Sound: " + selectedMotorcycle.hasCoolExhaustSound() + ", New Cool Exhaust Sound: " + exhaustSoundCheckBox.isSelected());
                System.out.println("Current Has Sidecar: " + selectedMotorcycle.hasSidecar() + ", New Has Sidecar: " + sidecarCheckBox.isSelected());

                selectedMotorcycle.setWeight(Integer.parseInt(weightField.getText()));
                selectedMotorcycle.setColor(colorField.getText());
                selectedMotorcycle.setModel(modelField.getText());
                selectedMotorcycle.setMake(makeField.getText());
                selectedMotorcycle.setEngineCapacity(Integer.parseInt(engineCapacityField.getText()));
                selectedMotorcycle.setOwner(ownerField.getText());
                selectedMotorcycle.setMileage(Integer.parseInt(mileageField.getText()));
                selectedMotorcycle.setHasNiceRideQuality(rideQualityCheckBox.isSelected());
                selectedMotorcycle.setCoolExhaustSound(exhaustSoundCheckBox.isSelected());
                selectedMotorcycle.setHasSidecar(sidecarCheckBox.isSelected());

                selectedMotorcycle.updateInDatabase(connection);
                motorcycleListView.refresh(); // Refresh the list view to reflect changes

                System.out.println("Motorcycle updated successfully.");
                clearFields();
            } else {
                System.out.println("No motorcycle selected for update.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void deleteMotorcycle() {
        try {
            Motorcycle selectedMotorcycle = motorcycleListView.getSelectionModel().getSelectedItem();
            if (selectedMotorcycle != null) {
                motorcycles.remove(selectedMotorcycle);
                selectedMotorcycle.deleteFromDatabase(connection);
                System.out.println("Motorcycle deleted successfully.");
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateFields() {
        Motorcycle selectedMotorcycle = motorcycleListView.getSelectionModel().getSelectedItem();
        if (selectedMotorcycle != null) {
            mvIDField.setText(String.valueOf(selectedMotorcycle.getMvID()));
            weightField.setText(String.valueOf(selectedMotorcycle.getWeight()));
            colorField.setText(selectedMotorcycle.getColor());
            modelField.setText(selectedMotorcycle.getModel());
            makeField.setText(selectedMotorcycle.getMake());
            engineCapacityField.setText(String.valueOf(selectedMotorcycle.getEngineCapacity()));
            ownerField.setText(selectedMotorcycle.getOwner());
            mileageField.setText(String.valueOf(selectedMotorcycle.getMileage()));
            rideQualityCheckBox.setSelected(selectedMotorcycle.hasNiceRideQuality());
            exhaustSoundCheckBox.setSelected(selectedMotorcycle.hasCoolExhaustSound());
            sidecarCheckBox.setSelected(selectedMotorcycle.hasSidecar());
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
        rideQualityCheckBox.setSelected(false);
        exhaustSoundCheckBox.setSelected(false);
        sidecarCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
