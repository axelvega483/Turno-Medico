package com.Turnos.TurnosMedico.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consultorio")
public class Consultorio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String piso;

    private boolean activo;
}
