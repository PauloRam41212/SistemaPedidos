
package com.sistemadepedidos.observer;

import com.sistemadepedidos.modelos.Pedido;
import java.util.ArrayList;
import java.util.List;

public class Evento {

    private final List<Observador> usuarios = new ArrayList<>();

    public void suscribir(Observador usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    public void desuscribir(Observador usuario) {
        usuarios.remove(usuario);
    }

    public void notificar(Pedido pedido) {
        for (Observador usuario : usuarios) {
            usuario.actualizar(pedido);
        }
    }
}

