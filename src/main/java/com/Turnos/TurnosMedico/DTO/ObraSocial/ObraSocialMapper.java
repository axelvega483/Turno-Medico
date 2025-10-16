package com.Turnos.TurnosMedico.DTO.ObraSocial;

import com.Turnos.TurnosMedico.model.ObraSocial;
import org.springframework.stereotype.Component;

@Component
public class ObraSocialMapper {
    public ObraSocialGetDTO toDTO(ObraSocial obraSocial) {
        ObraSocialGetDTO dto = new ObraSocialGetDTO();
        dto.setId(obraSocial.getId());
        dto.setActivo(obraSocial.getActivo());
        dto.setCodigo(obraSocial.getCodigo());
        dto.setNombre(obraSocial.getNombre());
        dto.setEmail(obraSocial.getEmail());
        dto.setTelefono(obraSocial.getTelefono());
        return dto;
    }
}
