package pe.edu.cibertec.massapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.massapi.persistence.model.PedidoItem;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {
    private Long id;
    private String email;
    private String nombre;
    private String numeroTelefono;
    private String password;
    private String role;
    private List<PedidoItem> pedidoItemList;
    private DireccionDTO direccion;

}
