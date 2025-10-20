package com.Turnos.TurnosMedico.DTO.Especialidad;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.model.Consultorio;
import com.Turnos.TurnosMedico.model.Especialidad;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Especialidad toEntity(EspecialidadPostDTO post) {
        Especialidad especialidad = new Especialidad();
        especialidad.setActivo(post.getActivo());
        especialidad.setNombre(post.getNombre());
        especialidad.setDescripcion(post.getDescripcion());
        return especialidad;
    }

    public Especialidad updateEntityFromDTO(EspecialidadUpdateDTO updateDTO, Especialidad especialidad) {
        if (updateDTO.getNombre() != null) {
            especialidad.setNombre(updateDTO.getNombre());
        }
        if (updateDTO.getDescripcion() != null) {
            especialidad.setDescripcion(updateDTO.getDescripcion());
        }
        if (updateDTO.getActivo() != null) {
            especialidad.setActivo(updateDTO.getActivo());
        }
        return especialidad;
    }

    public List<EspecialidadGetDTO> toDTOList(List<Especialidad> especialidades) {
        return especialidades.stream().filter(Especialidad::getActivo).map(this::toDTO).toList();
    }
}
