package com.example.shopall_challenge.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
@Getter
@Setter
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilegio_id;
    private String privilegio;
    @ManyToMany(mappedBy = "privilegio")
    Set<Rol> rols;
}