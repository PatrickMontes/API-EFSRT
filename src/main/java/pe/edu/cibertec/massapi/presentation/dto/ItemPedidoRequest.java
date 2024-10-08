package pe.edu.cibertec.massapi.presentation.dto;

import lombok.Data;

@Data
public class ItemPedidoRequest {
    private Long productoId;
    private int cantidad;

}
