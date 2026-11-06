
package com.sistemadepedidos.modelos;

public class PedidoNormal extends Pedido {

    public PedidoNormal(String producto, double precio) {
        super(producto, precio);
    }

    @Override
    public String getTipo() {
        return "Normal";
    }
}
