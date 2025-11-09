package edu.mx.itcelaya.carsagency.catalogs;

import edu.mx.itcelaya.carsagency.CarsAgencyApplication;
import edu.mx.itcelaya.carsagency.catalogs.forms.CarForm;
import edu.mx.itcelaya.carsagency.customcontrols.ImageComboBox;
import edu.mx.itcelaya.carsagency.database.dao.CarDao;
import edu.mx.itcelaya.carsagency.enums.BrakesType;
import edu.mx.itcelaya.carsagency.enums.TransmissionType;
import edu.mx.itcelaya.carsagency.models.Brand;
import edu.mx.itcelaya.carsagency.models.Car;
import edu.mx.itcelaya.carsagency.models.Engine;
import edu.mx.itcelaya.carsagency.services.cars.LoadCarsService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class CarsController implements Initializable {

    //private List<Car> carList = new ArrayList<>();
    private ObservableList<Car> observableCarList = FXCollections.observableArrayList();
    @FXML
    TableView<Car> tblCars;
    @FXML
    Text txtCarDetailId, txtCarDetailBrand, txtCarDetailModel, txtCarDetailEngine, txtCarDetailTransmission, txtCarDetailPrice, txtCarDetailMileage, txtCarDetailDoors, txtCarDetailBreaks, txtCarDetailYear;
    @FXML
    ImageView imgCarDetail;
    @FXML
    VBox formContainer;
    @FXML
    BorderPane carsViewContainer;
    //CarForm carForm;
    @FXML
    ComboBox<TransmissionType> cmbTransmission;
    @FXML
    ComboBox<BrakesType> cmbBrakes;
    @FXML
    ComboBox<Brand> cmbBrand;
    @FXML
    ComboBox<Engine> cmbEngine;
    @FXML
    private Button btnSave, btnEdit, btnDelete, btnReset, btnClear;
    @FXML
    Button btnCancel;
    @FXML
    TextField tfYear, tfDoors, tfModel, tfColor, tfMileage, tfPrice;
    @FXML
    private ImageComboBox cmbImage;
    CarDao carDao = new CarDao();
    private boolean editMode = false;
    private int selectedCarIndex = 0;
    List<String> imageFilenames = List.of("car1.jpg", "car2.jpg", "car3.jpg", "car4.jpg", "car5.jpg", "car6.jpg", "car7.jpg", "car8.jpg","car9.jpg", "car10.jpg","accord.jpg", "camry.jpg", "cclass.jpg", "civic.jpg", "corolla.jpg", "eqc.jpg", "explorer.jpg", "f150.jpg", "gle.jpg", "m3.jpg", "mustang.jpg", "pilot.jpg", "rav4.jpg", "x3.jpg", "x5.jpg");
    //Button btnSave = new Button("Save"), btnCancel = new Button("Cancel");
    //Car car = new Car();
    //Form carForm;
    private final LoadCarsService loadCarsService = new LoadCarsService();
    @FXML
    private ProgressIndicator loadingIndicator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initCars();
        initCarsTableView();
        initForm();
        btnSave.setOnAction(handlerButtons);
        btnEdit.setOnAction(handlerButtons);
        btnDelete.setOnAction(handlerButtons);
        btnReset.setOnAction(handlerButtons);
        btnClear.setOnAction(handlerButtons);
        btnEdit.disableProperty().bind(Bindings.size(tblCars.getSelectionModel().getSelectedItems()).isEqualTo(0));
        btnDelete.disableProperty().bind(Bindings.size(tblCars.getSelectionModel().getSelectedItems()).isEqualTo(0));
        btnClear.disableProperty().bind(Bindings.size(tblCars.getSelectionModel().getSelectedItems()).isEqualTo(0));

    }

    private void initForm()
    {
        /*List<Image> images = List.of(
                new Image(CarsAgencyApplication.class.getResource("images/car1.jpg").toString()),
                new Image(CarsAgencyApplication.class.getResource("images/car2.jpg").toString()),
                new Image(CarsAgencyApplication.class.getResource("images/car3.jpg").toString()),
                new Image(CarsAgencyApplication.class.getResource("images/car4.jpg").toString())
        );*/
        cmbTransmission.setItems(FXCollections.observableArrayList(TransmissionType.values()));
        cmbBrakes.setItems(FXCollections.observableArrayList(BrakesType.values()));
        cmbBrand.setItems(FXCollections.observableArrayList(carDao.getBrands()));
        cmbEngine.setItems(FXCollections.observableArrayList(carDao.getEngines()));

        //cmbImage.getItems().setAll(images);
        cmbImage.getItems().clear();
        for (String filename : imageFilenames) {
            cmbImage.addImage(filename);
        }
        /*carForm = generateCarForm();
        formContainer.getChildren().addAll(new FormRenderer(carForm), btnSave, btnCancel);
        btnSave.setOnAction(event -> {
            carForm.persist();
            System.out.println(car.getModel());
            System.out.println(car.getYear());
            System.out.println(car.getDoors());
        });*/
    }

    private void initCarsTableView()
    {
        loadingIndicator.visibleProperty().bind(loadCarsService.runningProperty());
        tblCars.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1)
            {
                Car car = tblCars.getSelectionModel().getSelectedItem();
                txtCarDetailId.setText(String.valueOf(car.getId()));
                //txtCarDetailBrand.setText(car.getBrand().getName());
                txtCarDetailBrand.setText(car.getBrand_name());
                txtCarDetailModel.setText(car.getModel());
                txtCarDetailDoors.setText(car.getDoors() + "");
                txtCarDetailMileage.setText(car.getMileage() + "");
                //txtCarDetailEngine.setText(car.getEngine().getName());
                txtCarDetailEngine.setText(car.getEngine_name());
                txtCarDetailBreaks.setText(car.getBrakesType().toString());
                txtCarDetailTransmission.setText(car.getTransmissionType().toString());
                txtCarDetailPrice.setText(car.getPrice() + "");
                txtCarDetailYear.setText(car.getYear() + "");
                //System.out.println(car.getImage());
                //Image image = new Image(CarsAgencyApplication.class.getResource("images/" + car.getImage()).toString());
                imgCarDetail.setImage(
                        new Image(CarsAgencyApplication.class.getResource(
                                "images/" + car.getImage()).toString())
                );
                //imgCarDetail.setImage(image);
                imgCarDetail.setFitHeight(390);
                imgCarDetail.setFitWidth(400);
                //btnEdit.setDisable(false);
                //btnDelete.setDisable(false);

            }
        });
        loadCarsService.setOnSucceeded(event -> {
            tblCars.getItems().clear();
            for (Car car : loadCarsService.getValue()) {
                tblCars.getItems().add(car);
            }
        });

        loadCarsService.setOnFailed(event -> {
            showError(loadCarsService.getException());
        });
        loadCars();
        //observableCarList = FXCollections.observableArrayList(carDao.findAll());
        //tblCars.setItems(observableCarList);
    }

    private void loadCars() {
        if (loadCarsService.isRunning()) return;
        loadCarsService.reset();
        loadCarsService.start();
    }

    private void showError(Throwable e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        alert.showAndWait();
    }

    private EventHandler<ActionEvent> handlerButtons = (ActionEvent event) -> {
        if (event.getSource() == btnSave)
        {
            saveCar();
        } else if (event.getSource() == btnEdit)
        {
            editMode = true;
            loadCar();
        } else if (event.getSource() == btnDelete) {
            deleteCar();
        } else if(event.getSource() == btnReset)
        {
            resetForm();
        } else if(event.getSource() == btnClear)
        {
            clearCarDetails();
            tblCars.getSelectionModel().clearSelection();
        }
    };

    private void loadCar()
    {
        Car car = tblCars.getSelectionModel().getSelectedItem();
        selectedCarIndex = tblCars.getSelectionModel().getSelectedIndex();
        tfYear.setText(String.valueOf(car.getYear()));
        tfModel.setText(car.getModel());
        tfColor.setText(car.getColor());
        tfMileage.setText(car.getMileage() + "");
        tfPrice.setText(car.getPrice() + "");
        tfDoors.setText(car.getDoors() + "");
        cmbBrand.setValue(car.getBrand());
        cmbEngine.setValue(car.getEngine());
        cmbBrakes.setValue(car.getBrakesType());
        cmbTransmission.setValue(car.getTransmissionType());
        //cmbImage.setSelectedImage(CarsAgencyApplication.class.getResource("images/" + car.getImage()).toString());
        cmbImage.setSelectedImage(car.getImage());
        tfYear.requestFocus();

    }

    private void saveCar()
    {
        Car car = (editMode) ? tblCars.getSelectionModel().getSelectedItem() : new Car();
        car.setYear(Integer.parseInt(tfYear.getText()));
        car.setModel(tfModel.getText());
        car.setColor(tfColor.getText());
        car.setPrice(Double.parseDouble(tfPrice.getText()));
        car.setMileage(Integer.parseInt(tfMileage.getText()));
        car.setDoors(Integer.parseInt(tfDoors.getText()));
        car.setBrakesType(cmbBrakes.getSelectionModel().getSelectedItem());
        car.setTransmissionType(cmbTransmission.getSelectionModel().getSelectedItem());
        car.setBrand(cmbBrand.getSelectionModel().getSelectedItem());
        car.setEngine(cmbEngine.getSelectionModel().getSelectedItem());
        //System.out.println("Selected: " + cmbImage.getSelectedImageFilename());
        //Path p = Paths.get(cmbImage.getSelectedImageFilename().substring(6));
        //String file = p.getFileName().toString();
        //car.setImage(file);
        car.setImage(cmbImage.getSelectedImageFilename());

        if (editMode)
        {
            carDao.update(car);
            observableCarList.set(selectedCarIndex, car);
            System.out.println("Car updated");
        }
        else {
            carDao.save(car);
            observableCarList.add(car);
            System.out.println("Car inserted");
        }
        clearCarDetails();
        tblCars.getSelectionModel().clearSelection();
        resetForm();
        editMode = false;
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        selectedCarIndex = -1;

    }

    private void deleteCar()
    {
        carDao.delete(tblCars.getSelectionModel().getSelectedItem().getId());
        observableCarList.remove(tblCars.getSelectionModel().getSelectedItem());
        clearCarDetails();
        tblCars.getSelectionModel().clearSelection();
        resetForm();
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        selectedCarIndex = -1;
    }

    private void clearCarDetails()
    {
        txtCarDetailYear.setText("");
        txtCarDetailModel.setText("");
        txtCarDetailBrand.setText("");
        txtCarDetailEngine.setText("");
        txtCarDetailMileage.setText("");
        txtCarDetailDoors.setText("");
        imgCarDetail.setImage(null);
        txtCarDetailId.setText("");
        txtCarDetailPrice.setText("");
        txtCarDetailBreaks.setText("");
        txtCarDetailTransmission.setText("");
    }

    private void resetForm()
    {
        tfYear.setText("");
        tfModel.setText("");
        tfColor.setText("");
        tfMileage.setText("");
        tfDoors.setText("");
        tfPrice.setText("");
        cmbTransmission.setValue(null);
        cmbBrand.setValue(null);
        cmbEngine.setValue(null);
        cmbBrakes.setValue(null);
        cmbImage.setValue(null);
    }

    /*private Form generateCarForm()
    {

        Form form = Form.of(
                Group.of(
                        Field.ofIntegerType(car.getId()).label("ID").editable(false),
                        Field.ofIntegerType(car.getYear()).label("Year"),
                        Field.ofStringType(car.getModel()).label("Model"),
                        Field.ofStringType(car.getColor()).label("Color"),
                        Field.ofDoubleType(car.getPrice()).label("Price"),
                        Field.ofIntegerType(car.getMileage()).label("Mileage"),
                        Field.ofIntegerType(car.getDoors()).label("Doors"),
                        //Field.ofSingleSelectionType(Brand.values(), car.getBrand()).label("Brand"),
                        //Field.ofSingleSelectionType(BrakesType.values(), car.getBrakesType()).label("Brakes Type"),
                        //Field.ofSingleSelectionType(TransmissionType.values(), car.getTransmissionType()).label("Transmission Type"),
                        Field.ofStringType(car.getImage()).label("Image URL")
                )
        );
        return form;
    }*/

    private void initCars()
    {

        /*Brand brand1 = new Brand(1, "Hyundai");
        Engine engine1 = new Engine(1, "Disel", "shape1");
        carList.add(
                new Car(1, 2020,
                        "Santa Fe", "Gray", 500000, 50000, 5
                        , brand1, BrakesType.DISC, TransmissionType.AUTOMATIC, engine1, "car1.jpg"));

         */
        //carList = generateRandomCars(30);
    }

    public static List<Car> generateRandomCars(int count) {
        List<Car> carList = new ArrayList<>();
        Random random = new Random();

        String[] colors = {"Red", "Blue", "Black", "White", "Gray", "Green"};
        String[] models = {"Sedan", "SUV", "Hatchback", "Coupe", "Convertible"};
        String[] brands = {"Toyota", "Ford", "BMW", "Honda", "Tesla"};
        String[] engines = {"V4", "V6", "V8", "Electric"};
        String[] engineShapes = {"Inline", "Boxer", "Rotary"};

        for (int i = 1; i <= count; i++) {
            Brand brand = new Brand(i, brands[random.nextInt(brands.length)]);
            Engine engine = new Engine(i, engines[random.nextInt(engines.length)], engineShapes[random.nextInt(engineShapes.length)]);

            Car car = new Car(
                    i,
                    random.nextInt(25) + 2000, // Random year between 2000 and 2025
                    models[random.nextInt(models.length)],
                    colors[random.nextInt(colors.length)],
                    random.nextDouble() * 50000 + 10000, // Price between 10,000 and 60,000
                    random.nextInt(200000), // Mileage up to 200,000
                    random.nextInt(3) + 2, // Doors between 2 and 4
                    brand,
                    BrakesType.values()[random.nextInt(BrakesType.values().length)],
                    TransmissionType.values()[random.nextInt(TransmissionType.values().length)],
                    engine,
                    "car" + i + ".jpg"
            );

            carList.add(car);
        }

        return carList;
    }

}
