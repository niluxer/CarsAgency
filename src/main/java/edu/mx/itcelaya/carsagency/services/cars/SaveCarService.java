package edu.mx.itcelaya.carsagency.services.cars;

import edu.mx.itcelaya.carsagency.models.Car;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class SaveCarService extends Service<Car> {
    @Override
    protected Task<Car> createTask() {
        return null;
    }
}
