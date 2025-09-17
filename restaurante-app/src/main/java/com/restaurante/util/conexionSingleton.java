package com.restaurante.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionSingleton {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurante";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789"; 

    private static Connection conexion;

    private conexionSingleton() {}

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;
    }
}
