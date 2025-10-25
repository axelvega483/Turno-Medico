package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioMapper;
import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadMapper;
import com.Turnos.TurnosMedico.model.Disponibilidad;
import com.Turnos.TurnosMedico.model.Profesional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfesionalMapper {
    @Autowired
    private EspecialidadMapper mapper;
    @Autowired
    private ConsultorioMapper consultorioMapper;


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
        if (profesional.getDisponibilidades() != null && !profesional.getDisponibilidades().isEmpty()) {
            dto.setDisponibilidades(disponibilidadesToDTO(profesional.getDisponibilidades()));
        }
        dto.setTelefono(profesional.getTelefono());
        return dto;
    }

    public Profesional toEntity(ProfesionalPostDTO dto) {
        Profesional profesional = new Profesional();
        profesional.setApellido(dto.getApellido());
        profesional.setNombre(dto.getNombre());
        profesional.setEstadoDisponible(dto.getDisponibilidad());
        profesional.setEmail(dto.getEmail());
        profesional.setMatricula(dto.getMatricula());
        profesional.setActivo(dto.getActivo());
        profesional.setTelefono(dto.getTelefono());
        return profesional;
    }

    public Profesional updateEntityFromDTO(ProfesionalUpdateDTO dto, Profesional profesional) {
        if (dto.getMatricula() != null) {
            profesional.setMatricula(dto.getMatricula());
        }
        if (dto.getNombre() != null) {
            profesional.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            profesional.setApellido(dto.getApellido());
        }
        if (dto.getEmail() != null) {
            profesional.setEmail(dto.getEmail());
        }
        if (dto.getTelefono() != null) {
            profesional.setTelefono(dto.getTelefono());
        }
        if (dto.getDisponibilidad() != null) {
            profesional.setEstadoDisponible(dto.getDisponibilidad());
        }
        return profesional;
    }

    public List<ProfesionalGetDTO> toDTOList(List<Profesional> profesionales) {
        return profesionales.stream().filter(Profesional::getActivo).map(this::toDTO).toList();
    }
    public List<DisponibilidadGetDTO> disponibilidadesToDTO(List<Disponibilidad> disponibilidades) {
        return disponibilidades.stream()
                .map(this::disponibilidadToDTO)
                .collect(Collectors.toList());
    }

    public DisponibilidadGetDTO disponibilidadToDTO(Disponibilidad disponibilidad) {
        DisponibilidadGetDTO dto = new DisponibilidadGetDTO();
        dto.setDia(disponibilidad.getDia());
        dto.setHorarioInicio(disponibilidad.getHorarioInicio());
        dto.setHorarioFin(disponibilidad.getHorarioFin());
        if(disponibilidad.getConsultorio() != null){
            dto.setConsultorio(consultorioMapper.toDTO(disponibilidad.getConsultorio()));
        }
        return dto;
    }
}
