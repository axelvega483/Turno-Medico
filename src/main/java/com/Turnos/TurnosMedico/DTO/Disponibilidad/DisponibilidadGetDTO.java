package com.Turnos.TurnosMedico.DTO.Disponibilidad;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.Util.Dia;

import java.time.LocalTime;


public record DisponibilidadGetDTO(
        Dia dia,
        LocalTime horarioInicio,
        LocalTime horarioFin,
        ConsultorioGetDTO consultorio) {

}
