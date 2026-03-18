package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadPostDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiRespons;
import com.Turnos.TurnosMedico.interfaz.IEspecialidad;
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
@RequestMapping("especialidad")
@Tag(name = "Especialidades", description = "Controlador para operaciones de especialidades médicas")
public class EspecialidadController {

    @Autowired
    private IEspecialidad especialidadService;

    @Operation(summary = "Crear nueva especialidad", description = "Registra una nueva especialidad médica en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Especialidad creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<ApiRespons<EspecialidadGetDTO>> create(
            @Parameter(description = "Datos de la especialidad a crear", required = true)
            @Valid @RequestBody EspecialidadPostDTO especialidadDTO) {
        EspecialidadGetDTO dto = especialidadService.create(especialidadDTO);
        return new ResponseEntity<>(ApiRespons.ok("Especialidad creada exitosamente", dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas las especialidades", description = "Devuelve una lista con todas las especialidades médicas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidades recuperadas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay especialidades registradas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<ApiRespons<List<EspecialidadGetDTO>>> findAll() {
        List<EspecialidadGetDTO> especialidades = especialidadService.findAll();
        String message = especialidades.isEmpty() ? "No hay especialidades registradas" : "Especialidades recuperadas exitosamente";
        return new ResponseEntity<>(ApiRespons.ok(message, especialidades), HttpStatus.OK);
    }

    @Operation(summary = "Obtener especialidad por ID", description = "Devuelve una especialidad específica basada en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidad encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiRespons<EspecialidadGetDTO>> findById(
            @Parameter(description = "ID de la especialidad a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        return especialidadService.findById(id)
                .map(especialidad -> new ResponseEntity<>(new ApiRespons<>("Especialidad encontrada", especialidad, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(ApiRespons.ok("Especialidad no encontrada", null), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar especialidad existente", description = "Actualiza la información de una especialidad existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidad actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiRespons<EspecialidadGetDTO>> update(
            @Parameter(description = "ID de la especialidad a actualizar", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados de la especialidad", required = true)
            @Valid @RequestBody EspecialidadUpdateDTO especialidadDTO) {
        EspecialidadGetDTO dto = especialidadService.update(id, especialidadDTO);
        return new ResponseEntity<>(ApiRespons.ok("Especialidad actualizada exitosamente", dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar especialidad", description = "Da de baja una especialidad del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidad dada de baja exitosamente"),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespons<EspecialidadGetDTO>> delete(
            @Parameter(description = "ID de la especialidad a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        EspecialidadGetDTO dto = especialidadService.delete(id);
        return new ResponseEntity<>(ApiRespons.ok("Especialidad dada de baja exitosamente", dto), HttpStatus.OK);
    }
}