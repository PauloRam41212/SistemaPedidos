
package com.sistemadepedidos.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private static final ConexionBD instancia = new ConexionBD();

    private Connection conexion;

    private ConexionBD() {
    }

    public static ConexionBD getInstancia() {
        return instancia;
    }

    public synchronized Connection getConexion() throws SQLException {

        if (conexion == null || conexion.isClosed()) {

            conexion = DriverManager.getConnection(
                "jdbc:sqlite:pedidos.db"
            );

            crearTabla();
        }

        return conexion;
    }

    private void crearTabla() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS pedidos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "producto TEXT NOT NULL, "
                + "precio REAL NOT NULL, "
                + "tipo TEXT NOT NULL"
                + ")";

        try (Statement statement = conexion.createStatement()) {
            statement.execute(sql);
        }
    }
}
