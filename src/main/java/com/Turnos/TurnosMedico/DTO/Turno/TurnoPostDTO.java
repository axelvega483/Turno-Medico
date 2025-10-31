package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.DTO.Paciente.PacientePostDTO;
import com.Turnos.TurnosMedico.Util.EstadoTurno;
import com.Turnos.TurnosMedico.Util.TipoConsulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoPostDTO {
    @NotNull(message = "El ID del paciente es obligatorio")
    private PacientePostDTO paciente;

    @NotNull(message = "El ID del profesional es obligatorio")
    private Integer profesionalId;

    @NotNull(message = "El ID de la especialidad es obligatorio")
    private Integer especialidadId;

    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La fecha y hora deben ser futuras")
    private LocalDateTime fechaHora;

    @NotNull(message = "El tipo de consulta es obligatorio")
    private TipoConsulta tipoConsulta;

    @NotNull(message = "El estado del turno es obligatorio")
    private EstadoTurno estado;

    @NotNull(message = "El ID del consultorio es obligatorio")
    private Integer consultorioId;
}
