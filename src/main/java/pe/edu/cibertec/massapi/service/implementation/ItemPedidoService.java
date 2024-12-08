package pe.edu.cibertec.massapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.massapi.persistence.enums.PedidoEstado;
import pe.edu.cibertec.massapi.persistence.model.ItemPedido;
import pe.edu.cibertec.massapi.persistence.model.Pedido;
import pe.edu.cibertec.massapi.persistence.model.Producto;
import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.persistence.repository.PedidoItemRepository;
import pe.edu.cibertec.massapi.persistence.repository.PedidoRepository;
import pe.edu.cibertec.massapi.persistence.repository.ProductoRepository;
import pe.edu.cibertec.massapi.presentation.dto.ItemPedidoDTO;
import pe.edu.cibertec.massapi.presentation.dto.PedidoRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.exception.NotFoundException;
import pe.edu.cibertec.massapi.service.interfaces.IPedidoItemService;
import pe.edu.cibertec.massapi.service.specification.ItemPedidoSpecification;
import pe.edu.cibertec.massapi.util.MapperDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemPedidoService implements IPedidoItemService {

    private final MapperDTO mapperDTO;
    private final UsuarioService usuarioService;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoItemRepository pedidoItemRepository;

    @Override
    public Respuesta realizarPedido(PedidoRequest pedidoRequest) {
        Usuario usuario = this.usuarioService.getLoginUsuario();
        List<ItemPedido> itemPedidos = pedidoRequest.getItems().stream()
                .map(orderItemRequest -> {
                    Producto producto = this.productoRepository.findById(orderItemRequest.getProductoId()).orElseThrow(() ->
                            new NotFoundException("Producto no encontrado"));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setProducto(producto);
                    itemPedido.setCantidad(orderItemRequest.getCantidad());
                    itemPedido.setPrecio(producto.getPrecio().multiply(BigDecimal.valueOf(orderItemRequest.getCantidad())));
                    itemPedido.setEstado(PedidoEstado.PENDIENTE);
                    itemPedido.setUsuario(usuario);

                    return itemPedido;
                }).toList();

        BigDecimal totalPrecio = pedidoRequest.getPrecioTotal() != null && pedidoRequest.getPrecioTotal().compareTo(BigDecimal.ZERO) > 0
                ? pedidoRequest.getPrecioTotal()
                : itemPedidos.stream().map(ItemPedido::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = new Pedido();
        pedido.setListaItemsPedido(itemPedidos);
        pedido.setPrecioTotal(totalPrecio);

        itemPedidos.forEach(itemPedido -> itemPedido.setPedido(pedido));
        this.pedidoRepository.save(pedido);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Pedido registrado correctamente")
                .build();
    }


    @Override
    public Respuesta actualizarEstadoItemPedido(Long itemPedidoId, String estado) {
        ItemPedido itemPedido = this.pedidoItemRepository.findById(itemPedidoId).orElseThrow(
                () -> new NotFoundException("ItemPedido no encontrado"));

        itemPedido.setEstado(PedidoEstado.valueOf(estado.toUpperCase()));
        this.pedidoItemRepository.save(itemPedido);

        return Respuesta.builder()
                .estado(200)
                .mensaje("Estado de pedido actualizado correctamente")
                .build();
    }


    @Override
    public Respuesta filtrarItemsPedido(PedidoEstado estado, LocalDateTime inicio, LocalDateTime fin, Long itemId, Pageable pageable) {
        Specification<ItemPedido> spec = Specification.where(ItemPedidoSpecification.hasStatus(estado))
                .and(ItemPedidoSpecification.createBetween(inicio, fin))
                .and(ItemPedidoSpecification.hasItemId(itemId));

        Page<ItemPedido> pedidoItemPage = this.pedidoItemRepository.findAll(spec, pageable);

        if (pedidoItemPage.isEmpty()){
            throw new NotFoundException("No se encontro pedido");
        }

        List<ItemPedidoDTO> itemPedidoDTOS = pedidoItemPage.getContent().stream()
                .map(mapperDTO::itemPedidoToDTOPlusProductoAndUsuario)
                .toList();

        return Respuesta.builder()
                .estado(200)
                .listaItemsPedido(itemPedidoDTOS)
                .totalPaginas(pedidoItemPage.getTotalPages())
                .totalElementos(pedidoItemPage.getTotalElements())
                .build();
    }


    @Override
    public List<ItemPedido> obtenerPedidosPorUsuario(Usuario usuario) {
        return pedidoItemRepository.findByUsuario(usuario);
    }


}