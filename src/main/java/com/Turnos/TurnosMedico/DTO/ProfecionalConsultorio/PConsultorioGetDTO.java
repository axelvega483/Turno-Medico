package com.Turnos.TurnosMedico.DTO.ProfecionalConsultorio;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.Util.Dia;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class PConsultorioGetDTO {
    private Integer id;

    private ProfesionalGetDTO profesional;

    private ConsultorioGetDTO consultorio;

    private Dia dia;

    private LocalTime horarioInicio;

    private LocalTime horarioFin;
}
