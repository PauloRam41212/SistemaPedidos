
package com.sistemadepedidos.modelos;

public class PedidoUrgente extends Pedido {

    public PedidoUrgente(String producto, double precio) {
        super(producto, precio);
    }

    @Override
    public String getTipo() {
        return "Urgente";
    }
}
