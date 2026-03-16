package com.Turnos.TurnosMedico.DTO.Especialidad;

import com.Turnos.TurnosMedico.model.Especialidad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EspecialidadMapper {
    public EspecialidadGetDTO toDTO(Especialidad especialidad) {
        return new EspecialidadGetDTO(
                especialidad.getId(),
                especialidad.getNombre(),
                especialidad.getDescripcion()
        );
    }

    public Especialidad toEntity(EspecialidadPostDTO post) {
        return Especialidad.builder()
                        .nombre(post.nombre())
                        .descripcion(post.descripcion())
                        .activo(true)
                        .build();
    }

    public void updateEntityFromDTO(EspecialidadUpdateDTO updateDTO, Especialidad especialidad) {
        if (updateDTO.nombre() != null) {
            especialidad.setNombre(updateDTO.nombre());
        }
        if (updateDTO.descripcion() != null) {
            especialidad.setDescripcion(updateDTO.descripcion());
        }
    }

    public List<EspecialidadGetDTO> toDTOList(List<Especialidad> especialidades) {
        return especialidades.stream().filter(Especialidad::isActivo).map(this::toDTO).toList();
    }
}
