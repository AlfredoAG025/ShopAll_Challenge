package com.example.shopall_challenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity

@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producto_id;
    private String nombre;
    private Double precio;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
}
