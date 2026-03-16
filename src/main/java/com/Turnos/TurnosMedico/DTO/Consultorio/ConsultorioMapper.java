package com.Turnos.TurnosMedico.DTO.Consultorio;

import com.Turnos.TurnosMedico.model.Consultorio;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultorioMapper {
    public ConsultorioGetDTO toDTO(Consultorio consultorio) {
        return new ConsultorioGetDTO(
                consultorio.getId(),
                consultorio.getDescripcion(),
                consultorio.getPiso(),
                consultorio.getNumero()
        );
    }

    public Consultorio toEntity(ConsultorioPostDTO postDTO) {
        return Consultorio.builder()
                .descripcion(postDTO.descripcion())
                .piso(postDTO.piso())
                .numero(postDTO.numero())
                .activo(true)
                .build();
    }

    public void updateEntityFromDTO(ConsultorioUpdateDTO updateDTO, Consultorio consultorio) {
        if (updateDTO.descripcion() != null) {
            consultorio.setDescripcion(updateDTO.descripcion());
        }
        if (updateDTO.numero() != null) {
            consultorio.setNumero(updateDTO.numero());
        }
        if (updateDTO.piso() != null) {
            consultorio.setPiso(updateDTO.piso());
        }
    }
    public List<ConsultorioGetDTO> toDTOList(List<Consultorio> consultorios) {
        return consultorios.stream().filter(Consultorio::isActivo).map(this::toDTO).toList();
    }
}
