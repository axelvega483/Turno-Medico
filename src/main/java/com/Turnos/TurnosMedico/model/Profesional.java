package com.Turnos.TurnosMedico.model;

import com.Turnos.TurnosMedico.Util.Disponibilidad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesional")
public class Profesional implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibilidad")
    private Disponibilidad disponibilidad;
    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    private String telefono;
    private String email;

    @Column(name = "horario_inicio")
    private LocalTime horarioInicio;

    @Column(name = "horario_fin")
    private LocalTime horarioFin;

    @Column(name = "duracion_turno_minutos")
    private Integer duracionTurnoMinutos;

    private Boolean activo;

    @OneToMany(mappedBy = "profesional")
    private List<ProfesionalConsultorio> consultorios;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Turno> turnos = new ArrayList<>();
}
