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
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrito_id;
    private Integer cantidad;
    @Transient
    private Double monto_total;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Double getMonto_total() {
        this.monto_total= 0.0;

        this.monto_total = this.cantidad * this.producto.getPrecio();

        return this.monto_total;
    }
}
