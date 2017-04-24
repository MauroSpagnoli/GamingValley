package Mauro;

import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames="GamingValley")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	
	ArrayList<Pedido> findByComprador(Usuario comprador);
	Page<Pedido> findByFecha(String fecha,Pageable page);
	Page<Pedido> findByCoste(double coste,Pageable page);
	@Cacheable
	Pedido findById(long id);
	@Cacheable
	Pedido findByIdAndComprador(long id, Usuario comprador);
}
