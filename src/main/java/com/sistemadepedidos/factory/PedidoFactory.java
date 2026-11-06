
package com.sistemadepedidos.factory;

import com.sistemadepedidos.modelos.Pedido;
import com.sistemadepedidos.modelos.PedidoNormal;
import com.sistemadepedidos.modelos.PedidoUrgente;

public class PedidoFactory {

    public static Pedido crearPedido(
            String tipo, String producto, double precio) {

        switch (tipo.toLowerCase()) {

            case "normal":
                return new PedidoNormal(producto, precio);

            case "urgente":
                return new PedidoUrgente(producto, precio);

            default:
                throw new IllegalArgumentException(
                    "Tipo de pedido no válido: " + tipo
                );
        }
    }
}

