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
@Table(name = "obra_social")
public class ObraSocial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String email;

    @Column(name = "activo")
    private boolean activo ;
}
