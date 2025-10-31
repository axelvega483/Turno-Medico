package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.Util.EstadoTurno;
import com.Turnos.TurnosMedico.Util.TipoConsulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoUpdateDTO {
    private Integer pacienteId;

    private Integer profesionalId;

    private Integer especialidadId;

    private Integer consultorioId;

    @Future(message = "La fecha y hora deben ser futuras")
    private LocalDateTime fechaHora;

    private EstadoTurno estado;

    private TipoConsulta tipoConsulta;

    @Size(max = 500, message = "Las observaciones no pueden exceder los 500 caracteres")
    private String observaciones;
}
