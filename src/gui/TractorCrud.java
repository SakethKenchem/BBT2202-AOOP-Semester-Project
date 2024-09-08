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
import AOOPClassAssignment1.Tractor;
import AOOPClassAssignment1.databaseConnection;

import java.sql.Connection;

public class TractorCrud extends Application {

    private TextField mvIDField, weightField, colorField, modelField, makeField, engineCapacityField, ownerField, mileageField;
    private CheckBox fourWheelDriveCheckBox, frontLoaderCheckBox;
    private ListView<Tractor> tractorListView;
    private ObservableList<Tractor> tractors = FXCollections.observableArrayList();
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
        
        fourWheelDriveCheckBox = new CheckBox("Four Wheel Drive");
        frontLoaderCheckBox = new CheckBox("Has Front Loader");
        
        tractorListView = new ListView<>(tractors);
        tractorListView.setOnMouseClicked(event -> populateFields());

        Button addButton = new Button("Add Tractor");
        addButton.setOnAction(e -> addTractor());
        
        Button updateButton = new Button("Update Tractor");
        updateButton.setOnAction(e -> updateTractor());
        
        Button deleteButton = new Button("Delete Tractor");
        deleteButton.setOnAction(e -> deleteTractor());
        
        Button clearButton = new Button("Clear Fields");
        clearButton.setOnAction(e -> clearFields());
        
        vbox.getChildren().addAll(tractorListView, mvIDField, weightField, colorField, modelField, makeField, engineCapacityField, ownerField, mileageField, fourWheelDriveCheckBox, frontLoaderCheckBox, addButton, updateButton, deleteButton, clearButton);
        
        Scene scene = new Scene(vbox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tractor CRUD Operations");
        primaryStage.show();

        Tractor.loadTractorsFromDatabase(connection, tractors);
    }

    private void addTractor() {
        Tractor tractor = new Tractor(
            Integer.parseInt(weightField.getText()),
            colorField.getText(),
            Integer.parseInt(mvIDField.getText()),
            modelField.getText(),
            makeField.getText(),
            Integer.parseInt(engineCapacityField.getText()),
            ownerField.getText(),
            Integer.parseInt(mileageField.getText()),
            fourWheelDriveCheckBox.isSelected(),
            frontLoaderCheckBox.isSelected()
        );
        tractors.add(tractor);
        tractor.saveToDatabase(connection);
        clearFields();
    }

    private void updateTractor() {
        Tractor selectedTractor = tractorListView.getSelectionModel().getSelectedItem();
        if (selectedTractor != null) {
            selectedTractor.setWeight(Integer.parseInt(weightField.getText()));
            selectedTractor.setColor(colorField.getText());
            selectedTractor.setModel(modelField.getText());
            selectedTractor.setMake(makeField.getText());
            selectedTractor.setEngineCapacity(Integer.parseInt(engineCapacityField.getText()));
            selectedTractor.setOwner(ownerField.getText());
            selectedTractor.setMileage(Integer.parseInt(mileageField.getText()));
            selectedTractor.setFourWheelDrive(fourWheelDriveCheckBox.isSelected());
            selectedTractor.setHasFrontLoader(frontLoaderCheckBox.isSelected());
            selectedTractor.updateInDatabase(connection);
            tractorListView.refresh();
            clearFields();
        }
    }

    private void deleteTractor() {
        Tractor selectedTractor = tractorListView.getSelectionModel().getSelectedItem();
        if (selectedTractor != null) {
            tractors.remove(selectedTractor);
            selectedTractor.deleteFromDatabase(connection);
            clearFields();
        }
    }

    private void populateFields() {
        Tractor selectedTractor = tractorListView.getSelectionModel().getSelectedItem();
        if (selectedTractor != null) {
            mvIDField.setText(String.valueOf(selectedTractor.getMvID()));
            weightField.setText(String.valueOf(selectedTractor.getWeight()));
            colorField.setText(selectedTractor.getColor());
            modelField.setText(selectedTractor.getModel());
            makeField.setText(selectedTractor.getMake());
            engineCapacityField.setText(String.valueOf(selectedTractor.getEngineCapacity()));
            ownerField.setText(selectedTractor.getOwner());
            mileageField.setText(String.valueOf(selectedTractor.getMileage()));
            fourWheelDriveCheckBox.setSelected(selectedTractor.isFourWheelDrive());
            frontLoaderCheckBox.setSelected(selectedTractor.hasFrontLoader());
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
        fourWheelDriveCheckBox.setSelected(false);
        frontLoaderCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
