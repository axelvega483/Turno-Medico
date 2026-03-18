package com.Turnos.TurnosMedico.DTO.Usuario;

import com.Turnos.TurnosMedico.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {

    public UsuarioGetDTO toDTO(Usuario usuario) {
        return new UsuarioGetDTO(usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getDni(),
                usuario.getRol());
    }

    public Usuario toEntity(UsuarioPostDTO post) {
        return  Usuario.builder()
                .dni(post.dni())
                .email(post.email())
                .nombre(post.nombre())
                .password(post.password())
                .rol(post.rol())
                .activo(true)
                .build();
    }

    public void fromUpdateDTO(UsuarioPutDTO update, Usuario usuario) {
        if (update.dni() != null) {
            usuario.setDni(update.dni());
        }
        if (update.email() != null) {
            usuario.setEmail(update.email());
        }
        if (update.nombre() != null) {
            usuario.setNombre(update.nombre());
        }
        if (update.password() != null) {
            usuario.setPassword(update.password());
        }
    }

    public List<UsuarioGetDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().filter(Usuario::isActivo).map(this::toDTO).toList();
    }
}
