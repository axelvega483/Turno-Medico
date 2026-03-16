package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioMapper;
import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadMapper;
import com.Turnos.TurnosMedico.model.Disponibilidad;
import com.Turnos.TurnosMedico.model.Profesional;
import com.Turnos.TurnosMedico.repositroy.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfesionalMapper {
    @Autowired
    private EspecialidadMapper mapper;
    @Autowired
    private EspecialidadRepository especialidadRepo;
    @Autowired
    private ConsultorioMapper consultorioMapper;


    public ProfesionalGetDTO toDTO(Profesional profesional) {
        return new ProfesionalGetDTO(
                profesional.getId(),
                profesional.getMatricula(),
                profesional.getNombre(),
                profesional.getApellido(),
                profesional.getEstadoDisponible(),
                profesional.getEspecialidad() != null ? mapper.toDTO(profesional.getEspecialidad()) : null,
                profesional.getDisponibilidades() != null ? disponibilidadesToDTO(profesional.getDisponibilidades()) : null,
                profesional.getTelefono(),
                profesional.getEmail()
        );
    }

    public Profesional toEntity(ProfesionalPostDTO dto) {
        return Profesional.builder()
                .apellido(dto.apellido())
                .nombre(dto.nombre())
                .estadoDisponible(dto.disponibilidad())
                .email(dto.email())
                .matricula(dto.matricula())
                .activo(true)
                .telefono(dto.telefono())
                .especialidad(dto.especialidadId() != null ? especialidadRepo.findById(dto.especialidadId())
                        .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"))
                        : null)
                .build();
    }

    public void updateEntityFromDTO(ProfesionalUpdateDTO dto, Profesional profesional) {
        if (dto.matricula() != null) {
            profesional.setMatricula(dto.matricula());
        }
        if (dto.nombre() != null) {
            profesional.setNombre(dto.nombre());
        }
        if (dto.apellido() != null) {
            profesional.setApellido(dto.apellido());
        }
        if (dto.email() != null) {
            profesional.setEmail(dto.email());
        }
        if (dto.telefono() != null) {
            profesional.setTelefono(dto.telefono());
        }
        if (dto.disponibilidad() != null) {
            profesional.setEstadoDisponible(dto.disponibilidad());
        }
    }

    public List<ProfesionalGetDTO> toDTOList(List<Profesional> profesionales) {
        return profesionales.stream().filter(Profesional::isActivo).map(this::toDTO).toList();
    }

    public List<DisponibilidadGetDTO> disponibilidadesToDTO(List<Disponibilidad> disponibilidades) {
        return disponibilidades.stream()
                .map(this::disponibilidadToDTO)
                .collect(Collectors.toList());
    }

    public DisponibilidadGetDTO disponibilidadToDTO(Disponibilidad disponibilidad) {
        return new DisponibilidadGetDTO(
                disponibilidad.getDia(),
                disponibilidad.getHorarioInicio(),
                disponibilidad.getHorarioFin(),
                disponibilidad.getConsultorio() != null ? consultorioMapper.toDTO(disponibilidad.getConsultorio()) : null


        );

    }
}
