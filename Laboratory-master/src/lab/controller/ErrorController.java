package lab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ErrorController {

    @FXML
    private Button exitBtn;

    @FXML
    void exit(ActionEvent event) {
        exitBtn.getScene().getWindow().hide();

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
        stage.setTitle("Laboratory #20 - Лаборант");
        stage.setResizable(false);
        stage.getIcons().add(new Image("lab\\assets\\logo.png"));
        OrderController orderController = loader.getController();
        orderController.initiatize();
        stage.showAndWait();
    }
}
