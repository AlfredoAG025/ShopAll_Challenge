package com.example.shopall_challenge.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table

@Data
@Setter
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_id;
    private String nombre_usuario;
    private String email;
    private String contrasena;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}

