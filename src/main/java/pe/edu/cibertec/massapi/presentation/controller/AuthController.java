package pe.edu.cibertec.massapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.massapi.presentation.dto.LoginRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.presentation.dto.UsuarioDTO;
import pe.edu.cibertec.massapi.service.implementation.UsuarioService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autenticacion")
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    private ResponseEntity<Respuesta> registerUser(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(this.usuarioService.registrarUsuario(usuarioDTO));
    }


    @PostMapping("/login")
    private ResponseEntity<Respuesta> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(this.usuarioService.loginUsuario(loginRequest));
    }

}
