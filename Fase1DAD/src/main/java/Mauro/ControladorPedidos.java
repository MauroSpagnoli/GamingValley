package Mauro;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorPedidos {
	@Autowired
	private PedidoRepository repositorioPedidos;
	@Autowired
	private VideojuegoRepository repositorioVideojuegos;
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	
	@PostConstruct
	public void init() {
		Videojuego TheLastOfUs = new Videojuego("The Last of Us 2", "Aventuras", 60, 50.0f);
		Videojuego F12017 = new Videojuego("F1 2017", "Deporte", 60, 60.0f);
		Usuario usuarioAlvaro = new Usuario("Alvaro","Hinojal","alvaro95","alvaro@gmail.com","C/Alcalde de Mostoles 33","Mostoles");
		repositorioUsuarios.save(usuarioAlvaro);
		repositorioVideojuegos.save(TheLastOfUs);
		repositorioVideojuegos.save(F12017);
	}

}
