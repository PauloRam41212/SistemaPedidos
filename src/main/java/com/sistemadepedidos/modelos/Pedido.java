
package com.sistemadepedidos.modelos;

public abstract class Pedido {

    protected int id;
    protected String producto;
    protected double precio;

    public Pedido(String producto, double precio) {
        this.producto = producto;
        this.precio = precio;
    }

    public abstract String getTipo();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Producto: " + producto);
        System.out.println("Precio: $" + precio);
        System.out.println("Tipo: " + getTipo());
    }
}
