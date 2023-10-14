package com.example.shopall_challenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Long vendedor_id;
    private String nombre_tienda;
    private String descripcion;
}
