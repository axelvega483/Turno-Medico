package com.Turnos.TurnosMedico.DTO.Consultorio;

import com.Turnos.TurnosMedico.model.Consultorio;
import org.springframework.stereotype.Component;

@Component
public class ConsultorioMapper {
    public ConsultorioGetDTO toDTO(Consultorio consultorio) {
        ConsultorioGetDTO dto = new ConsultorioGetDTO();
        dto.setId(consultorio.getId());
        dto.setPiso(consultorio.getPiso());
        dto.setActivo(consultorio.getActivo());
        dto.setNumero(consultorio.getNumero());
        dto.setDescripcion(dto.getDescripcion());
        return dto;
    }
}
