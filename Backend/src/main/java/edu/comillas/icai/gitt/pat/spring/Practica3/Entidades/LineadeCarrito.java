package edu.comillas.icai.gitt.pat.spring.Practica3.Entidades;

import jakarta.persistence.*;

@Entity
public class LineadeCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idLineaCarrito;

    @ManyToOne@JoinColumn(name="idCarrito", referencedColumnName = "idCarrito", nullable = false)
    public Carrito carrito;

    @Column(nullable = false)
    public Long idArticulo;

    @Column(nullable = false)
    public Double precioUnitario;

    @Column(nullable = false)
    public Long numeroUnidades;

    @Column(nullable = false)
    public Double costeLineaArticulo;
}
