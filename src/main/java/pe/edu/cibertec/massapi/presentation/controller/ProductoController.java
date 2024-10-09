package pe.edu.cibertec.massapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.exception.InvalidCredentialsException;
import pe.edu.cibertec.massapi.service.implementation.ProductoService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/crear")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> crearProducto(@RequestParam Long categoriaId, @RequestParam MultipartFile imagen, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam BigDecimal precio){
        if (categoriaId == null || imagen.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || precio == null){
            throw new InvalidCredentialsException("All Fields are Required");

        }

        return ResponseEntity.ok(this.productoService.crearProducto(categoriaId, imagen, nombre, descripcion, precio));
    }


    @PutMapping("/actualizar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> updateProduct(@RequestParam Long productoId, @RequestParam(required = false) Long categoriaId, @RequestParam(required = false) MultipartFile imagen, @RequestParam(required = false) String nombre, @RequestParam(required = false) String descripcion, @RequestParam(required = false) BigDecimal precio){
        return ResponseEntity.ok(this.productoService.actualizarProducto(productoId, categoriaId, imagen, nombre, descripcion, precio));
    }


    @DeleteMapping("/eliminar/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> deleteProduct(@PathVariable Long productoId){
        return ResponseEntity.ok(this.productoService.eliminarProducto(productoId));
    }


    @GetMapping("/getProductoPorId/{productId}")
    public ResponseEntity<Respuesta> getProductoPorId(@PathVariable Long productoId){
        return ResponseEntity.ok(this.productoService.getProductoPorId(productoId));
    }


    @GetMapping("/getAll")
    public ResponseEntity<Respuesta> getAllProductos(){
        return ResponseEntity.ok(this.productoService.getAllProductos());
    }


    @GetMapping("/getProductosPorCategoria/{categoriaId}")
    public ResponseEntity<Respuesta> getProductosPorCategoria(@PathVariable Long categoriaId){
        return ResponseEntity.ok(this.productoService.getProductosPorCategoria(categoriaId));
    }


    @GetMapping("/buscar")
    public ResponseEntity<Respuesta> searchProduct(@RequestParam String valor){
        return ResponseEntity.ok(this.productoService.buscarProducto(valor));
    }

}
