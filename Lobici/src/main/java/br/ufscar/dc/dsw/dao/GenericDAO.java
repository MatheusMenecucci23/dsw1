package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericDAO {
    public GenericDAO(){
        try {
            /* Setup Banco de dados MySQL */
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        /* Conexão banco de dados MySQL */
        String url = "jdbc:mysql://lobici-db-1:3306/Lobici";
        return DriverManager.getConnection(url, "user", "password");
    }
}