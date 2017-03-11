package Mauro;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.Socket;
import javax.annotation.PostConstruct; 
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorUsuario {
	
	private Usuario usuarioLogeado;
	private Pedido pedidoActual;
	@Autowired
	private VideojuegoRepository repositorioVideojuegos;
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	@Autowired
	private ValoracionRepository repositorioValoraciones;
	@Autowired
	private PedidoRepository repositorioPedidos;
	@PostConstruct
	public void init(){
		this.pedidoActual = null;
	}
	
	@RequestMapping("/")
	public String paginaInicial(Model model, HttpServletRequest request){
		model.addAttribute("user", request.isUserInRole("USER"));
		return "index";
	}
	
	@GetMapping("/login_usuario")
	public String inicioSesion(Model model, HttpSession sesion){
		/*if(this.usuarioLogeado== null){
			return "login_usuario";
		}else{*/
			model.addAttribute("usuario",this.usuarioLogeado);
			return "login_usuario";
		//}
	}

	@PostMapping("/logearse")
	public String logearse(Model model, HttpSession sesion, Usuario usuario){
		//Usuario usuarioIntento = this.repositorioUsuarios.findByEmailAndPassword(usuario.getEmail(),usuario.getPasswordHash());
		/*if(usuarioIntento==null){
			return "login_incorrecto";
		}else{*/
			//this.usuarioLogeado = usuarioIntento;
			model.addAttribute("usuario", this.usuarioLogeado);
			return "login_correcto";	
		//}
	}
	
    @GetMapping("/login_correcto")
    public String loginCorrecto() {
    	return "login_correcto";
    }
    
    @GetMapping("/login_incorrecto")
    public String loginIncorrecto() {
    	return "login_incorrecto";
    }
    
	@GetMapping("/registro")
	public String registroUsuario(Model model,Usuario usuario, HttpSession sesion){
		usuarioLogeado=usuario;
		usuarioLogeado.setPasswordHash(new BCryptPasswordEncoder().encode(usuario.getPasswordHash()));
		usuarioLogeado.setRoles("ROLE_USER");
		repositorioUsuarios.save(usuarioLogeado);
		return "registro_correcto.html";
	}

	@GetMapping("/form_registro")
	public String mostrarForm(Model model){
		return "registro_usuario.html";
	}
	
	@GetMapping("/deslogearse")
	public String deslogearse(Model model,HttpSession sesion){
		//if (this.usuarioLogeado != null){
			this.usuarioLogeado = null;
			this.pedidoActual = null;
			return "deslogeo_correcto";
		/*}else{
			return "no_logeado";
		}*/
	}
	
    @GetMapping("/deslogeo_correcto")
    public String deslogueoCorrecto() {
    	return "deslogeo_correcto";
    }
	
	@RequestMapping("/pedidos")
	public String tablon(Model model,HttpSession sesion) {
		model.addAttribute("pedidos", repositorioPedidos.findByComprador(this.usuarioLogeado));
		return "pedidos";
	}
	
	@RequestMapping("/pedido/{id}")
	public String verVideojuego(Model model, @PathVariable long id){
		Pedido pedido = repositorioPedidos.findOne(id);
		model.addAttribute("pedido", pedido);
		System.out.println(pedido.getCesta());
		return "ver_pedido";
	}
	
	@GetMapping("/pedido/{idPedido}/eliminar_videojuego_pedido/{idVideojuego}")
	public String eliminarVideojuegoPedido(HttpSession sesion, @PathVariable long idPedido,@PathVariable long idVideojuego){
		Videojuego videojuego = this.repositorioVideojuegos.findById(idVideojuego);
		Pedido pedido = this.repositorioPedidos.findById(idPedido);
		pedido.eliminarVideojuego(videojuego);
		videojuego.eliminarPedido(pedido);
		this.repositorioPedidos.save(pedido);
		this.repositorioVideojuegos.save(videojuego);
		return"videojuego_eliminado_pedido";
	}
	
	
	@GetMapping("/nuevo_pedido_actual")
	public String pedidoActualNuevo(HttpSession sesion) {
		//if(this.usuarioLogeado!=null){
			Pedido pedido = new Pedido();
			pedido.setComprador(this.usuarioLogeado);
			Calendar fecha = new GregorianCalendar();
			pedido.setFecha(fecha.get(Calendar.DATE) + "-" + fecha.get(Calendar.MONTH) + "-" + fecha.get(Calendar.YEAR));
			this.pedidoActual = pedido;
			this.repositorioPedidos.save(pedido);
			System.out.println(this.pedidoActual.getId());
			System.out.println(this.pedidoActual.getCesta());
			return"pedido_guardado";
		/*}else{
			return"logearse_necesita";
		}*/
	}
	
	@GetMapping("/cambiar_pedido_actual/{id}")
	public String cambiarPedidoActual(Model model,@PathVariable long id,HttpSession sesion){
		this.pedidoActual = this.repositorioPedidos.findByIdAndComprador(id, this.usuarioLogeado);
		model.addAttribute("id",id);
		System.out.println(this.pedidoActual.getId());
		System.out.println(this.pedidoActual.getCesta());
		return"pedido_actual_cambiado";
	}
	
	@GetMapping("/eliminar_pedido/{id}")
	public String eliminarPedido(@PathVariable long id,HttpSession sesion){
		Pedido pedido = this.repositorioPedidos.findByIdAndComprador(id,this.usuarioLogeado);
		pedido.vaciarPedido();
		this.repositorioPedidos.delete(pedido);
		return"pedido_eliminado";
	}
	
	@GetMapping("/crearpdf")
	public String crearpdf(HttpSession sesion){
		ClientePDF c=new ClientePDF("127.0.0.1", 9990,pedidoActual);
		c.iniciar();
		ClienteEmail e=new ClienteEmail("127.0.0.1",9990,pedidoActual);
		e.iniciar();
		return "";
	}
	
	@PostMapping("/agregar_videojuego_pedido_actual/{id}")
	public String agregar_v_pactual(@PathVariable long id,HttpSession sesion){
			Videojuego videojuego = this.repositorioVideojuegos.getOne(id);
			videojuego.getPedidos().add(this.pedidoActual);
			videojuego.comprarVideojuego();
			this.pedidoActual.getCesta().add(videojuego);
			this.pedidoActual.costeTotalPedido();
			this.repositorioPedidos.save(this.pedidoActual);
			this.repositorioVideojuegos.save(videojuego);
			return"videojuego_agregado_pedido";
	}
	
	@GetMapping("/pedido_requerido")
	public String pedidoRequerido(){
		return"pedido_requerido";
	}
	
	@PostMapping("/videojuego/{id}/nueva_form_valoracion")
	public String agregarValoracion(Model model,@PathVariable long id, Valoracion valoracion,HttpSession sesion){
		model.addAttribute("id",id);
		Videojuego videojuego = this.repositorioVideojuegos.findOne(id);
		valoracion.setAutor(this.usuarioLogeado);
		videojuego.agregarValoracion(valoracion);
		repositorioValoraciones.save(valoracion);
		repositorioVideojuegos.save(videojuego);
		return "valoracion_guardada";	
	}
	
	@GetMapping("/videojuego/{id}/form_valoracion")
	public String mostrarForm(Model model, @PathVariable long id,HttpSession sesion){
		//if(this.usuarioLogeado != null){
			model.addAttribute("id",id);
			return "form_valoracion";
		/*}else{
			return "logearse_necesita";
		}*/
	}
}
