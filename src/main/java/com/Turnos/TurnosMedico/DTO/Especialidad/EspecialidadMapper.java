package com.Turnos.TurnosMedico.DTO.Especialidad;

import com.Turnos.TurnosMedico.model.Especialidad;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadMapper {
    public EspecialidadGetDTO toDTO(Especialidad especialidad) {
        EspecialidadGetDTO dto = new EspecialidadGetDTO();
        dto.setId(especialidad.getId());
        dto.setActivo(especialidad.getActivo());
        dto.setDescripcion(especialidad.getDescripcion());
        dto.setNombre(especialidad.getNombre());
        return dto;
    }
}
