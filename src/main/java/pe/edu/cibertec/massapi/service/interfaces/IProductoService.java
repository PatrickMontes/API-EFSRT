package pe.edu.cibertec.massapi.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;

import java.math.BigDecimal;

public interface IProductoService {

    Respuesta crearProducto(Long categoriaId, MultipartFile imagen, String nombre, String descripcion, BigDecimal precio);
    Respuesta actualizarProducto(Long productoId, Long categoriaId, MultipartFile imagen, String nombre, String descripcion, BigDecimal precio);
    Respuesta eliminarProducto(Long productoId);
    Respuesta getProductoPorId(Long productoId);
    Respuesta getAllProductos();
    Respuesta getProductosPorCategoria(Long categoriaId);
    Respuesta buscarProducto(String valor);

}
