package com.Turnos.TurnosMedico.DTO.Usuario;

import com.Turnos.TurnosMedico.Util.RolUsuario;

public record UsuarioGetDTO(Integer id,
                            String nombre,
                            String email,
                            String dni,
                            RolUsuario rol) {

}
