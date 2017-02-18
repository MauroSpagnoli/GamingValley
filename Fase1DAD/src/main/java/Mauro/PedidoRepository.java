package Mauro;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	ArrayList<Pedido> findByComprador(Usuario comprador);
	Page<Pedido> findByFecha(String fecha,Pageable page);
	Page<Pedido> findByCoste(double coste,Pageable page);
	Pedido findById(long id);
	Pedido findByIdAndComprador(long id, Usuario comprador);
}
