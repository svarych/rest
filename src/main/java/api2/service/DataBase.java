package api2.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private String DB_URL;
    private String USER;
    private String PASS;
    private Connection conn = null;
    private Statement stmt = null;

    public DataBase() {
        DB_URL = "jdbc:mysql://wdb2.sb.np.ua:3306/webclient";
        USER = "tober";
        PASS = "love13q3";

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<String> getResponse(String cell, String table, String parameter, String value) throws SQLException {
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT " + cell + " FROM " + table + " WHERE " + parameter + " = '" + value + "'";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("code"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    public List<String> getResponse(String query) throws SQLException {
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = query;
        ResultSet rs = stmt.executeQuery(sql);
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("MessageText"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    public List<String> getResponseX(String query) throws SQLException {
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = query;
        ResultSet rs = stmt.executeQuery(sql);
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("MessageContent"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

}
