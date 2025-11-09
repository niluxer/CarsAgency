package edu.mx.itcelaya.carsagency.models;

import edu.mx.itcelaya.carsagency.enums.BrakesType;
import edu.mx.itcelaya.carsagency.enums.TransmissionType;

public class Car {
    private int id;
    private int year;
    private String model;
    private String color;
    private double price;
    private int mileage;
    private int doors;
    private Brand brand;
    private BrakesType brakesType;
    private TransmissionType transmissionType;
    private Engine engine;
    private String image;
    private String brand_name;
    private String engine_name;

    public Car() {
    }

    public Car(int id, int year, String model, String color, double price, int mileage, int doors, Brand brand, BrakesType brakesType, TransmissionType transmissionType, Engine engine, String image) {
        this.id = id;
        this.year = year;
        this.model = model;
        this.color = color;
        this.price = price;
        this.mileage = mileage;
        this.doors = doors;
        this.brand = brand;
        this.brakesType = brakesType;
        this.transmissionType = transmissionType;
        this.engine = engine;
        this.image = image;
    }

    public Car(int id, int year, String model, String color, double price, int mileage, int doors, String brand_name, BrakesType brakesType, TransmissionType transmissionType, String engine_name, String image) {
        this.id = id;
        this.year = year;
        this.model = model;
        this.color = color;
        this.price = price;
        this.mileage = mileage;
        this.doors = doors;
        this.brand_name = brand_name;
        this.brakesType = brakesType;
        this.transmissionType = transmissionType;
        this.engine_name = engine_name;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BrakesType getBrakesType() {
        return brakesType;
    }

    public void setBrakesType(BrakesType brakesType) {
        this.brakesType = brakesType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getEngine_name() {
        return engine_name;
    }

    public void setEngine_name(String engine_name) {
        this.engine_name = engine_name;
    }
}
