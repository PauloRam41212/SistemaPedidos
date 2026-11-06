
package com.sistemadepedidos;

import com.sistemadepedidos.modelos.Usuario;
import com.sistemadepedidos.observer.Evento;
import com.sistemadepedidos.observer.UsuarioObservador;
import com.sistemadepedidos.servicios.RepositorioPedidosSQLite;
import com.sistemadepedidos.servicios.ServicioPedidos;
import com.sistemadepedidos.singleton.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class SistemaPedidos {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Evento evento = new Evento();

        ServicioPedidos servicio = new ServicioPedidos(
            new RepositorioPedidosSQLite(), evento
        );

        try {
            ConexionBD.getInstancia().getConexion();

            int opcion;

            do {
                System.out.println("\n==============================");
                System.out.println("     SISTEMA DE PEDIDOS");
                System.out.println("==============================");
                System.out.println("1. Crear pedido normal");
                System.out.println("2. Crear pedido urgente");
                System.out.println("3. Registrar usuario");
                System.out.println("4. Consultar pedidos");
                System.out.println("5. Salir");
                System.out.print("Selecciona una opcion: ");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 1:
                    case 2:
                        System.out.print("Nombre del producto: ");
                        String producto = sc.nextLine();

                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();
                        sc.nextLine();

                        String tipo = (opcion == 1) ? "normal" : "urgente";

                        servicio.registrarPedido(tipo, producto, precio);
                        break;

                    case 3:
                        System.out.print("Nombre del usuario: ");
                        String nombre = sc.nextLine();

                        evento.suscribir(
                            new UsuarioObservador(new Usuario(nombre))
                        );

                        System.out.println(
                            "Usuario suscrito correctamente."
                        );
                        break;

                    case 4:
                        consultarPedidos();
                        break;

                    case 5:
                        System.out.println("Gracias por utilizar el sistema.");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                }

            } while (opcion != 5);

        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void consultarPedidos() throws SQLException {

        String sql = "SELECT * FROM pedidos";

        try (Connection conexion =
                ConexionBD.getInstancia().getConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n===== PEDIDOS REGISTRADOS =====");

            boolean hayPedidos = false;

            while (rs.next()) {
                hayPedidos = true;

                System.out.println("--------------------------");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Producto: " + rs.getString("producto"));
                System.out.println("Precio: $" + rs.getDouble("precio"));
                System.out.println("Tipo: " + rs.getString("tipo"));
            }

            if (!hayPedidos) {
                System.out.println("No hay pedidos registrados.");
            }
        }
    }
}
