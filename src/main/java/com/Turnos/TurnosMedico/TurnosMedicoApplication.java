package com.Turnos.TurnosMedico;

import com.Turnos.TurnosMedico.DTO.Usuario.UsuarioGetDTO;
import com.Turnos.TurnosMedico.Util.RolUsuario;
import com.Turnos.TurnosMedico.interfaz.IUsuario;
import com.Turnos.TurnosMedico.model.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TurnosMedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnosMedicoApplication.class, args);
	}
	@Bean
	CommandLineRunner initDatabase(IUsuario usuarioService
	) {
		return args -> {
			List<UsuarioGetDTO> usuarios = usuarioService.listar();
			if (usuarios.isEmpty()) {
				Usuario usuarioADMIN = new Usuario();
				usuarioADMIN.setRol(RolUsuario.ADMIN);
				usuarioADMIN.setNombre("ADMIN");
				usuarioADMIN.setPassword("adminadmin");
				usuarioADMIN.setEmail("admin@admin.com");
				usuarioADMIN.setDni("12345678");
				usuarioADMIN.setActivo(true);
				usuarioService.guardar(usuarioADMIN);
				System.out.println("Usuario administrador inicializado con éxito.");
			}
		};
	}
}
