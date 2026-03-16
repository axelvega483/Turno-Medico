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

public record TurnoUpdateDTO(
        Integer pacienteId,
        Integer profesionalId,
        Integer especialidadId,
        Integer consultorioId,
        @Future(message = "La fecha y hora deben ser futuras")
        LocalDateTime fechaHora,
        EstadoTurno estado,
        TipoConsulta tipoConsulta) {

}
