package Mauro;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorPedidos {
	private long contadorPedidos= 0l;
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
	
	@GetMapping("/nuevo_pedido_actual")
	public String pedidoActualNuevo() {
		this.contadorPedidos++;
		System.out.println(this.contadorPedidos);
		this.repositorioPedidos.deleteAll();
		Pedido pedido = new Pedido("2017-02-13",this.repositorioUsuarios.getOne(1L));
		this.repositorioPedidos.save(pedido);
		return "pedido_guardado";
	}
	
	@GetMapping("/agregar_videojuego_pedido_actual/{id}")
	public String agregar_v_pactual(Model model, @PathVariable long id){
		Pedido pedido = this.repositorioPedidos.getOne(this.contadorPedidos);
		Videojuego videojuego = this.repositorioVideojuegos.getOne(id);
		videojuego.comprarVideojuego();
		pedido.agregarVideojuego(videojuego);
		pedido.costeTotalPedido();
		this.repositorioPedidos.save(pedido);
		return"videojuego_agregado_pedido";
	}
	
	@RequestMapping("/pedidos")
	public String tablon(Model model) {
		model.addAttribute("pedidos", repositorioPedidos.findAll());
		return "pedidos";
	}
	
	@RequestMapping("/realizar_pedido")
	public String verVideojuegos(Model model){
		model.addAttribute("videojuegos",repositorioVideojuegos.findAll());
		return "realizar_pedido";
	}
	
	@RequestMapping("/pedido/{id}")
	public String verVideojuego(Model model, @PathVariable long id){
		Pedido pedido = repositorioPedidos.findOne(id);
		model.addAttribute("pedido", pedido);
		return "ver_pedido";
	}

}
