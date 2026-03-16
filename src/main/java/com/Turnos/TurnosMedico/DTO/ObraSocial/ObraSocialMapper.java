package com.Turnos.TurnosMedico.DTO.ObraSocial;

import com.Turnos.TurnosMedico.model.ObraSocial;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObraSocialMapper {
    public ObraSocialGetDTO toDTO(ObraSocial obraSocial) {
        return new ObraSocialGetDTO(
                obraSocial.getId(),
                obraSocial.getNombre(),
                obraSocial.getCodigo(),
                obraSocial.getTelefono(),
                obraSocial.getEmail()
        );
    }

    public ObraSocial toEntity(ObraSocialPostDTO postDTO) {
        return ObraSocial.builder()
                .nombre(postDTO.nombre())
                .codigo(postDTO.codigo())
                .telefono(postDTO.telefono())
                .email(postDTO.email())
                .activo(true)
                .build();
    }

    public void updateEntityFromDTO(ObraSocialUpdateDTO updateDTO, ObraSocial obraSocial) {
        if (updateDTO.nombre() != null) {
            obraSocial.setNombre(updateDTO.nombre());
        }
        if (updateDTO.codigo() != null) {
            obraSocial.setCodigo(updateDTO.codigo());
        }
        if (updateDTO.email() != null) {
            obraSocial.setEmail(updateDTO.email());
        }
        if (updateDTO.telefono() != null) {
            obraSocial.setTelefono(updateDTO.telefono());
        }
    }

    public List<ObraSocialGetDTO> toDTOList(List<ObraSocial> obraSocials) {
        return obraSocials.stream().filter(ObraSocial::isActivo).map(this::toDTO).toList();
    }
}
