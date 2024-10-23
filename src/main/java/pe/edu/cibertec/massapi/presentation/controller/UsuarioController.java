package pe.edu.cibertec.massapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.implementation.UsuarioService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> getAllUsuarios() {
        return ResponseEntity.ok(this.usuarioService.getAllUsuarios());
    }


    @GetMapping("/miInformacion")
    public ResponseEntity<Respuesta> getUsuarioInfoAndPedidoHistorial() {
        return ResponseEntity.ok(this.usuarioService.getUsuarioInfoAndPedidoHistorial());
    }

}
