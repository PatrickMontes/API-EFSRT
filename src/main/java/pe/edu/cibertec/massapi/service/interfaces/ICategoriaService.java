package pe.edu.cibertec.massapi.service.interfaces;

import pe.edu.cibertec.massapi.presentation.dto.CategoriaDTO;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;

public interface ICategoriaService {

    Respuesta crearCategoria(CategoriaDTO categoria);
    Respuesta actualizarCategoria(Long categoriaId, CategoriaDTO categoria);
    Respuesta getAllCategorias();
    Respuesta getCategoriaPorId(Long categoriaId);
    Respuesta eliminarCategoria(Long categoriaId);

}
