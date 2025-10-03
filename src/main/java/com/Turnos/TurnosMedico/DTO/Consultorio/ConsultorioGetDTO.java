package com.Turnos.TurnosMedico.DTO.Consultorio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultorioGetDTO {
    private Integer id;
    private String numero;
    private String descripcion;
    private String piso;
    private Boolean activo;
}
