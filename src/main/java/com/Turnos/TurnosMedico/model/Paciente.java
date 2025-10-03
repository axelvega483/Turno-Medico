package com.Turnos.TurnosMedico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String genero;
    private String telefono;
    private String email;
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "obra_social_id")
    private ObraSocial obraSocial;

    @Column(name = "numero_afiliado")
    private String numeroAfiliado;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    private Boolean activo;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Turno> turnos = new ArrayList<>();
}
