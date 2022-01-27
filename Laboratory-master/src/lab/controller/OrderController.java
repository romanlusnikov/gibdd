package lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lab.database.DatabaseHandler;
import lab.database.Drivers;

//@SuppressWarnings("All")

public class OrderController {

    @FXML
    private TableView<Drivers> driversTable;

    @FXML
    private TableColumn<Drivers, String > idColumn;

    @FXML
    private TableColumn<Drivers, String > nameColumn;

    @FXML
    private TableColumn<Drivers, String > middlenameColumn;

    @FXML
    private TableColumn<Drivers, String > passportSerialColumn;

    @FXML
    private TableColumn<Drivers, String > passportNumberColumn;

    @FXML
    private TableColumn<Drivers, String > postcodeColumn;

    @FXML
    private TableColumn<Drivers, String > addressColumn;

    @FXML
    private TableColumn<Drivers, String > addressLifeColumn;

    @FXML
    private TableColumn<Drivers, String > companyColumn;

    @FXML
    private TableColumn<Drivers, String > jobnameColumn;

    @FXML
    private TableColumn<Drivers, String > phoneColumn;

    @FXML
    private TableColumn<Drivers, String > emailColumn;

    @FXML
    private TableColumn<Drivers, String > photoColumn;

    @FXML
    private TableColumn<Drivers, String > descriptionColumn;

    @FXML
    private TextField deleteID;

    @FXML
    private Label isDeleteLabel;

    String action;
    private ObservableList<Drivers> DBDriver = FXCollections.observableArrayList();
    private DatabaseHandler databaseHandler = new DatabaseHandler();
    FXMLLoader loader = new FXMLLoader();
    private boolean isDeleting = true;

    @FXML
    void initiatize() {
        updateTable();
    }

    @FXML
    void addDriver(ActionEvent event) {
        action = "Добавление";
        changeScene("/lab/fxml/addDriver.fxml", action);
        AddDriverController addDriverController = loader.getController();
        addDriverController.initialize();
    }

    @FXML
    void editDriver(ActionEvent event) {
        action = "Critical error";
        changeScene("/lab/fxml/error.fxml", action);
    }

    @FXML
    void deleteDriver(ActionEvent event) {
        if (isDeleting) {
            isDeleting = false;
            isDeleteLabel.setVisible(true);
            updateTable();
        } else {
            isDeleteLabel.setVisible(false);
            isDeleting = true;
            try {
                String id = deleteID.getText();
                if (!id.equals("")) {
                    databaseHandler.deleteDriver(id);
                    updateTable();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        middlenameColumn.setCellValueFactory(new PropertyValueFactory<>("middlename"));
        passportSerialColumn.setCellValueFactory(new PropertyValueFactory<>("passportSerial"));
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));
        postcodeColumn.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressLifeColumn.setCellValueFactory(new PropertyValueFactory<>("addressLife"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        jobnameColumn.setCellValueFactory(new PropertyValueFactory<>("jobname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        photoColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        DBDriver = databaseHandler.getDrivers();
        driversTable.setItems(DBDriver);
    }

    public void changeScene(String name, String action) {
        driversTable.getScene().getWindow().hide();

        loader.setLocation(getClass().getResource(name));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("ГИБДД - " + action);
        //stage.setResizable(false);
        stage.getIcons().add(new Image("lab\\assets\\gibdd_logo.png"));
        stage.show();
    }
}
