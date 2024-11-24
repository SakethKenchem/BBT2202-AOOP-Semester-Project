package anotherGUI;

import database.DBConnection;
import database.DBOperations;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AeroplaneApp extends Application {

    DBOperations dbOperations = new DBOperations();

    // Data model class for TableView
    public static class Aeroplane {
        private int id;
        private String brand;
        private String model;
        private int numberOfEngines;

        public Aeroplane(int id, String brand, String model, int numberOfEngines) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.numberOfEngines = numberOfEngines;
        }

        public int getId() {
            return id;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public int getNumberOfEngines() {
            return numberOfEngines;
        }
    }

    @Override
    public void start(Stage primaryStage) {

        // Create UI components
        Label label = new Label("Aeroplane Database Operations");
        label.getStyleClass().add("heading");

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label brandLabel = new Label("Brand:");
        TextField brandField = new TextField();

        Label modelLabel = new Label("Model:");
        TextField modelField = new TextField();

        Label enginesLabel = new Label("Number of Engines:");
        TextField enginesField = new TextField();

        Button insertButton = new Button("Insert Aeroplane");
        Button selectButton = new Button("View Aeroplanes");
        Button updateButton = new Button("Update Aeroplane");
        Button deleteButton = new Button("Delete Aeroplane");

        // TableView for displaying data
        TableView<Aeroplane> tableView = new TableView<>();
        TableColumn<Aeroplane, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Aeroplane, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Aeroplane, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Aeroplane, Integer> enginesColumn = new TableColumn<>("Number of Engines");
        enginesColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfEngines"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(brandColumn);
        tableView.getColumns().add(modelColumn);
        tableView.getColumns().add(enginesColumn);

        // Set button actions
        insertButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String brand = brandField.getText();
                String model = modelField.getText();
                int engines = Integer.parseInt(enginesField.getText());
                dbOperations.insertOperation(id, brand, model, engines);
                refreshTableData(tableView);
            } catch (NumberFormatException ex) {
                showAlert("Invalid input for ID or Number of Engines.");
            }
        });

        selectButton.setOnAction(e -> refreshTableData(tableView));

        updateButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String newBrand = brandField.getText();
                String newModel = modelField.getText();
                int newEngines = Integer.parseInt(enginesField.getText());
                dbOperations.updateOperation(id, newBrand, newModel, newEngines);
                refreshTableData(tableView);
            } catch (NumberFormatException ex) {
                showAlert("Invalid input for ID or Number of Engines.");
            }
        });

        deleteButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                dbOperations.deleteOperation(id);
                refreshTableData(tableView);
            } catch (NumberFormatException ex) {
                showAlert("Invalid input for ID.");
            }
        });

        // Create the VBox layout
        VBox vbox = new VBox(10, label, idLabel, idField, brandLabel, brandField, modelLabel, modelField, enginesLabel, enginesField,
                insertButton, updateButton, deleteButton, selectButton, tableView);
        vbox.setPadding(new Insets(20));
        vbox.getStyleClass().add("vbox");

        // Create the scene and apply CSS
        Scene scene = new Scene(vbox, 600, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Set the stage
        primaryStage.setTitle("Aeroplane Database CRUD Operations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to refresh TableView data
    private void refreshTableData(TableView<Aeroplane> tableView) {
        ObservableList<Aeroplane> aeroplanesList = FXCollections.observableArrayList();
        try {
            ResultSet rs = dbOperations.con.prepareStatement("SELECT * FROM aeroplanes").executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int numberOfEngines = rs.getInt("number_of_engines");
                aeroplanesList.add(new Aeroplane(id, brand, model, numberOfEngines));
            }
            tableView.setItems(aeroplanesList);
        } catch (SQLException e) {
            showAlert("Error fetching data: " + e.getMessage());
        }
    }

    // Method to show error alerts
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
