package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {


    private Connection connection = null;

    public Database(String url, String user_name, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user_name, password);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            System.out.println("Pas de driver SQL");
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
            System.out.println("Pas de connexion à la base");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public PreparedStatement selectAllFromClients() throws SQLException {
        String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }


    public PreparedStatement selectAllFromClientsByNum(String num) throws SQLException {
        String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients WHERE clt_num = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, num);
        return preparedStatement;
    }

    public PreparedStatement createClient(String num, String nom, String pnom, String loc, String pays) throws SQLException {
        String query = "INSERT INTO clients (clt_num, clt_nom, clt_pnom, clt_loc, clt_pays) VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, pnom);
        preparedStatement.setString(3, loc);
        preparedStatement.setString(4, pays);
        preparedStatement.setString(5, num);
        return preparedStatement;
    }

    public PreparedStatement updateClient(String num, String nom, String pnom, String loc, String pays) throws SQLException {
    String query ="UPDATE clients set clt_nom = ?, clt_pnom = ?, clt_loc = ?, clt_pays = ? WHERE clt_num = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, nom);
    preparedStatement.setString(2, pnom);
    preparedStatement.setString(3, loc);
    preparedStatement.setString(4,pays);
    preparedStatement.setString(5, num);
    return preparedStatement;
    }

    public PreparedStatement deleteClient(String num)throws SQLException{
        String query = "DELETE FROM clients where clt_num = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, num);
        return preparedStatement;
    }
}


