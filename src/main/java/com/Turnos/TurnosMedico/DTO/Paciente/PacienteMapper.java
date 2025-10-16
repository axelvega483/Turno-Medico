package com.Turnos.TurnosMedico.DTO.Paciente;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialMapper;
import com.Turnos.TurnosMedico.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    @Autowired
    private ObraSocialMapper mapper;
    public PacienteGetDTO toDTO(Paciente paciente) {
        PacienteGetDTO dto = new PacienteGetDTO();
        dto.setId(paciente.getId());
        dto.setDni(paciente.getDni());
        dto.setActivo(paciente.getActivo());
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
}
