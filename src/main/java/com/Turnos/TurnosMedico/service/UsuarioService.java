package com.Turnos.TurnosMedico.service;

import com.Turnos.TurnosMedico.DTO.Usuario.*;
import com.Turnos.TurnosMedico.Util.RolUsuario;
import com.Turnos.TurnosMedico.interfaz.IUsuario;
import com.Turnos.TurnosMedico.model.Usuario;
import com.Turnos.TurnosMedico.repositroy.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuario {

    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private UsuarioMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario guardar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repo.save(usuario);
    }

    @Override
    public UsuarioGetDTO crear(UsuarioPostDTO post) {
        if (existe(post.dni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }
        Usuario usuario = mapper.toEntity(post);
        usuario.setPassword(passwordEncoder.encode(post.password()));
        repo.save(usuario);
        return mapper.toDTO(usuario);
    }

    @Override
    public Optional<UsuarioGetDTO> obtener(Integer id) {
        return repo.findById(id).filter(Usuario::isActivo).map(mapper::toDTO);
    }

    @Override
    public List<UsuarioGetDTO> listar() {
        return mapper.toDTOList(repo.findAll());
    }

    @Override
    public UsuarioGetDTO eliminar(Integer id) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.setActivo(false);
        repo.save(usuario);
        return mapper.toDTO(usuario);
    }

    @Override
    public UsuarioGetDTO actualizar(Integer id, UsuarioPutDTO put) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        mapper.fromUpdateDTO(put, usuario);
        usuario.setActivo(true);
        if (put.password() != null && !put.password().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(put.password()));
        }
        repo.save(usuario);
        return mapper.toDTO(usuario);
    }

    @Override
    public UsuarioGetDTO actualizarRol(Integer id, UsuarioRolDTO dto) {
        if (dto.rol() == null) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Usuario usuarioLogueado = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));
        if (usuarioLogueado.getId().equals(id)) {
            throw new IllegalStateException("No podés cambiar tu propio rol");
        }
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (usuario.getRol() == RolUsuario.ADMIN && dto.rol() != RolUsuario.ADMIN) {
            long admins = repo.countByRol(RolUsuario.ADMIN);
            if (admins <= 1) {
                throw new IllegalStateException("Debe existir al menos un ADMIN");
            }
        }

        usuario.setRol(dto.rol());
        repo.save(usuario);

        return mapper.toDTO(usuario);
    }

    @Override
    public Boolean existe(String dni) {
        return repo.existsByDniAndActivoTrue(dni);
    }

}
