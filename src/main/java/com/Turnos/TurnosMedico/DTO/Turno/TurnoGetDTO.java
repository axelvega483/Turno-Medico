package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacienteGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.Util.EstadoTurno;
import com.Turnos.TurnosMedico.Util.TipoConsulta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoGetDTO {
    private Integer id;

    private PacienteGetDTO paciente;

    private ProfesionalGetDTO profesional;

    private EspecialidadGetDTO especialidad;

    private LocalDateTime fechaHora;

    private EstadoTurno estado;

    private TipoConsulta tipoConsulta;

    private String observaciones;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    private boolean activo;
}
