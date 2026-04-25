package edu.comillas.icai.gitt.pat.spring.Practica3.Modelo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

//Los datos que se van a usar en la lógica
public class Carrito {
    private int idCarrito;
    private int idArticulo;
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
    @NotBlank(message = "El correo es obligatorio")
    private String correoUsuario;


    @Min(1) //Para no poder crear un carrito con 0 unidades
    private int unidades;
    private double precioFinal;

    public Carrito(int idCarrito, int idArticulo, String descripcion, int unidades, double precioFinal) {
        this.idCarrito = idCarrito;
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.precioFinal = precioFinal;
    }
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }
}
