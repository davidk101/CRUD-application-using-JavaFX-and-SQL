package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;

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

        ObservableList<Vehicle> Vehicles = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM vehicle";

        try{

        }
        catch{

        }
    }
}
