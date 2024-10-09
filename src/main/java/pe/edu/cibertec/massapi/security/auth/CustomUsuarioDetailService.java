package pe.edu.cibertec.massapi.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.persistence.repository.UsuarioRepository;
import pe.edu.cibertec.massapi.service.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUsuarioDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByEmail(username).orElseThrow(
                () -> new NotFoundException("Correo no encontrado")
        );

        return AuthUsuario.builder()
                .usuario(usuario)
                .build();
    }

}
