package com.Turnos.TurnosMedico.DTO.Paciente;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialGetDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PacienteGetDTO(
        Integer id,
        String dni,
        String nombre,
        String apellido,
        LocalDate fechaNacimiento,
        String genero,
        String telefono,
        String email,
        String direccion,
        ObraSocialGetDTO obraSocial,
        String numeroAfiliado,
        LocalDateTime fechaRegistro) {


}
