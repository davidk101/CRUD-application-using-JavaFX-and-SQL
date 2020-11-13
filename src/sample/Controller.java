package sample;

import com.sun.jndi.toolkit.url.UrlUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.nashorn.internal.objects.NativeError;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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

    public void Initialize(URL url, ResourceBundle rb){
        pushVehicles();
    }

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

        ObservableList<Vehicle> vehicles;
        vehicles = getVehicles();
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        year_column.setCellValueFactory(new PropertyValueFactory<>("year"));
        make_column.setCellValueFactory(new PropertyValueFactory<>("make"));
        model_column.setCellValueFactory(new PropertyValueFactory<>("model"));

        main_table.setItems(vehicles);
    }

    public void createVehicle() throws SQLException {

        String sql_query = "INSERT INTO vehicles VALUES(" + id_text.getText() + "," + year_text.getText() + ",'" + make_text.getText() + "','" + model_text.getText() + "')";
        establishSQLConnection(sql_query);
        pushVehicles();
    }

    public void updateVehicle() throws SQLException {

        String sql_query = "UPDATE vehicles SET ";
        establishSQLConnection(sql_query);
        pushVehicles();

    }

    private void deleteVehicle() throws SQLException {
        String sql_query = "DELETE FROM vehicles WHERE id = " + id_text.getText() + "";
        establishSQLConnection(sql_query);
        pushVehicles();

    }

    private void establishSQLConnection(String sql_query) throws SQLException {

        Connection connect = getConnection();

        try(Statement statement = connect.createStatement()){
            statement.executeQuery(sql_query);
        }
        catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

        if (actionEvent.getSource() == create_btn ){
            createVehicle();
        }
        else if(actionEvent.getSource() == update_btn){
            updateVehicle();
        }

        else if(actionEvent.getSource() == delete_btn){
            deleteVehicle();
        }
    }
}
