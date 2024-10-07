package pe.edu.cibertec.massapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pe.edu.cibertec.massapi.persistence.model.Pago;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class PedidoRequest {
    private BigDecimal totalPrecio;
    private List<PedidoItemRequest> items;
    private Pago pagoInfo;

}
