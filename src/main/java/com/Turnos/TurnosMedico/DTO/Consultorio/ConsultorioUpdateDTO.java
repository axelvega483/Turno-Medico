package com.Turnos.TurnosMedico.DTO.Consultorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultorioUpdateDTO {
    @NotBlank(message = "El número de consultorio es obligatorio")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "El número debe contener solo letras mayúsculas, números y guiones")
    private String numero;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String descripcion;

    @Size(max = 50, message = "El piso no puede exceder los 50 caracteres")
    private String piso;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
}
