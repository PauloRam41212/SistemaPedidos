
package com.sistemadepedidos.interfaces;

import com.sistemadepedidos.modelos.Pedido;
import java.sql.SQLException;

public interface RepositorioPedidos {

    void guardar(Pedido pedido) throws SQLException;
}
