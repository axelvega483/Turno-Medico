package com.Turnos.TurnosMedico.DTO.Disponibilidad;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioMapper;
import com.Turnos.TurnosMedico.model.Disponibilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisponibilidadMapper {
    @Autowired
    private ConsultorioMapper mapper;
    public DisponibilidadGetDTO toDTO(Disponibilidad disponibilidad) {
        DisponibilidadGetDTO dto = new DisponibilidadGetDTO();
        dto.setDia(disponibilidad.getDia());
        dto.setHorarioFin(disponibilidad.getHorarioFin());
        dto.setHorarioInicio(disponibilidad.getHorarioInicio());
        if (disponibilidad.getConsultorio() != null) {
            dto.setConsultorio(mapper.toDTO(disponibilidad.getConsultorio()));
        }
        return  dto;
    }
}
