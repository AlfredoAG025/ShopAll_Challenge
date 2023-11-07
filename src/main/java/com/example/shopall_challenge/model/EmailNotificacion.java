package com.example.shopall_challenge.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="emailnotificacion")
@Data
@Getter
@Setter
public class EmailNotificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificacion_id;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    private String tipo_notificacion;
    private String mensaje;
    private LocalDate fecha_envio;
}
