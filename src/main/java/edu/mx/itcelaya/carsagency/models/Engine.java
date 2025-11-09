package edu.mx.itcelaya.carsagency.models;

public class Engine {
    private int id;
    private String name;
    private String shape;

    public Engine() {
    }

    public Engine(int id, String name, String shape) {
        this.id = id;
        this.name = name;
        this.shape = shape;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return name;
    }
}
