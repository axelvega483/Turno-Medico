package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.DTO.Paciente.PacientePostDTO;
import com.Turnos.TurnosMedico.Util.EstadoTurno;
import com.Turnos.TurnosMedico.Util.TipoConsulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TurnoPostDTO(
        @NotNull(message = "El ID del paciente es obligatorio")
        PacientePostDTO paciente,

        @NotNull(message = "El ID del profesional es obligatorio")
        Integer profesionalId,

        @NotNull(message = "El ID de la especialidad es obligatorio")
        Integer especialidadId,

        @NotNull(message = "La fecha y hora son obligatorias")
        @Future(message = "La fecha y hora deben ser futuras")
        LocalDateTime fechaHora,

        @NotNull(message = "El tipo de consulta es obligatorio")
        TipoConsulta tipoConsulta,

        @NotNull(message = "El estado del turno es obligatorio")
        EstadoTurno estado,

        @NotNull(message = "El ID del consultorio es obligatorio")
        Integer consultorioId) {

}
