package pe.edu.cibertec.massapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pe.edu.cibertec.massapi.persistence.model.Pago;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class PedidoRequest {

    private BigDecimal precioTotal;
    private List<ItemPedidoRequest> items;
    private Pago informacionPago;

}
