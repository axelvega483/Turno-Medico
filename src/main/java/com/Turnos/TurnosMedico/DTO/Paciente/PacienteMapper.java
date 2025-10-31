package com.Turnos.TurnosMedico.DTO.Paciente;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialMapper;
import com.Turnos.TurnosMedico.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacienteMapper {
    @Autowired
    private ObraSocialMapper mapper;


    public PacienteGetDTO toDTO(Paciente paciente) {
        PacienteGetDTO dto = new PacienteGetDTO();
        dto.setId(paciente.getId());
        dto.setDni(paciente.getDni());
        dto.setActivo(paciente.isActivo());
        dto.setEmail(paciente.getEmail());
        dto.setApellido(paciente.getApellido());
        dto.setDireccion(paciente.getDireccion());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setNombre(paciente.getNombre());
        dto.setFechaRegistro(paciente.getFechaRegistro());
        dto.setGenero(paciente.getGenero());
        dto.setTelefono(paciente.getTelefono());
        dto.setNumeroAfiliado(paciente.getNumeroAfiliado());
        if (paciente.getObraSocial() != null) {
            dto.setObraSocial(mapper.toDTO(paciente.getObraSocial()));
        }
        return dto;
    }

    public Paciente toEntity(PacientePostDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setDni(dto.getDni());
        paciente.setEmail(dto.getEmail());
        paciente.setApellido(dto.getApellido());
        paciente.setDireccion(dto.getDireccion());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setNombre(dto.getNombre());
        paciente.setGenero(dto.getGenero());
        paciente.setTelefono(dto.getTelefono());
        paciente.setNumeroAfiliado(dto.getNumeroAfiliado());
        paciente.setTipoPaciente(dto.getTipoPaciente());
        paciente.prePersist();
        return paciente;
    }
    public Paciente updateEntityFromDTO(PacienteUpdateDTO dto, Paciente paciente) {
        if (dto.getDni() != null) {
            paciente.setDni(dto.getDni());
        }
        if (dto.getNombre() != null) {
            paciente.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            paciente.setApellido(dto.getApellido());
        }
        if (dto.getFechaNacimiento() != null) {
            paciente.setFechaNacimiento(dto.getFechaNacimiento());
        }
        if (dto.getGenero() != null) {
            paciente.setGenero(dto.getGenero());
        }
        if (dto.getTelefono() != null) {
            paciente.setTelefono(dto.getTelefono());
        }
        if (dto.getEmail() != null) {
            paciente.setEmail(dto.getEmail());
        }
        if (dto.getDireccion() != null) {
            paciente.setDireccion(dto.getDireccion());
        }
        if (dto.getNumeroAfiliado() != null) {
            paciente.setNumeroAfiliado(dto.getNumeroAfiliado());
        }
        if( dto.getTipoPaciente() != null) {
            paciente.setTipoPaciente(dto.getTipoPaciente());
        }
        return paciente;
    }
    public List<PacienteGetDTO>toDTOList(List<Paciente> pacientes){
        return pacientes.stream().filter(Paciente::isActivo).map(this::toDTO).toList();
    }
}
