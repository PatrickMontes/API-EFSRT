package pe.edu.cibertec.massapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.massapi.presentation.dto.CategoriaDTO;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.implementation.CategoriaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaContoller {

    private final CategoriaService categoriaService;

    @PostMapping("/crear")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> createCategory(@RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(this.categoriaService.crearCategoria(categoriaDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Respuesta> getAllCategory() {
        return ResponseEntity.ok(this.categoriaService.getAllCategorias());
    }


    @PutMapping("/actualizar/{categoriaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> updateCategory(@PathVariable Long categoriaId, @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(this.categoriaService.actualizarCategoria(categoriaId, categoriaDTO));
    }


    @DeleteMapping("/eliminar/{categoriaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> deleteCategory(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(this.categoriaService.eliminarCategoria(categoriaId));
    }


    @GetMapping("/getCategoriaPorId/{categoriaId}")
    public ResponseEntity<Respuesta> getCategoryById(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(this.categoriaService.getCategoriaPorId(categoriaId));
    }

}
