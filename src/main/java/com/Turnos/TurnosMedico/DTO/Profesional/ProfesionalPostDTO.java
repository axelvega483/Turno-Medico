package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.Util.EstadoDisponible;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionalPostDTO {
    @NotBlank(message = "La matrícula es obligatoria")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "La matrícula debe contener solo letras mayúsculas, números y guiones")
    private String matricula;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;

    @NotNull(message = "La especialidad es obligatoria")
    private Integer especialidadId;

    @NotNull(message = "La disponibilidad es obligatoria")
    private EstadoDisponible disponibilidad;

    @Pattern(regexp = "^$|^\\+?[0-9\\s-]{10,}$", message = "El formato del teléfono no es válido")
    private String telefono;

    @Email(message = "El formato del email no es válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;

    private Boolean activo = true;
}
