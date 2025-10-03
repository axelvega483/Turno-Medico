package com.Turnos.TurnosMedico.DTO.ProfecionalConsultorio;

import com.Turnos.TurnosMedico.Util.Dia;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PConsultorioPostDTO {

    @NotNull(message = "El ID del profesional es obligatorio")
    private Integer profesionalId;

    @NotNull(message = "El ID del consultorio es obligatorio")
    private Integer consultorioId;

    @NotNull(message = "El día de la semana es obligatorio")
    private Dia dia;

    @NotNull(message = "El horario de inicio es obligatorio")
    private LocalTime horarioInicio;

    @NotNull(message = "El horario de fin es obligatorio")
    private LocalTime horarioFin;
}
