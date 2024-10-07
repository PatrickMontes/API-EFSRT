package pe.edu.cibertec.massapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String imagenUrl;
    private CategoriaDTO categoria;

}
