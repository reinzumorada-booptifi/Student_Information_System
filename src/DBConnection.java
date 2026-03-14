/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Properties;
/**
 *
 * @author jenniferobach
 */
public class DBConnection {
private static Connection connection = null;

    public static Connection getConnection() {

        try {

            if(connection == null){

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/student_information_system",
                        "root",
                        "bryn16@THR"
                );

            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }

}