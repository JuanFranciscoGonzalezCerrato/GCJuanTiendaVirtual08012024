package es.albarregas.beans;

import java.util.HashMap;

public class Carrito {

    int idCarrito;
    int idUsuario;
    HashMap<Producto, Integer> productos;

    public Carrito() {
        this.productos = new HashMap<>();
    }

    public Carrito(int idCarrito, int idUsuario, HashMap<Producto, Integer> productos) {
        this.idCarrito = idCarrito;
        this.idUsuario = idUsuario;
        this.productos = productos;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public HashMap<Producto, Integer> getProductos() {
        return productos;
    }

    public void setProductos(HashMap<Producto, Integer> productos) {
        this.productos = productos;
    }

    public Double importeTotal() {
        double retorno = 0.00;
        for (Producto i : this.productos.keySet()) {

            retorno = retorno + (i.getPrecio() * this.productos.get(i));

        }
        return retorno;
    }

}
