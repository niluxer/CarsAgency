package edu.mx.itcelaya.carsagency;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private MenuItem mnuCars, mnuBrands, mnuTransmission, mnuEngines, mnuAbout;
    @FXML
    BorderPane mainContainer;

    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mnuCars.setOnAction(handlerMenus);
        mnuBrands.setOnAction(handlerMenus);
        mnuTransmission.setOnAction(handlerMenus);
        mnuEngines.setOnAction(handlerMenus);
        mnuAbout.setOnAction(handlerMenus);
    }

    EventHandler<ActionEvent> handlerMenus = event -> {
            if (event.getSource() == mnuCars) {
                mainContainer.setCenter(loadCatalog("cars-view.fxml"));
            } else if (event.getSource() == mnuBrands) {
                mainContainer.setCenter(loadCatalog("catalog1.fxml"));
            } else if (event.getSource() == mnuTransmission) {
                mainContainer.setCenter(loadCatalog("catalog2.fxml"));
            } else if (event.getSource() == mnuEngines) {
                mainContainer.setCenter(loadCatalog("catalog3.fxml"));
            } else if (event.getSource() == mnuAbout) {
                showAboutStage();
            }
    };

    private Parent loadCatalog(String fxml)
    {
        /*HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label label = new Label("Cars catalog");
        hBox.getChildren().add(label);
        return hBox;*/
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/mx/itcelaya/carsagency/catalogs/cars-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader( CarsAgencyApplication.class.getResource("catalogs/" + fxml));
        try {
            Parent root = fxmlLoader.load();
            return root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HBox loadCatalog2()
    {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label label = new Label("Brands Catalog");
        hBox.getChildren().add(label);
        return hBox;
    }

    private void showAboutStage()
    {
        Stage aboutStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalogs/about.fxml"));

        /*EmployeeController employeeController = new EmployeeController();
        employeeController.setEmployee(employee);

        loader.setController(employeeController);*/

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 500, 400);

        aboutStage.setScene(scene);
        aboutStage.setTitle("About");
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setResizable(false);
        aboutStage.showAndWait();

    }

}