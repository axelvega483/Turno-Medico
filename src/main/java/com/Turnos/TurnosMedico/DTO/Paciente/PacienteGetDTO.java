package com.Turnos.TurnosMedico.DTO.Paciente;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialGetDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PacienteGetDTO {
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String genero;
    private String telefono;
    private String email;
    private String direccion;
    private ObraSocialGetDTO obraSocial;
    private String numeroAfiliado;
    private LocalDateTime fechaRegistro;
    private boolean activo;

}
