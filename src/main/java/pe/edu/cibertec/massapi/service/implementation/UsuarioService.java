package pe.edu.cibertec.massapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.massapi.persistence.enums.UsuarioRole;
import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.persistence.repository.UsuarioRepository;
import pe.edu.cibertec.massapi.presentation.dto.LoginRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.presentation.dto.UsuarioDTO;
import pe.edu.cibertec.massapi.security.jwt.JwtUtil;
import pe.edu.cibertec.massapi.service.exception.InvalidCredentialsException;
import pe.edu.cibertec.massapi.service.exception.NotFoundException;
import pe.edu.cibertec.massapi.service.interfaces.IUsuarioService;
import pe.edu.cibertec.massapi.util.MapperDTO;

import java.sql.SQLOutput;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final JwtUtil jwtUtil;
    private final MapperDTO mapperDTO;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Respuesta registrarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioRole usuarioRole = UsuarioRole.USER;

        if (usuarioDTO.getRol() != null && usuarioDTO.getRol().equalsIgnoreCase("admin")){
            usuarioRole = UsuarioRole.ADMIN;
        }

        Usuario usuario = Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .email(usuarioDTO.getEmail())
                .contrasena(usuarioDTO.getContrasena())
                .numeroTelefono(usuarioDTO.getNumeroTelefono())
                .rol(usuarioRole)
                .build();

        Usuario guardarUsuario = this.usuarioRepository.save(usuario);
        System.out.println(guardarUsuario);
        UsuarioDTO dto = this.mapperDTO.usuarioToDTOBasico(guardarUsuario);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Usuario registrado")
                .rol(String.valueOf(usuarioRole))
                .usuario(dto)
                .build();
    }


    @Override
    public Respuesta loginUsuario(LoginRequest loginRequest) {
        Usuario usuario = this.usuarioRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new NoSuchElementException("Email no encontrado")
        );

        if (!passwordEncoder.matches(loginRequest.getContrasena(), usuario.getContrasena())){
            throw new InvalidCredentialsException("Contrasena Incorrecta");
        }

        String token = this.jwtUtil.generarToken(usuario);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Usuario logeado correctamente")
                .token(token)
                .rol(usuario.getRol().name())
                .tiempoExpiracion("6 meses")
                .build();
    }


    @Override
    public Respuesta getAllUsuarios() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOS = usuarios.stream()
                .map(mapperDTO::usuarioToDTOBasico)
                .toList();

        return Respuesta.builder()
                .estado(200)
                .mensaje("Usuarios encontrados")
                .listaUsuarios(usuarioDTOS)
                .build();
    }


    @Override
    public Usuario getLoginUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        log.info("Usuario encontrado: " + email);

        return this.usuarioRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Usuario no encontrado")
        );
    }


    @Override
    public Respuesta getUsuarioInfoAndPedidoHistorial() {
        Usuario usuario = getLoginUsuario();
        UsuarioDTO usuarioDTO = this.mapperDTO.usuarioToDTOPlusDireccionAndPedidoHistorial(usuario);

        return null;
    }
}
