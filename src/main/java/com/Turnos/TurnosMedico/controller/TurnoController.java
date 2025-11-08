package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Turno.TurnoGetDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoPostDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoUpdateDTO;
import com.Turnos.TurnosMedico.Util.CustomApiResponse;
import com.Turnos.TurnosMedico.interfaz.ITurno;
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
@RequestMapping("turnos")
@Tag(name = "Turnos", description = "Controlador para operaciones de turnos médicos")
public class TurnoController {

    @Autowired
    private ITurno turnoService;

    @Operation(summary = "Crear nuevo turno", description = "Registra un nuevo turno médico en el sistema con validación de disponibilidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turno creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o conflicto de horario"),
            @ApiResponse(responseCode = "404", description = "Paciente o profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<TurnoGetDTO>> create(
            @Parameter(description = "Datos del turno a crear", required = true)
            @Valid @RequestBody TurnoPostDTO turnoPostDTO) {
        TurnoGetDTO dto = turnoService.create(turnoPostDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Turno creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los turnos", description = "Devuelve una lista con todos los turnos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turnos recuperados exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay turnos registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<TurnoGetDTO>>> findAll() {
        List<TurnoGetDTO> turnos = turnoService.findAll();
        String message = turnos.isEmpty() ? "No hay turnos registrados" : "Turnos recuperados exitosamente";
        return new ResponseEntity<>(new CustomApiResponse<>(message, turnos, true), HttpStatus.OK);
    }

    @Operation(summary = "Obtener turno por ID", description = "Devuelve un turno específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<TurnoGetDTO>> findById(
            @Parameter(description = "ID del turno a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        return turnoService.findById(id)
                .map(turno -> new ResponseEntity<>(new CustomApiResponse<>("Turno encontrado", turno, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new CustomApiResponse<>("Turno no encontrado", null, false), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar turno existente", description = "Actualiza la información de un turno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<TurnoGetDTO>> update(
            @Parameter(description = "ID del turno a actualizar", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del turno", required = true)
            @Valid @RequestBody TurnoUpdateDTO turnoUpdateDTO) {
        TurnoGetDTO dto = turnoService.update(id, turnoUpdateDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Turno actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar turno", description = "Da de baja un turno del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno dado de baja exitosamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<TurnoGetDTO>> delete(
            @Parameter(description = "ID del turno a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        TurnoGetDTO dto = turnoService.delete(id);
        return new ResponseEntity<>(new CustomApiResponse<>("Turno dado de baja exitosamente", dto, true), HttpStatus.OK);
    }
}