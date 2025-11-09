module edu.mx.itcelaya.carsagency {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.dlsc.formsfx;

    opens edu.mx.itcelaya.carsagency to javafx.fxml;
    opens edu.mx.itcelaya.carsagency.catalogs;
    opens edu.mx.itcelaya.carsagency.models;
    opens edu.mx.itcelaya.carsagency.catalogs.forms;
    exports edu.mx.itcelaya.carsagency;
    exports edu.mx.itcelaya.carsagency.customcontrols;
    exports edu.mx.itcelaya.carsagency.catalogs.forms;
}