package com.Turnos.TurnosMedico.interfaz;


import com.Turnos.TurnosMedico.DTO.Usuario.UsuarioGetDTO;
import com.Turnos.TurnosMedico.DTO.Usuario.UsuarioPostDTO;
import com.Turnos.TurnosMedico.DTO.Usuario.UsuarioPutDTO;
import com.Turnos.TurnosMedico.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuario {

    Usuario guardar(Usuario usuario);

    UsuarioGetDTO crear(UsuarioPostDTO post);

    Optional<UsuarioGetDTO> obtener(Integer id);

    List<UsuarioGetDTO> listar();

    UsuarioGetDTO actualizar(Integer id, UsuarioPutDTO put);

    UsuarioGetDTO eliminar(Integer id);

    Boolean existe(String dni);
}
