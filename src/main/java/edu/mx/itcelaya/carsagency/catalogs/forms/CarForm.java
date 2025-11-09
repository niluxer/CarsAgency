package edu.mx.itcelaya.carsagency.catalogs.forms;

import edu.mx.itcelaya.carsagency.CarsAgencyApplication;
import edu.mx.itcelaya.carsagency.customcontrols.ImageComboBox;
import edu.mx.itcelaya.carsagency.database.dao.CarDao;
import edu.mx.itcelaya.carsagency.enums.BrakesType;
import edu.mx.itcelaya.carsagency.enums.TransmissionType;
import edu.mx.itcelaya.carsagency.models.Brand;
import edu.mx.itcelaya.carsagency.models.Car;
import edu.mx.itcelaya.carsagency.models.Engine;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class CarForm implements Initializable {
    @FXML
    ComboBox<TransmissionType> cmbTransmission;
    @FXML
    ComboBox<BrakesType> cmbBrakes;
    @FXML
    ComboBox<Brand> cmbBrand;
    @FXML
    ComboBox<Engine> cmbEngine;
    @FXML
    public Button btnSave;
    @FXML
    Button btnCancel;
    @FXML
    TextField tfYear, tfDoors, tfModel, tfColor, tfMileage, tfPrice;
    @FXML
    private ImageComboBox cmbImage;
    CarDao carDao = new CarDao();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbTransmission.setItems(FXCollections.observableArrayList(TransmissionType.values()));
        cmbBrakes.setItems(FXCollections.observableArrayList(BrakesType.values()));
        cmbBrand.setItems(FXCollections.observableArrayList(carDao.getBrands()));
        cmbEngine.setItems(FXCollections.observableArrayList(carDao.getEngines()));
        List<String> imageFilenames = List.of(
                CarsAgencyApplication.class.getResource("images/car1.jpg").toString(),
                CarsAgencyApplication.class.getResource("images/car2.jpg").toString(),
                CarsAgencyApplication.class.getResource("images/car3.jpg").toString(),
                CarsAgencyApplication.class.getResource("images/car4.jpg").toString()
        );

        //cmbImage.getItems().setAll(images);
        cmbImage.getItems().clear();
        for (String filename : imageFilenames) {
            cmbImage.addImage(filename);
        }
        btnSave.setOnAction(event -> {
            Car car = new Car();
            car.setYear(Integer.parseInt(tfYear.getText()));
            car.setModel(tfModel.getText());
            car.setColor(tfColor.getText());
            car.setPrice(Integer.parseInt(tfPrice.getText()));
            car.setMileage(Integer.parseInt(tfMileage.getText()));
            car.setDoors(Integer.parseInt(tfDoors.getText()));
            car.setBrakesType(cmbBrakes.getSelectionModel().getSelectedItem());
            car.setTransmissionType(cmbTransmission.getSelectionModel().getSelectedItem());
            car.setBrand(cmbBrand.getSelectionModel().getSelectedItem());
            car.setEngine(cmbEngine.getSelectionModel().getSelectedItem());
            System.out.println("Selected: " + cmbImage.getSelectedImageFilename());
            Path p = Paths.get(cmbImage.getSelectedImageFilename().substring(6));
            String file = p.getFileName().toString();
            car.setImage(file);
            //carList.add(car);
            //observableCarList.add(car);
            if (carDao.save(car))
                System.out.println("Car saved");

        });

    }
}
