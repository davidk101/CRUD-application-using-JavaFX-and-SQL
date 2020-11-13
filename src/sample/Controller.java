package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    private Properties user;
    private Properties password;

    public Connection getConnection(){

        Connection connect_object;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect_object = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUDAPPSCHEMA","root", "Divyan23" );
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
        String sql_query = "SELECT * FROM vehicles";

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

        main_table.setItems(vehicles);
    }

    public void createVehicle() throws SQLException {

        if(id_text.getText().equals("") || year_text.getText().equals("") || make_text.getText().equals("") || model_text.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else{
        String sql_query = "INSERT INTO vehicles VALUES(" + id_text.getText() + "," + year_text.getText() + ",'" + make_text.getText() + "','" + model_text.getText() + "')";
        establishSQLConnection(sql_query);
        pushVehicles();
        }
    }

    public void updateVehicle() throws SQLException {

        if(id_text.getText().equals("") || year_text.getText().equals("") || make_text.getText().equals("") || model_text.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            String sql_query = "UPDATE vehicles SET year = " + year_text.getText() + ",make = '" + make_text.getText() + "', model = '" + model_text.getText() + "' WHERE id = " + id_text.getText() + "";
            establishSQLConnection(sql_query);
            pushVehicles();
        }
    }

    private void deleteVehicle() throws SQLException {

        if(id_text.getText().equals("") || year_text.getText().equals("") || make_text.getText().equals("") || model_text.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "DELETE FROM vehicles WHERE id = " + id_text.getText() + "";
            establishSQLConnection(sql_query);
            pushVehicles();
        }

    }

    private void establishSQLConnection(String sql_query) throws SQLException {

        Connection connect = getConnection();

        try(Statement statement = connect.createStatement()){
            statement.executeUpdate(sql_query);
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

    public void mouseClicked(MouseEvent mouseEvent) {

        Vehicle vehicle = (Vehicle) main_table.getSelectionModel().getSelectedItem();
        id_text.setText(String.valueOf(vehicle.getId()));
        year_text.setText(String.valueOf(vehicle.getYear()));
        make_text.setText(vehicle.getMake());
        model_text.setText(vehicle.getModel());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pushVehicles();
    }
}
