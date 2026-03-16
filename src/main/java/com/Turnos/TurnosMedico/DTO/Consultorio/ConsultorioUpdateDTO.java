package com.Turnos.TurnosMedico.DTO.Consultorio;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ConsultorioUpdateDTO(
        @Pattern(regexp = "^[A-Z0-9-]+$", message = "El número debe contener solo letras mayúsculas, números y guiones")
        String numero,

        @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
        String descripcion,

        @Size(max = 50, message = "El piso no puede exceder los 50 caracteres")
        String piso) {

}
