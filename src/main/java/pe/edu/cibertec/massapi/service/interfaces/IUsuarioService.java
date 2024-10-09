package pe.edu.cibertec.massapi.service.interfaces;

import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.presentation.dto.LoginRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.presentation.dto.UsuarioDTO;

public interface IUsuarioService {

    Respuesta registrarUsuario(UsuarioDTO usuarioDTO);
    Respuesta loginUsuario(LoginRequest loginRequest);
    Respuesta getAllUsuarios();
    Usuario getLoginUsuario();
    Respuesta getUsuarioInfoAndPedidoHistorial();

}
