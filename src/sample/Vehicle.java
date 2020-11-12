package sample;

public class Vehicle {

    public int id;
    public int year;
    public String make;
    public String model;

    public Vehicle(int id, int year, String make, String model) {

        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
