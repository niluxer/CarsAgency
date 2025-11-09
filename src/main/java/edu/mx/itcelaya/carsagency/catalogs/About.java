package edu.mx.itcelaya.carsagency.catalogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class About implements Initializable {

    @FXML
    Button btnAboutClose;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAboutClose.setOnAction(handlerButton);
    }

    EventHandler<ActionEvent> handlerButton = (event)->{
        ((Node)(event.getSource())).getScene().getWindow().hide();

        System.out.println("Closing...");
    };
}
