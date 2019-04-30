package app;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {

        // setup connection varibles
        String user = "springstudent";
        String pass = "springstudent";
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:XE";
        String driver = "oracle.jdbc.OracleDriver";

        java.sql.Date d = java.sql.Date.valueOf("1582-10-10");

        String query = "select * from DUMMY.employees_copy where HIRE_DATE > to_date(" + new SimpleDateFormat("dd-MM-YYYY").format(new java.util.Date()) + ",'DD-MM-YYYY')";

        System.out.println(d);

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getDate(6));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
