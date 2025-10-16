package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.DTO.Paciente.PacienteMapper;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalMapper;
import com.Turnos.TurnosMedico.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurnoMapper {
    @Autowired
    private ProfesionalMapper mapperProfesional;
    @Autowired
    private PacienteMapper mapperPaciente;

    public TurnoGetDTO toDTO(Turno turno) {
        TurnoGetDTO dto = new TurnoGetDTO();
        dto.setId(turno.getId());
        if (turno.getProfesional() != null) {
            dto.setProfesional(mapperProfesional.toDTO(turno.getProfesional()));
        }
        if (turno.getPaciente() != null) {
            dto.setPaciente(mapperPaciente.toDTO(turno.getPaciente()));
        }
        dto.setFechaHora(turno.getFechaHora());
        dto.setEstado(turno.getEstado());
        dto.setTipoConsulta(turno.getTipoConsulta());
        dto.setObservaciones(turno.getObservaciones());
        dto.setFechaCreacion(turno.getFechaCreacion());
        dto.setFechaActualizacion(turno.getFechaActualizacion());
        dto.setActivo(turno.getActivo());
        return dto;
    }
}
