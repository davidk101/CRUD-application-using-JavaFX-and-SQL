package sample;

// Primary object to load data to table view
public class Vehicle {

    public int id;
    public int year;
    public String make;
    public String model;

    // constructor
    public Vehicle(int id, int year, String make, String model) {

        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
    }

    // defining accessors and mutators
    public int getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    public int getYear() {return year;}

    public void setYear(Integer year) {this.year = year;}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {this.make = make;}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {this.model = model;}
}
