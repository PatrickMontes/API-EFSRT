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
    private final LocalDateTime marcaTiempo = LocalDateTime.now();

    private String token;
    private String rol;
    private String tiempoExpiracion;

    private int totalPaginas;
    private Long totalElementos;

    private DireccionDTO direccion;

    private UsuarioDTO usuario;
    private List<UsuarioDTO> listaUsuarios;

    private CategoriaDTO categoria;
    private List<CategoriaDTO> listaCategorias;

    private ProductoDTO producto;
    private List<ProductoDTO> listaProductos;

    private ItemPedidoDTO itemPedido;
    private List<ItemPedidoDTO> listaItemsPedido;

    private PedidoDTO pedido;
    private List<PedidoDTO> listaPedidos;

}
