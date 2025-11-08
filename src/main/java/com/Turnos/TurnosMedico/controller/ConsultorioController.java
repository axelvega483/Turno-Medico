package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioPostDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioUpdateDTO;
import com.Turnos.TurnosMedico.Util.CustomApiResponse;
import com.Turnos.TurnosMedico.interfaz.IConsultorio;
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
@RequestMapping("consultorios")
@Tag(name = "Consultorios", description = "Controlador para operaciones de consultorios médicos")
public class ConsultorioController {

    @Autowired
    private IConsultorio consultorioService;

    @Operation(summary = "Crear nuevo consultorio", description = "Registra un nuevo consultorio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consultorio creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<ConsultorioGetDTO>> create(
            @Parameter(description = "Datos del consultorio a crear", required = true)
            @Valid @RequestBody ConsultorioPostDTO consultorioDTO) {
        ConsultorioGetDTO dto = consultorioService.create(consultorioDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Consultorio creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los consultorios", description = "Devuelve una lista con todos los consultorios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultorios recuperados exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay consultorios registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<ConsultorioGetDTO>>> findAll() {
        List<ConsultorioGetDTO> consultorios = consultorioService.findAll();
        String message = consultorios.isEmpty() ? "No hay consultorios registrados" : "Consultorios recuperados exitosamente";
        return new ResponseEntity<>(new CustomApiResponse<>(message, consultorios, true), HttpStatus.OK);
    }

    @Operation(summary = "Obtener consultorio por ID", description = "Devuelve un consultorio específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultorio encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Consultorio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ConsultorioGetDTO>> findById(
            @Parameter(description = "ID del consultorio a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        return consultorioService.findById(id)
                .map(consultorio -> new ResponseEntity<>(new CustomApiResponse<>("Consultorio encontrado", consultorio, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new CustomApiResponse<>("Consultorio no encontrado", null, false), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar consultorio existente", description = "Actualiza la información de un consultorio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultorio actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Consultorio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ConsultorioGetDTO>> update(
            @Parameter(description = "ID del consultorio a actualizar", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del consultorio", required = true)
            @Valid @RequestBody ConsultorioUpdateDTO consultorioDTO) {
        ConsultorioGetDTO dto = consultorioService.update(id, consultorioDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Consultorio actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar consultorio", description = "Da de baja un consultorio del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consultorio dado de baja exitosamente"),
            @ApiResponse(responseCode = "404", description = "Consultorio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ConsultorioGetDTO>> delete(
            @Parameter(description = "ID del consultorio a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        ConsultorioGetDTO dto = consultorioService.delete(id);
        return new ResponseEntity<>(new CustomApiResponse<>("Consultorio dado de baja exitosamente", dto, true), HttpStatus.OK);
    }
}