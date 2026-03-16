package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.Util.EstadoDisponible;
import jakarta.validation.constraints.*;


public record ProfesionalUpdateDTO(
        @Pattern(regexp = "^[A-Z0-9-]+$", message = "La matrícula debe contener solo letras mayúsculas, números y guiones")
        String matricula,

        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
        String apellido,

        Integer especialidadId,

        EstadoDisponible disponibilidad,

        @Pattern(regexp = "^$|^\\+?[0-9\\s-]{10,}$", message = "El formato del teléfono no es válido")
        String telefono,

        @Email(message = "El formato del email no es válido")
        @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
        String email) {


}
