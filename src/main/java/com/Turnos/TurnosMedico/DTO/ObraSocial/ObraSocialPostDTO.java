package com.Turnos.TurnosMedico.DTO.ObraSocial;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObraSocialPostDTO {
    @NotBlank(message = "El nombre de la obra social es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Size(max = 50, message = "El código no puede exceder los 50 caracteres")
    private String codigo;

    @Pattern(regexp = "^$|^\\+?[0-9\\s-]{10,}$", message = "El formato del teléfono no es válido")
    private String telefono;

    @Email(message = "El formato del email no es válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;

    private Boolean activo = true;
}
