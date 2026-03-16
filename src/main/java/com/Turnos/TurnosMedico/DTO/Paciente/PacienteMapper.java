package com.Turnos.TurnosMedico.DTO.Paciente;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialMapper;
import com.Turnos.TurnosMedico.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PacienteMapper {
    @Autowired
    private ObraSocialMapper mapper;


    public PacienteGetDTO toDTO(Paciente paciente) {
        return new PacienteGetDTO(
                paciente.getId(),
                paciente.getDni(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getFechaNacimiento(),
                paciente.getGenero(),
                paciente.getTelefono(),
                paciente.getEmail(),
                paciente.getDireccion(),
                paciente.getObraSocial() != null ? mapper.toDTO(paciente.getObraSocial()) : null,
                paciente.getNumeroAfiliado(),
                paciente.getFechaRegistro()
        );
    }

    public Paciente toEntity(PacientePostDTO dto) {
        return Paciente.builder()
                .dni(dto.dni())
                .email(dto.email())
                .apellido(dto.apellido())
                .direccion(dto.direccion())
                .fechaNacimiento(dto.fechaNacimiento())
                .nombre(dto.nombre())
                .genero(dto.genero())
                .telefono(dto.telefono())
                .numeroAfiliado(dto.numeroAfiliado())
                .tipoPaciente(dto.tipoPaciente())
                .activo(true)
                .fechaRegistro(LocalDateTime.now())
                .build();

    }

    public void updateEntityFromDTO(PacienteUpdateDTO dto, Paciente paciente) {
        if (dto.dni() != null) {
            paciente.setDni(dto.dni());
        }
        if (dto.nombre() != null) {
            paciente.setNombre(dto.nombre());
        }
        if (dto.apellido() != null) {
            paciente.setApellido(dto.apellido());
        }
        if (dto.fechaNacimiento() != null) {
            paciente.setFechaNacimiento(dto.fechaNacimiento());
        }
        if (dto.genero() != null) {
            paciente.setGenero(dto.genero());
        }
        if (dto.telefono() != null) {
            paciente.setTelefono(dto.telefono());
        }
        if (dto.email() != null) {
            paciente.setEmail(dto.email());
        }
        if (dto.direccion() != null) {
            paciente.setDireccion(dto.direccion());
        }
        if (dto.numeroAfiliado() != null) {
            paciente.setNumeroAfiliado(dto.numeroAfiliado());
        }
        if (dto.tipoPaciente() != null) {
            paciente.setTipoPaciente(dto.tipoPaciente());
        }
    }

    public List<PacienteGetDTO> toDTOList(List<Paciente> pacientes) {
        return pacientes.stream().filter(Paciente::isActivo).map(this::toDTO).toList();
    }
}
