package com.Turnos.TurnosMedico.model;

import com.Turnos.TurnosMedico.Util.Dia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disponibilidad implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private Dia dia;

    @Column(name = "horario_inicio", nullable = false)
    private LocalTime horarioInicio;

    @Column(name = "horario_fin", nullable = false)
    private LocalTime horarioFin;

    // Métodos útiles
    public boolean estaDisponible(LocalDateTime fechaHora) {
        return this.dia.equals(Dia.from(fechaHora.getDayOfWeek())) &&
                !fechaHora.toLocalTime().isBefore(horarioInicio) &&
                !fechaHora.toLocalTime().isAfter(horarioFin);
    }

    public Duration obtenerDuracionJornada() {
        return Duration.between(horarioInicio, horarioFin);
    }
}