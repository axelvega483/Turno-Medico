package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadMapper;
import com.Turnos.TurnosMedico.model.Profesional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfesionalMapper {
    @Autowired
    private EspecialidadMapper mapper;

    public ProfesionalGetDTO toDTO(Profesional profesional) {
        ProfesionalGetDTO dto = new ProfesionalGetDTO();
        dto.setId(profesional.getId());
        dto.setApellido(profesional.getApellido());
        dto.setNombre(profesional.getNombre());
        dto.setDisponible(profesional.getEstadoDisponible());
        dto.setEmail(profesional.getEmail());
        dto.setMatricula(profesional.getMatricula());
        dto.setActivo(profesional.getActivo());
        if (profesional.getEspecialidad() != null) {
            dto.setEspecialidad(mapper.toDTO(profesional.getEspecialidad()));
        }
        dto.setTelefono(profesional.getTelefono());
        return dto;
    }
}
