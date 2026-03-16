package com.Turnos.TurnosMedico.DTO.Disponibilidad;

import com.Turnos.TurnosMedico.Util.Dia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

public record DisponibilidadPostDTO(
        @NotNull(message = "El ID del consultorio es requerido")
        @Positive(message = "El ID del consultorio debe ser un número positivo")
        Integer consultorioId,

        @NotNull(message = "El día de la semana es requerido")
        Dia dia,

        @NotNull(message = "El horario de inicio es requerido")
        LocalTime horarioInicio,

        @NotNull(message = "El horario de fin es requerido")
        LocalTime horarioFin) {

}
