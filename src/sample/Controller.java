package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {

    public TextField id_text;
    public TextField year_text;
    public TextField make_text;
    public TextField model_text;
    public TableView main_table;
    public TableColumn id_column;
    public TableColumn year_column;
    public TableColumn make_column;
    public TableColumn model_column;
    public Button create_btn;
    public Button update_btn;
    public Button delete_btn;

    public Connection getConnection(){

        Connection connect_object;
        try{

            connect_object = DriverManager.getConnection("");
            return connect_object;

        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }

    public ObservableList<Vehicle> getVehicles(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM vehicle";

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            while(result_set.next()){
                Vehicle vehicles_queried;
                vehicles_queried = new Vehicle(result_set.getInt("id"), result_set.getInt("year"), result_set.getString("make"), result_set.getString("model"));
                vehicles.add(vehicles_queried);
            }

        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }

        return vehicles;
    }

    public void pushVehicles(){

        ObservableList<Vehicle> vehicles = getVehicles();
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        year_column.setCellValueFactory(new PropertyValueFactory<>("year"));
        make_column.setCellValueFactory(new PropertyValueFactory<>("make"));
        model_column.setCellValueFactory(new PropertyValueFactory<>("model"));


    }
}
