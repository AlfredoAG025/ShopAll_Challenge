package com.example.shopall_challenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table
@Data
@Getter
@Setter
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventario_id;
    private float precio_venta;
    private int cantidad_stock;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
