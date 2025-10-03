package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.Util.Disponibilidad;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionalUpdateDTO {
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
    private Disponibilidad disponibilidad;

    @Pattern(regexp = "^$|^\\+?[0-9\\s-]{10,}$", message = "El formato del teléfono no es válido")
    private String telefono;

    @Email(message = "El formato del email no es válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;

    @NotNull(message = "El horario de inicio es obligatorio")
    private LocalTime horarioInicio;

    @NotNull(message = "El horario de fin es obligatorio")
    private LocalTime horarioFin;

    @Min(value = 15, message = "La duración del turno debe ser de al menos 15 minutos")
    @Max(value = 120, message = "La duración del turno no puede exceder 120 minutos")
    private Integer duracionTurnoMinutos;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
}
