package pe.edu.cibertec.massapi.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.massapi.persistence.enums.PedidoEstado;
import pe.edu.cibertec.massapi.persistence.model.ItemPedido;
import pe.edu.cibertec.massapi.persistence.model.Usuario;
import pe.edu.cibertec.massapi.presentation.dto.PedidoRequest;
import pe.edu.cibertec.massapi.presentation.dto.Respuesta;
import pe.edu.cibertec.massapi.service.implementation.ItemPedidoService;
import pe.edu.cibertec.massapi.service.implementation.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;
    private final UsuarioService usuarioService;

    @PostMapping("/realizar")
    public ResponseEntity<Respuesta> realizarPedido(@RequestBody PedidoRequest pedidoRequest) {
        return ResponseEntity.ok(this.itemPedidoService.realizarPedido(pedidoRequest));
    }


    @PutMapping("/actualizarEstadoItemPedido/{itemPedidoId}")
    public ResponseEntity<Respuesta> actualizarEstadoItemPedido(@PathVariable Long itemPedidoId, @RequestParam String estado) {
        return ResponseEntity.ok(this.itemPedidoService.actualizarEstadoItemPedido(itemPedidoId, estado));
    }


    @GetMapping("/filtrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Respuesta> filtrarItemsPedido(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime fin,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long itemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size

    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        PedidoEstado pedidoEstado = estado != null ? PedidoEstado.valueOf(estado.toUpperCase()) : null;

        return ResponseEntity.ok(itemPedidoService.filtrarItemsPedido(pedidoEstado, inicio, fin, itemId, pageable));

    }


    @GetMapping
    public ResponseEntity<List<ItemPedido>> obtenerPedidos() {
        Usuario usuario = usuarioService.getLoginUsuario();
        List<ItemPedido> pedidos = itemPedidoService.obtenerPedidosPorUsuario(usuario);

        return ResponseEntity.ok(pedidos);
    }

}
