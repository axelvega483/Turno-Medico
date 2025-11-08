package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Paciente.PacienteGetDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacientePostDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacienteUpdateDTO;
import com.Turnos.TurnosMedico.Util.CustomApiResponse;
import com.Turnos.TurnosMedico.interfaz.IPaciente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("pacientes")
@Tag(name = "Pacientes", description = "Controlador para operaciones de pacientes")
public class PacienteController {

    @Autowired
    private IPaciente pacienteService;

    @Operation(summary = "Crear nuevo paciente", description = "Registra un nuevo paciente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<PacienteGetDTO>> create(
            @Parameter(description = "Datos del paciente a crear", required = true)
            @Valid @RequestBody PacientePostDTO pacientePostDTO) {
        PacienteGetDTO dto = pacienteService.create(pacientePostDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Paciente creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los pacientes", description = "Devuelve una lista con todos los pacientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacientes recuperados exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay pacientes registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<PacienteGetDTO>>> findAll() {
        List<PacienteGetDTO> dto = pacienteService.findAll();
        String message = dto.isEmpty() ? "No hay pacientes registrados" : "Pacientes recuperados exitosamente";
        return new ResponseEntity<>(new CustomApiResponse<>(message, dto, true), HttpStatus.OK);
    }

    @Operation(summary = "Obtener paciente por ID", description = "Devuelve un paciente específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<PacienteGetDTO>> findById(
            @Parameter(description = "ID del paciente a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        return pacienteService.findById(id)
                .map(paciente -> new ResponseEntity<>(new CustomApiResponse<>("Paciente encontrado", paciente, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new CustomApiResponse<>("Paciente no encontrado", null, false), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar paciente existente", description = "Actualiza la información de un paciente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<PacienteGetDTO>> update(
            @Parameter(description = "ID del paciente a actualizar", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del paciente", required = true)
            @Valid @RequestBody PacienteUpdateDTO updateDTO) {
        PacienteGetDTO dto = pacienteService.update(id, updateDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Paciente actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar paciente", description = "Da de baja un paciente del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente dado de baja exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<PacienteGetDTO>> delete(
            @Parameter(description = "ID del paciente a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        PacienteGetDTO dto = pacienteService.delete(id);
        return new ResponseEntity<>(new CustomApiResponse<>("Paciente dado de baja exitosamente", dto, true), HttpStatus.OK);
    }
}