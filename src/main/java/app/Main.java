package app;

import java.io.PrintWriter;
import java.sql.*;

import oracle.jdbc.OracleDriver;

public class Main {
    public static void main(String[] args) {

        // setup connection varibles
        String user = "springstudent";
        String pass = "springstudent";
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:XE";
        String driver = "oracle.jdbc.OracleDriver";
        String query = "select * from SPRINGSTUDENT.CUSTOMER";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
