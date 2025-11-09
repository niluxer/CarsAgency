package edu.mx.itcelaya.carsagency.customcontrols;

import edu.mx.itcelaya.carsagency.CarsAgencyApplication;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageComboBox extends ComboBox<Image> {

    private final Map<Image, String> imageFilenameMap = new HashMap<>();
    private final String imageLocation = "images";

    // No-argument constructor for FXML compatibility
    public ImageComboBox() {
        this(List.of());
    }

    // Constructor that accepts image filenames
    public ImageComboBox(List<String> imageFilenames) {
        for (String filename : imageFilenames) {
            //System.out.println(CarsAgencyApplication.class.getResource(imageLocation + "/" + filename).toString());
            Image image = new Image(CarsAgencyApplication.class.getResource(imageLocation + "/" + filename).toString());
            imageFilenameMap.put(image, filename);
            getItems().add(image);
        }

        setCellFactory(param -> new ImageListCell());
        setButtonCell(new ImageListCell());
    }

    // Allows adding new images dynamically
    public void addImage(String filename) {
        Image image = new Image(CarsAgencyApplication.class.getResource(imageLocation + "/" + filename).toString());
        imageFilenameMap.put(image, filename);
        getItems().add(image);
    }

    // Retrieve the filename of the selected image
    public String getSelectedImageFilename() {
        Image selectedImage = getValue();
        return imageFilenameMap.get(selectedImage);
    }

    public void setSelectedImage(String filename) {
        for (Map.Entry<Image, String> entry : imageFilenameMap.entrySet()) {
            if (entry.getValue().equals(filename)) {
                System.out.println("found: " + entry.getKey());
                setValue(entry.getKey()); // Set the Image as the selected item
                break;
            } else System.out.println("not found: " + entry.getKey());
        }
    }
    // Custom ListCell for displaying images
    private static class ImageListCell extends ListCell<Image> {
        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(Image image, boolean empty) {
            super.updateItem(image, empty);
            if (empty || image == null) {
                setGraphic(null);
            } else {
                imageView.setImage(image);
                imageView.setFitWidth(80);
                imageView.setPreserveRatio(true);
                setGraphic(imageView);
            }
        }
    }
}

/*import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageComboBox extends ComboBox<Image> {

    // No-argument constructor for FXML
    public ImageComboBox() {
        this(new ArrayList<>()); // Initialize with an empty list
    }

    public ImageComboBox(List<Image> images) {
        super.getItems().addAll(images);
        setCellFactory(param -> new ImageListCell());
        setButtonCell(new ImageListCell());
    }

    private static class ImageListCell extends ListCell<Image> {
        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(Image image, boolean empty) {
            super.updateItem(image, empty);
            if (empty || image == null) {
                setGraphic(null);
            } else {
                imageView.setImage(image);
                imageView.setFitWidth(50); // Adjust size as needed
                imageView.setPreserveRatio(true);
                setGraphic(imageView);
            }
        }
    }
}*/

/*import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class ImageComboBox extends ComboBox<Image> {

    public ImageComboBox(List<Image> images) {
        super.getItems().addAll(images);
        setCellFactory(param -> new ImageListCell());
        setButtonCell(new ImageListCell()); // Show selected item in the button
    }

    private static class ImageListCell extends ListCell<Image> {
        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(Image image, boolean empty) {
            super.updateItem(image, empty);
            if (empty || image == null) {
                setGraphic(null);
            } else {
                imageView.setImage(image);
                imageView.setFitWidth(50); // Adjust size as needed
                imageView.setPreserveRatio(true);
                setGraphic(imageView);
            }
        }
    }
}
*/