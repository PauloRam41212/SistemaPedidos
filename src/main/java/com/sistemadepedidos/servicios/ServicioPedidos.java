
package com.sistemadepedidos.servicios;

import com.sistemadepedidos.factory.PedidoFactory;
import com.sistemadepedidos.interfaces.RepositorioPedidos;
import com.sistemadepedidos.modelos.Pedido;
import com.sistemadepedidos.observer.Evento;
import java.sql.SQLException;

public class ServicioPedidos {

    private final RepositorioPedidos repositorio;
    private final Evento evento;

    public ServicioPedidos(RepositorioPedidos repositorio, Evento evento) {
        this.repositorio = repositorio;
        this.evento = evento;
    }

    public void registrarPedido(String tipo, String producto, double precio)
            throws SQLException {

        Pedido pedido = PedidoFactory.crearPedido(
            tipo, producto, precio
        );

        repositorio.guardar(pedido);

        System.out.println("\n¡Pedido registrado correctamente!");
        pedido.mostrarInformacion();

        evento.notificar(pedido);
    }
}
