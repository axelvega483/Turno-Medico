package com.Turnos.TurnosMedico.DTO.ObraSocial;

import com.Turnos.TurnosMedico.model.ObraSocial;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ObraSocial toEntity(ObraSocialPostDTO postDTO) {
        ObraSocial obraSocial = new ObraSocial();
        obraSocial.setActivo(postDTO.getActivo());
        obraSocial.setCodigo(postDTO.getCodigo());
        obraSocial.setNombre(postDTO.getNombre());
        obraSocial.setEmail(postDTO.getEmail());
        obraSocial.setTelefono(postDTO.getTelefono());
        return obraSocial;
    }

    public ObraSocial updateEntityFromDTO(ObraSocialUpdateDTO updateDTO, ObraSocial existingObraSocial) {
        if (updateDTO.getNombre() != null) {
            existingObraSocial.setNombre(updateDTO.getNombre());
        }
        if (updateDTO.getCodigo() != null) {
            existingObraSocial.setCodigo(updateDTO.getCodigo());
        }
        if (updateDTO.getEmail() != null) {
            existingObraSocial.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getTelefono() != null) {
            existingObraSocial.setTelefono(updateDTO.getTelefono());
        }
        return existingObraSocial;
    }

    public List<ObraSocialGetDTO> toDTOList(List<ObraSocial> obraSocials) {
        return obraSocials.stream().filter(ObraSocial::getActivo).map(this::toDTO).toList();
    }
}
