package pe.edu.cibertec.massapi.service.interfaces;

import pe.edu.cibertec.massapi.presentation.dto.DireccionDTO;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;

public interface IDireccionService {

    Respuesta guardarAndActualizarDireccion(DireccionDTO direccion);

}
