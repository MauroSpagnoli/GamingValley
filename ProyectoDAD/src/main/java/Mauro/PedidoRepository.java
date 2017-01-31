package Mauro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	Page<Pedido> findByComprador(String comprador,Pageable page);
	Page<Pedido> findByFecha(String fecha,Pageable page);
	Page<Pedido> findByCoste(double coste,Pageable page);
}
