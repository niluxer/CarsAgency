package edu.mx.itcelaya.carsagency.services.cars;

import edu.mx.itcelaya.carsagency.database.dao.CarDao;
import edu.mx.itcelaya.carsagency.models.Car;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.List;

public class LoadCarsService extends Service<List<Car>> {

    CarDao carDao = new CarDao();

    @Override
    protected Task<List<Car>> createTask() {
        return new Task<List<Car>>() {
            @Override
            protected List<Car> call() throws Exception {
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                return carDao.findAll();
            }
        };
    }
}
