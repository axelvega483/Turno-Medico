package com.Turnos.TurnosMedico.DTO.Turno;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadMapper;
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
    @Autowired
    private EspecialidadMapper mapperEspecialidad;

    public TurnoGetDTO toDTO(Turno turno) {
        return new TurnoGetDTO(
                turno.getId(),
                turno.getPaciente() != null ? mapperPaciente.toDTO(turno.getPaciente()) : null,
                turno.getProfesional() != null ? mapperProfesional.toDTO(turno.getProfesional()) : null,
                turno.getEspecialidad() != null ? mapperEspecialidad.toDTO(turno.getEspecialidad()) : null,
                turno.getFechaHora(),
                turno.getEstado(),
                turno.getTipoConsulta(),
                turno.getFechaCreacion(),
                turno.getFechaActualizacion()

        );
    }

    public Turno toEntity(TurnoPostDTO dto, Paciente paciente, Profesional profesional, Consultorio consultorio, Especialidad especialidad) {
        return Turno.builder()
                .fechaHora(dto.fechaHora())
                .estado(dto.estado())
                .tipoConsulta(dto.tipoConsulta())
                .fechaCreacion(LocalDateTime.now())
                .paciente(paciente)
                .profesional(profesional)
                .consultorio(consultorio)
                .especialidad(especialidad)
                .activo(true)
                .build();
    }

    public void updateEntityFromDTO(TurnoUpdateDTO dto, Turno turno) {
        if (dto.fechaHora() != null) {
            turno.setFechaHora(dto.fechaHora());
        }
        if (dto.estado() != null) {
            turno.setEstado(dto.estado());
        }
        if (dto.tipoConsulta() != null) {
            turno.setTipoConsulta(dto.tipoConsulta());
        }
        turno.setFechaActualizacion(LocalDateTime.now());
    }

    public List<TurnoGetDTO> toDTOList(List<Turno> turnos) {
        return turnos.stream().filter(Turno::isActivo).map(this::toDTO).toList();
    }
}
