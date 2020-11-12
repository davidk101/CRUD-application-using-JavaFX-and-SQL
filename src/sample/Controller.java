package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class Controller {

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
}
