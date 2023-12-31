package com.example.shopall_challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table
@Data
@Getter
@Setter
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendedor_id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
