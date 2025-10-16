package com.Turnos.TurnosMedico.model;

import com.Turnos.TurnosMedico.Util.Dia;
import com.Turnos.TurnosMedico.Util.EstadoDisponible;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Column(name = "estado_disponible")
    private EstadoDisponible estadoDisponible;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    private String telefono;
    private String email;

    private Boolean activo;

    @ElementCollection
    @CollectionTable(
            name = "profesional_disponibilidad",
            joinColumns = @JoinColumn(name = "profesional_id"),
            foreignKey = @ForeignKey(name = "fk_profesional_disponibilidad")
    )
    private List<Disponibilidad> disponibilidades = new ArrayList<>();

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Turno> turnos = new ArrayList<>();

    public boolean estaDisponible(LocalDateTime fechaHora, Consultorio consultorio) {
        return disponibilidades.stream()
                .anyMatch(disp -> disp.getConsultorio().equals(consultorio) &&
                        disp.estaDisponible(fechaHora));
    }

    public List<Disponibilidad> getDisponibilidadesPorDia(Dia dia) {
        return disponibilidades.stream()
                .filter(disp -> disp.getDia().equals(dia))
                .collect(Collectors.toList());
    }

    public void agregarDisponibilidad(Consultorio consultorio, Dia dia,
                                      LocalTime inicio, LocalTime fin) {
        Disponibilidad nuevaDisp = new Disponibilidad(
                consultorio, dia, inicio, fin
        );
        this.disponibilidades.add(nuevaDisp);
    }

    public void removerDisponibilidad(Consultorio consultorio, Dia dia) {
        disponibilidades.removeIf(disp ->
                disp.getConsultorio().equals(consultorio) && disp.getDia().equals(dia));
    }
}
