package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.ProfecionalConsultorio.PConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoGetDTO;
import com.Turnos.TurnosMedico.Util.Disponibilidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ProfesionalGetDTO {
    private Integer id;
    private String matricula;

    private String nombre;

    private String apellido;

    private Disponibilidad disponibilidad;

    private EspecialidadGetDTO especialidad;

    private String telefono;
    private String email;

    private LocalTime horarioInicio;

    private LocalTime horarioFin;

    private Integer duracionTurnoMinutos;

    private Boolean activo;

    private List<PConsultorioGetDTO> consultorios;

}
