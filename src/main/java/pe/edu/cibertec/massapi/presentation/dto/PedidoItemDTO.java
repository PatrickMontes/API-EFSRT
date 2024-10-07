package pe.edu.cibertec.massapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoItemDTO {
    private Long id;
    private int cantidad;
    private BigDecimal precio;
    private String estado;
    private UsuarioDTO usuario;
    private ProductoDTO producto;
    private LocalDateTime creadoEn;

}
