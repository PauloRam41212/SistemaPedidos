package com.sistemadepedidos.servicios;

import com.sistemadepedidos.interfaces.RepositorioPedidos;
import com.sistemadepedidos.modelos.Pedido;
import com.sistemadepedidos.singleton.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositorioPedidosSQLite implements RepositorioPedidos {

    @Override
    public void guardar(Pedido pedido) throws SQLException {

        String sql = "INSERT INTO pedidos (producto, precio, tipo) VALUES (?, ?, ?)";

        Connection conexion = ConexionBD.getInstancia().getConexion();

        try (PreparedStatement ps = conexion.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pedido.getProducto());
            ps.setDouble(2, pedido.getPrecio());
            ps.setString(3, pedido.getTipo());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setId(rs.getInt(1));
                }
            }
        }
    }
}