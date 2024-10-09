package pe.edu.cibertec.massapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.massapi.presentation.dto.DireccionDTO;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.implementation.DireccionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class DireccionController {

    private final DireccionService direccionService;

    @PostMapping("/save")
    public ResponseEntity<Respuesta> save(@RequestBody DireccionDTO direccionDTO) {
        return ResponseEntity.ok(this.direccionService.guardarAndActualizarDireccion(direccionDTO));
    }

}
