package pe.edu.cibertec.massapi.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.massapi.persistence.model.Direccion;
import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.persistence.repository.DireccionRepository;
import pe.edu.cibertec.massapi.presentation.dto.DireccionDTO;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.interfaces.IDireccionService;
import pe.edu.cibertec.massapi.util.MapperDTO;

@Service
@RequiredArgsConstructor
public class DireccionService implements IDireccionService {

    private final UsuarioService usuarioService;
    private final DireccionRepository direccionRepository;

    @Override
    public Respuesta guardarAndActualizarDireccion(DireccionDTO direccionDTO) {
        Usuario usuario = this.usuarioService.getLoginUsuario();
        Direccion direccion = usuario.getDireccion();

        if (direccion == null){
            direccion = new Direccion();
            direccion.setUsuario(usuario);
        }

        if (direccionDTO.getCalle() != null) direccion.setCalle(direccionDTO.getCalle());
        if (direccionDTO.getCiudad() != null) direccion.setCiudad(direccionDTO.getCiudad());
        if (direccionDTO.getEstado() != null) direccion.setEstado(direccionDTO.getEstado());
        if (direccionDTO.getCodigoPostal() != null) direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
        if (direccionDTO.getPais() != null) direccion.setPais(direccionDTO.getPais());

        this.direccionRepository.save(direccion);

        String mensaje = (usuario.getDireccion() == null) ? "Direccion creada" : "Direccion actualizada";

        return Respuesta.builder()
                .estado(200)
                .mensaje(mensaje)
                .build();
    }

}
