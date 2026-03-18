package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialGetDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialPostDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiRespons;
import com.Turnos.TurnosMedico.interfaz.IObraSocial;
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
@RequestMapping("obrasociales")
@Tag(name = "Obras Sociales", description = "Controlador para operaciones de obras sociales")
public class ObraSocialController {

    @Autowired
    private IObraSocial obraSocialService;

    @Operation(summary = "Crear nueva obra social", description = "Registra una nueva obra social en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Obra social creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<ApiRespons<ObraSocialGetDTO>> create(
            @Parameter(description = "Datos de la obra social a crear", required = true)
            @Valid @RequestBody ObraSocialPostDTO obraSocialDTO) {
        ObraSocialGetDTO dto = obraSocialService.create(obraSocialDTO);
        return new ResponseEntity<>(ApiRespons.ok("Obra social creada exitosamente", dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas las obras sociales", description = "Devuelve una lista con todas las obras sociales registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obras sociales recuperadas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay obras sociales registradas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<ApiRespons<List<ObraSocialGetDTO>>> findAll() {
        List<ObraSocialGetDTO> obrasSociales = obraSocialService.findAll();
        String message = obrasSociales == null || obrasSociales.isEmpty() ? "No hay obras sociales registradas" : "Obras sociales recuperadas exitosamente";
        return new ResponseEntity<>(ApiRespons.ok(message, obrasSociales), HttpStatus.OK);
    }

    @Operation(summary = "Obtener obra social por ID", description = "Devuelve una obra social específica basada en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obra social encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Obra social no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiRespons<ObraSocialGetDTO>> findById(
            @Parameter(description = "ID de la obra social a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        return obraSocialService.findById(id)
                .map(obraSocial -> new ResponseEntity<>(new ApiRespons<>("Obra social encontrada", obraSocial, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(ApiRespons.ok("Obra social no encontrada", null), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar obra social existente", description = "Actualiza la información de una obra social existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obra social actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Obra social no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiRespons<ObraSocialGetDTO>> update(
            @Parameter(description = "ID de la obra social a actualizar", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados de la obra social", required = true)
            @Valid @RequestBody ObraSocialUpdateDTO obraSocialDTO) {
        ObraSocialGetDTO dto = obraSocialService.update(id, obraSocialDTO);
        return new ResponseEntity<>(ApiRespons.ok("Obra social actualizada exitosamente", dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar obra social", description = "Da de baja una obra social del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obra social dada de baja exitosamente"),
            @ApiResponse(responseCode = "404", description = "Obra social no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespons<ObraSocialGetDTO>> delete(
            @Parameter(description = "ID de la obra social a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        ObraSocialGetDTO dto = obraSocialService.delete(id);
        return new ResponseEntity<>(ApiRespons.ok("Obra social dada de baja exitosamente", dto), HttpStatus.OK);
    }
}