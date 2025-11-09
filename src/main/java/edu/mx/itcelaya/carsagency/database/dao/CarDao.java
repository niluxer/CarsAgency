package edu.mx.itcelaya.carsagency.database.dao;

import edu.mx.itcelaya.carsagency.database.MySQLConnection;
import edu.mx.itcelaya.carsagency.enums.BrakesType;
import edu.mx.itcelaya.carsagency.enums.TransmissionType;
import edu.mx.itcelaya.carsagency.models.Brand;
import edu.mx.itcelaya.carsagency.models.Car;
import edu.mx.itcelaya.carsagency.models.Engine;
import javafx.collections.FXCollections;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao extends MySQLConnection implements Dao<Car> {
    Connection conn = getConnection();
    @Override
    public Optional<Car> findById(int id) {
        Optional<Car> optProduct = Optional.empty();
        String query = "select * from cars where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() )
            {
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getInt("year"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getDouble("price"),
                        rs.getInt("mileage"),
                        rs.getInt("doors"),
                        new Brand(rs.getInt("brand_id"), ""),
                        BrakesType.valueOf(rs.getString("brakes_type")),
                        TransmissionType.valueOf(rs.getString("transmission_type")),
                        new Engine(rs.getInt("engine_id"), "", ""),
                        rs.getString("image"));
                optProduct = Optional.of(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optProduct;
    }
    /*@Override
    public List<Car> findAll() {

        List<Car> carsList = FXCollections.observableArrayList();
        String query = "select * from cars";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getInt("year"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getDouble("price"),
                        rs.getInt("mileage"),
                        rs.getInt("doors"),
                        getBrand(rs.getInt("brand_id")),
                        BrakesType.valueOf(rs.getString("brakes_type")),
                        TransmissionType.valueOf(rs.getString("transmission_type")),
                        getEngine(rs.getInt("engine_id")),
                        rs.getString("image")
                );
                carsList.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carsList;
    }*/

    @Override
    public List<Car> findAll() {

        List<Car> carsList = FXCollections.observableArrayList();
        String query = "select c.*, b.name as brand_name, e.name as engine_name\n" +
                "from cars c\n" +
                "join brands b on c.brand_id = b.id\n" +
                "join engines e on c.engine_id = e.id\n" +
                "order by  c.id;";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getInt("year"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getDouble("price"),
                        rs.getInt("mileage"),
                        rs.getInt("doors"),
                        rs.getString("brand_name"),
                        BrakesType.valueOf(rs.getString("brakes_type")),
                        TransmissionType.valueOf(rs.getString("transmission_type")),
                        rs.getString("engine_name"),
                        rs.getString("image")
                );
                carsList.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carsList;
    }

    private Brand getBrand(int brand_id)
    {
        String query = "select * from brands where id = " + brand_id;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Brand brand = new Brand();
            brand.setId(rs.getInt("id"));
            brand.setName(rs.getString("name"));
            return brand;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Brand> getBrands()
    {
        List<Brand> brandsList = new ArrayList<>();
        String query = "select * from brands";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
            {
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                brandsList.add(brand);

            }
            return brandsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Engine> getEngines()
    {
        List<Engine> enginesList = new ArrayList<>();
        String query = "select * from engines";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next())
            {
                Engine engine = new Engine();
                engine.setId(rs.getInt("id"));
                engine.setName(rs.getString("name"));
                enginesList.add(engine);

            }
            return enginesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Engine getEngine(int engine_id)
    {
        String query = "select * from engines where id = " + engine_id;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Engine engine = new Engine();
            engine.setId(rs.getInt("id"));
            engine.setName(rs.getString("name"));
            return engine;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean save(Car car) {

        String query = "insert into cars " +
                        " (year, model, color, price, mileage, doors, brand_id, brakes_type, transmission_type, engine_id, image)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, car.getYear());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getColor());
            ps.setDouble(4, car.getPrice());
            ps.setInt(5, car.getMileage());
            ps.setInt(6, car.getDoors());
            ps.setInt(7, car.getBrand().getId());
            ps.setString(8, car.getBrakesType().name());
            ps.setString(9, car.getTransmissionType().name());
            ps.setInt(10, car.getEngine().getId());
            ps.setString(11, car.getImage());
            ps.execute();
            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;

    }
    @Override
    public boolean update(Car car) {
        String query = "update cars set year = ?, model = ?, color = ?, price = ?, mileage = ?, doors = ?, brand_id = ?, brakes_type = ?, transmission_type = ?, engine_id = ?, image = ?  where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, car.getYear());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getColor());
            ps.setDouble(4, car.getPrice());
            ps.setInt(5, car.getMileage());
            ps.setInt(6, car.getDoors());
            ps.setInt(7, car.getBrand().getId());
            ps.setString(8, car.getBrakesType().name());
            ps.setString(9, car.getTransmissionType().name());
            ps.setInt(10, car.getEngine().getId());
            ps.setString(11, car.getImage());
            ps.setInt(12, car.getId());
            System.out.println("ID to update: " + car.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int car_id) {
        String query = "delete from cars where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, car_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
