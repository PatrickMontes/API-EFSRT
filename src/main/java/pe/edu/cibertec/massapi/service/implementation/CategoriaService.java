package pe.edu.cibertec.massapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.massapi.persistence.model.Categoria;
import pe.edu.cibertec.massapi.persistence.repository.CategoriaRepository;
import pe.edu.cibertec.massapi.presentation.dto.CategoriaDTO;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.exception.NotFoundException;
import pe.edu.cibertec.massapi.service.interfaces.ICategoriaService;
import pe.edu.cibertec.massapi.util.MapperDTO;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final MapperDTO mapperDTO;


    @Override
    public Respuesta crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());

        this.categoriaRepository.save(categoria);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Categoria creada con exito")
                .build();
    }


    @Override
    public Respuesta actualizarCategoria(Long categoriaId, CategoriaDTO categoriaDTO) {
        Categoria categoria = this.categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new NotFoundException("Categoria no encontrada")
        );

        categoria.setNombre(categoriaDTO.getNombre());
        this.categoriaRepository.save(categoria);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Categoria actualizada con exito")
                .build();
    }


    @Override
    public Respuesta getAllCategorias() {
        List<Categoria> categorias = this.categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOS = categorias.stream()
                .map(mapperDTO::categoriaToDTOBasico)
                .toList();

        return Respuesta.builder()
                .estado(200)
                .listaCategorias(categoriaDTOS)
                .build();
    }


    @Override
    public Respuesta getCategoriaPorId(Long categoriaId) {
        Categoria categoria = this.categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new NotFoundException("Categoria no encontrada")
        );

        CategoriaDTO categoriaDTO = this.mapperDTO.categoriaToDTOBasico(categoria);

        return Respuesta.builder()
                .estado(200)
                .categoria(categoriaDTO)
                .build();
    }


    @Override
    public Respuesta eliminarCategoria(Long categoriaId) {
        Categoria categoria = this.categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new NotFoundException("Categoria no encontrada")
        );

        this.categoriaRepository.delete(categoria);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Categoria eliminada con exito")
                .build();
    }
}
