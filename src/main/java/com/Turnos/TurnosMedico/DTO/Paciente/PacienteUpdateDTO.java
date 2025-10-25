package com.Turnos.TurnosMedico.DTO.Paciente;

import com.Turnos.TurnosMedico.Util.TipoPaciente;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteUpdateDTO {

    @Pattern(regexp = "^[0-9]{7,8}$", message = "El DNI debe contener 7 u 8 dígitos")
    private String dni;

    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;


    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")

    private LocalDate fechaNacimiento;

    @Pattern(regexp = "^(MASCULINO|FEMENINO|OTRO)$", message = "El género debe ser MASCULINO, FEMENINO u OTRO")
    private String genero;

    @Pattern(regexp = "^$|^\\+?[0-9\\s-]{10,}$", message = "El formato del teléfono no es válido")
    private String telefono;

    @Email(message = "El formato del email no es válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;

    @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
    private String direccion;

    private Integer obraSocialId;

    @Size(max = 50, message = "El número de afiliado no puede exceder los 50 caracteres")
    private String numeroAfiliado;

    private TipoPaciente tipoPaciente;
}
