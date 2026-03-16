package com.Turnos.TurnosMedico.DTO.Auth;

public record AuthResponseDTO(String username,
                              String mensaje,
                              String token,
                              boolean status) {
}
