package lab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lab.database.DatabaseHandler;
import lab.database.Drivers;

@SuppressWarnings("All")

public class AddDriverController {

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passportNField;

    @FXML
    private TextField passportSField;

    @FXML
    private TextField middlenameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField addressLifeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField postcodeField;

    @FXML
    private TextField companyField;

    @FXML
    private TextField jobnameField;

    @FXML
    private TextArea descriptionField;

    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    void initialize() {
    } //используется как точка загрузки окна

    @FXML
    void addDriver(ActionEvent event) {
        String name = surnameField.getText() + nameField.getText();
        Drivers drivers = new Drivers("202", name, middlenameField.getText(), passportSField.getText(), passportNField.getText(),
                postcodeField.getText(), addressField.getText(), addressLifeField.getText(), companyField.getText(), jobnameField.getText(),
                phoneField.getText(), emailField.getText(), "", descriptionField.getText());

        databaseHandler.addDriver(drivers);

        surnameField.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/lab/fxml/order.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Главное окно");
        stage.setResizable(false);
        stage.getIcons().add(new Image("lab\\assets\\gibdd_logo.png"));
        OrderController orderController = loader.getController();
        orderController.initiatize();
        stage.show();
    }

    @FXML
    void addPhoto(ActionEvent event) {

    }
}
