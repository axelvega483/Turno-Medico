package com.Turnos.TurnosMedico.DTO.Usuario;

import com.Turnos.TurnosMedico.Util.RolUsuario;

public record UsuarioPutDTO(String nombre,
                            String email,
                            String dni,
                            String password,
                            RolUsuario rol) {
}