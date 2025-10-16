package com.Turnos.TurnosMedico.DTO.Disponibilidad;

import com.Turnos.TurnosMedico.Util.Dia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadPutDTO {
    @NotNull(message = "El ID del consultorio es requerido")
    @Positive(message = "El ID del consultorio debe ser un número positivo")
    private Integer consultorioId;

    @NotNull(message = "El día de la semana es requerido")
    private Dia dia;

    @NotNull(message = "El horario de inicio es requerido")
    private LocalTime horarioInicio;

    @NotNull(message = "El horario de fin es requerido")
    private LocalTime horarioFin;
}
