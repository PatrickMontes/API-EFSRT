package pe.edu.cibertec.massapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.massapi.persistence.model.ItemPedido;
import pe.edu.cibertec.massapi.persistence.model.Usuario;

import java.util.List;

@Repository
public interface PedidoItemRepository extends JpaRepository<ItemPedido, Long>, JpaSpecificationExecutor<ItemPedido> {

    List<ItemPedido> findByUsuario(Usuario usuario);
}
