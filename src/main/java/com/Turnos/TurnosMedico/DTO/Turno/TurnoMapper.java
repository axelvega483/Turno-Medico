package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.DTO.Paciente.PacienteMapper;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalMapper;
import com.Turnos.TurnosMedico.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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
        dto.setFechaCreacion(turno.getFechaCreacion());
        dto.setFechaActualizacion(turno.getFechaActualizacion());
        dto.setActivo(turno.isActivo());
        return dto;
    }

    public Turno toEntity(TurnoPostDTO dto, Paciente paciente, Profesional profesional, Consultorio consultorio, Especialidad especialidad) {
        Turno turno = new Turno();
        turno.setFechaHora(dto.getFechaHora());
        turno.setEstado(dto.getEstado());
        turno.setTipoConsulta(dto.getTipoConsulta());
        turno.setFechaCreacion(LocalDateTime.now());
        turno.setPaciente(paciente);
        turno.setProfesional(profesional);
        turno.setConsultorio(consultorio);
        turno.setEspecialidad(especialidad);
        turno.setActivo(true);
        return turno;
    }
    public Turno updateEntityFromDTO(TurnoUpdateDTO dto, Turno turno) {
        if (dto.getFechaHora() != null) {
            turno.setFechaHora(dto.getFechaHora());
        }
        if (dto.getEstado() != null) {
            turno.setEstado(dto.getEstado());
        }
        if (dto.getTipoConsulta() != null) {
            turno.setTipoConsulta(dto.getTipoConsulta());
        }
        turno.setFechaActualizacion(LocalDateTime.now());
        return turno;
    }

    public List<TurnoGetDTO> toDTOList(List<Turno> turnos) {
        return turnos.stream().filter(Turno::isActivo).map(this::toDTO).toList();
    }
}
