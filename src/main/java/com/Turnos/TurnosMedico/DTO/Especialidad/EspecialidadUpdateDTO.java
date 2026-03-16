package com.Turnos.TurnosMedico.DTO.Especialidad;

import jakarta.validation.constraints.Size;

public record EspecialidadUpdateDTO(
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
        String descripcion) {


}
