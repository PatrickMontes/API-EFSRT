package pe.edu.cibertec.massapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.massapi.persistence.model.Categoria;
import pe.edu.cibertec.massapi.persistence.model.Producto;
import pe.edu.cibertec.massapi.persistence.repository.CategoriaRepository;
import pe.edu.cibertec.massapi.persistence.repository.ProductoRepository;
import pe.edu.cibertec.massapi.presentation.dto.ProductoDTO;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.LocalStorage.LocalStorageService;
import pe.edu.cibertec.massapi.service.exception.NotFoundException;
import pe.edu.cibertec.massapi.service.interfaces.IProductoService;
import pe.edu.cibertec.massapi.util.MapperDTO;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    private final MapperDTO mapperDTO;
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final LocalStorageService localStorageService;

    @Override
    public Respuesta crearProducto(Long categoriaId, MultipartFile imagen, String nombre, String descripcion, BigDecimal precio) {
        Categoria categoria = this.categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new NotFoundException("Categoria no encontrado"));

        String productoImageUrl = localStorageService.guardarImagenLocalmente(imagen);

        Producto producto = new Producto();
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setUrlImagen(productoImageUrl);

        this.productoRepository.save(producto);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Producto creado con exito")
                .build();
    }


    @Override
    public Respuesta actualizarProducto(Long productoId, Long categoriaId, MultipartFile imagen, String nombre, String descripcion, BigDecimal precio) {
        Producto producto = this.productoRepository.findById(productoId).orElseThrow(
                () -> new NotFoundException("Producto no encontrado"));

        Categoria categoria = null;
        String productoImageUrl = null;

        if (categoriaId != null) {
            categoria = this.categoriaRepository.findById(categoriaId).orElseThrow(
                    () -> new NotFoundException("Categoria no encontrado"));
        }

        if (imagen != null && !imagen.isEmpty()) {
            productoImageUrl = this.localStorageService.guardarImagenLocalmente(imagen);
        }

        if (categoria != null) producto.setCategoria(categoria);
        if (nombre != null) producto.setNombre(nombre);
        if (precio != null) producto.setPrecio(precio);
        if (descripcion != null) producto.setDescripcion(descripcion);
        if (productoImageUrl != null) producto.setUrlImagen(productoImageUrl);

        this.productoRepository.save(producto);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Producto actualizado correctamente")
                .build();
    }


    @Override
    public Respuesta eliminarProducto(Long productoId) {
        Producto producto = this.productoRepository.findById(productoId).orElseThrow(
                () -> new NotFoundException("Producto no encontrado")
        );

        this.productoRepository.delete(producto);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Producto eliminado correctamente")
                .build();
    }


    @Override
    public Respuesta getProductoPorId(Long productoId) {
        Producto producto = this.productoRepository.findById(productoId).orElseThrow(
                () -> new NotFoundException("Producto no encontrado")
        );

        ProductoDTO productoDTO = mapperDTO.productoToDTOBasico(producto);

        return Respuesta.builder()
                .estado(200)
                .producto(productoDTO)
                .build();
    }


    @Override
    public Respuesta getAllProductos() {
        List<Producto> productos = this.productoRepository.findAll();
        List<ProductoDTO> productoDTOS = productos.stream()
                .map(mapperDTO::productoToDTOBasico)
                .toList();

        return Respuesta.builder()
                .estado(200)
                .listaProductos(productoDTOS)
                .build();
    }


    @Override
    public Respuesta getProductosPorCategoria(Long categoriaId) {
        List<Producto> productos = this.productoRepository.findByCategoriaId(categoriaId);
        List<ProductoDTO> productoDTOS = productos.stream()
                .map(mapperDTO::productoToDTOBasico)
                .toList();

        return Respuesta.builder()
                .estado(200)
                .listaProductos(productoDTOS)
                .build();
    }


    @Override
    public Respuesta buscarProducto(String valor) {
        List<Producto> productos = this.productoRepository.findByNombreOrDescripcionContaining(valor, valor);

        if (productos.isEmpty()){
            throw new NotFoundException("No se encontraron los productos");
        }

        List<ProductoDTO> productoDTOS = productos.stream()
                .map(mapperDTO::productoToDTOBasico)
                .toList();

        return Respuesta.builder()
                .estado(200)
                .listaProductos(productoDTOS)
                .build();
    }

}