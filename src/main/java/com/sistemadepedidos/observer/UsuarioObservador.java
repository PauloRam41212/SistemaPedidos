
package com.sistemadepedidos.observer;

import com.sistemadepedidos.modelos.Pedido;
import com.sistemadepedidos.modelos.Usuario;

public class UsuarioObservador implements Observador {

    private final Usuario usuario;

    public UsuarioObservador(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void actualizar(Pedido pedido) {
        System.out.println(
            "NOTIFICACION PARA " + usuario.getNombre()
        );

        System.out.println(
            "Se ha registrado un pedido: "
            + pedido.getProducto()
        );

        System.out.println(
            "Tipo: " + pedido.getTipo()
        );
    }
}
