package pe.edu.cibertec.massapi.service.interfaces;

import org.springframework.data.domain.Pageable;
import pe.edu.cibertec.massapi.persistence.enums.PedidoEstado;
import pe.edu.cibertec.massapi.presentation.dto.PedidoRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;

import java.time.LocalDateTime;

public interface IPedidoItemService {

    Respuesta realizarPedido(PedidoRequest pedidoRequest);
    Respuesta actualizarEstadoItemPedido(Long itemPedidoId, String estado);
    Respuesta filtrarItemsPedido(PedidoEstado estado, LocalDateTime inicio, LocalDateTime fin, Long itemId, Pageable pageable);
}
