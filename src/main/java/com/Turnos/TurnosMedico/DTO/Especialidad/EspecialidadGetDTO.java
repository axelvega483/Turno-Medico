package com.Turnos.TurnosMedico.DTO.Especialidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadGetDTO {
    private Integer id;

    private String nombre;

    private String descripcion;

    private Boolean activo;
}
