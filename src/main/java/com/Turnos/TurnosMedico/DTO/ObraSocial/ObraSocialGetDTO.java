package com.Turnos.TurnosMedico.DTO.ObraSocial;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObraSocialGetDTO {
    private Integer id;
    private String nombre;
    private String codigo;
    private String telefono;
    private String email;
    private Boolean activo;
}
