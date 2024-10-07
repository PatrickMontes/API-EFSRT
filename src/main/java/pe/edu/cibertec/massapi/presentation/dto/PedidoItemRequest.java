package pe.edu.cibertec.massapi.presentation.dto;

import lombok.Data;

@Data
public class PedidoItemRequest {
    private Long productoId;
    private int cantidad;

}
