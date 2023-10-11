package com.example.shopall_challenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@Getter
@Setter
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrito_id;
    private int cantidad;
    private float monto_total;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    /*@OneToMany(targetEntity = Transaction.class,fetch = FetchType.LAZY,mappedBy = "transaccion")
    private List<Transaction> shoppingCarts;*/
}
