package com.Turnos.TurnosMedico.repositroy;

import com.Turnos.TurnosMedico.Util.RolUsuario;
import com.Turnos.TurnosMedico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByDniAndActivoTrue(String dni);

    Optional<Usuario> findByEmail(String email);

    Integer countByRol(RolUsuario rol);
}
