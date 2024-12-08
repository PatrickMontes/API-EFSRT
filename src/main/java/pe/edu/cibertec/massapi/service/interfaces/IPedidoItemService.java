package pe.edu.cibertec.massapi.service.interfaces;

import org.springframework.data.domain.Pageable;
import pe.edu.cibertec.massapi.persistence.enums.PedidoEstado;
import pe.edu.cibertec.massapi.persistence.model.ItemPedido;
import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.presentation.dto.PedidoRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

public interface IPedidoItemService {

    Respuesta realizarPedido(PedidoRequest pedidoRequest);
    Respuesta actualizarEstadoItemPedido(Long itemPedidoId, String estado);
    Respuesta filtrarItemsPedido(PedidoEstado estado, LocalDateTime inicio, LocalDateTime fin, Long itemId, Pageable pageable);
    List<ItemPedido> obtenerPedidosPorUsuario(Usuario usuario);
}
