package pe.edu.cibertec.massapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.massapi.persistence.model.ItemPedido;

@Repository
public interface PedidoItemRepository extends JpaRepository<ItemPedido, Long>, JpaSpecificationExecutor<ItemPedido> {
}
