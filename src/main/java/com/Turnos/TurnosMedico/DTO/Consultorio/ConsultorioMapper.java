package com.Turnos.TurnosMedico.DTO.Consultorio;

import com.Turnos.TurnosMedico.model.Consultorio;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultorioMapper {
    public ConsultorioGetDTO toDTO(Consultorio consultorio) {
        ConsultorioGetDTO dto = new ConsultorioGetDTO();
        dto.setId(consultorio.getId());
        dto.setDescripcion(consultorio.getDescripcion());
        dto.setPiso(consultorio.getPiso());
        dto.setNumero(consultorio.getNumero());
        dto.setActivo(consultorio.getActivo());
        return dto;
    }

    public Consultorio toEntity(ConsultorioPostDTO postDTO) {
        Consultorio consultorio = new Consultorio();
        consultorio.setDescripcion(postDTO.getDescripcion());
        consultorio.setPiso(postDTO.getPiso());
        consultorio.setNumero(postDTO.getNumero());
        consultorio.setActivo(true);
        return consultorio;
    }

    public Consultorio updateEntityFromDTO(ConsultorioUpdateDTO updateDTO, Consultorio consultorio) {
        if (updateDTO.getDescripcion() != null) {
            consultorio.setDescripcion(updateDTO.getDescripcion());
        }
        if (updateDTO.getNumero() != null) {
            consultorio.setNumero(updateDTO.getNumero());
        }
        if (updateDTO.getPiso() != null) {
            consultorio.setPiso(updateDTO.getPiso());
        }
        return consultorio;
    }
    public List<ConsultorioGetDTO> toDTOList(List<Consultorio> consultorios) {
        return consultorios.stream().filter(Consultorio::getActivo).map(this::toDTO).toList();
    }
}
