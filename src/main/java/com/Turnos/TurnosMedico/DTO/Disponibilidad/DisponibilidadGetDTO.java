package com.Turnos.TurnosMedico.DTO.Disponibilidad;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.Util.Dia;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class DisponibilidadGetDTO {
    private ConsultorioGetDTO consultorio;
    private Dia dia;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
}
