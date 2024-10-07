package pe.edu.cibertec.massapi.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.cibertec.massapi.persistence.enums.PedidoEstado;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pedido_item")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private BigDecimal precio;
    private PedidoEstado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(name = "creado_en")
    private final LocalDateTime creadoEn = LocalDateTime.now();

