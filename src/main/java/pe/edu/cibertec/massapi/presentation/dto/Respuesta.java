package pe.edu.cibertec.massapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import pe.edu.cibertec.massapi.persistence.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Respuesta {

    private int estado;
    private String mensaje;
    private final LocalDateTime timeStamp = LocalDateTime.now();

    private String token;
    private String role;
    private String tiempoExpirado;

    private int totalPage;
    private Long totalElement;

    private DireccionDTO direccion;

    private UsuarioDTO usuario;
    private List<UsuarioDTO> usuarioList;

    private CategoriaDTO categoria;
    private List<CategoriaDTO> categoriaList;

    private ProductoDTO producto;
    private List<ProductoDTO> productoList;

    private PedidoItemDTO pedidoItem;
    private List<PedidoItemDTO> pedidoItemList;

    private PedidoDTO pedido;
    private List<PedidoDTO> pedidoList;


}
