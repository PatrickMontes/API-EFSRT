package pe.edu.cibertec.massapi.service.specification;

import org.springframework.data.jpa.domain.Specification;
import pe.edu.cibertec.massapi.persistence.enums.PedidoEstado;
import pe.edu.cibertec.massapi.persistence.model.ItemPedido;

import java.time.LocalDateTime;

public class ItemPedidoSpecification {

    public static Specification<ItemPedido> hasStatus(PedidoEstado estado) {
        return ((root, query, criteriaBuilder) -> estado != null ? criteriaBuilder.equal(root.get("estado"), estado) : null);
    }

    public static Specification<ItemPedido> createBetween(LocalDateTime inicio, LocalDateTime fin) {
        return ((root, query, criteriaBuilder) -> {

            if (inicio != null && fin != null) {
                return criteriaBuilder.between(root.get("creadoEn"), inicio, fin);

            } else if (inicio != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("creadoEn"), inicio);

            } else if (fin != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("creadoEn"), fin);

            } else {
                return null;

            }

        });
    }

    public static Specification<ItemPedido> hasItemId(Long itemId) {
        return (((root, query, criteriaBuilder) -> itemId != null ? criteriaBuilder.equal(root.get("id"), itemId) : null));
    }
}