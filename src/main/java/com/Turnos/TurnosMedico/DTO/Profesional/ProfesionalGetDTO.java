package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.Util.EstadoDisponible;

import java.util.List;


public record ProfesionalGetDTO(
        Integer id,
        String matricula,
        String nombre,
        String apellido,
        EstadoDisponible disponible,
        EspecialidadGetDTO especialidad,
        List<DisponibilidadGetDTO> disponibilidades,
        String telefono,
        String email) {


}
