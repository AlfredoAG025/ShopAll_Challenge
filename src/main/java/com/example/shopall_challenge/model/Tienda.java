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
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tienda_id;
    @ManyToOne
    @JoinColumn(name="vendedor_id")
    private Vendedor vendedor;
    private String nombre_tienda;
    private String descripcion;
}
