package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacienteGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.Util.EstadoTurno;
import com.Turnos.TurnosMedico.Util.TipoConsulta;

import java.time.LocalDateTime;

public record TurnoGetDTO(
        Integer id,
        PacienteGetDTO paciente,
        ProfesionalGetDTO profesional,
        EspecialidadGetDTO especialidad,
        LocalDateTime fechaHora,
        EstadoTurno estado,
        TipoConsulta tipoConsulta,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion) {
}
