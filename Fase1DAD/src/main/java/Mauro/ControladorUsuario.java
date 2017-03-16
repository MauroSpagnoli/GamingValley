package Mauro;

import java.util.Calendar; 
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorUsuario {

	@Autowired
	private VideojuegoRepository repositorioVideojuegos;
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	@Autowired
	private ValoracionRepository repositorioValoraciones;
	@Autowired
	private PedidoRepository repositorioPedidos;

	
	@RequestMapping("/")
	public String paginaInicial(Model model, HttpServletRequest request, HttpSession sesion){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		sesion = request.getSession();
		sesion.setAttribute("email", currentPrincipalName);
		boolean noLogeado = true;
		if(currentPrincipalName!="anonymousUser"){ //Si el usuario NO es anonimo
			noLogeado = false;
		}
		model.addAttribute("noLogeado",noLogeado);
		model.addAttribute("user", request.isUserInRole("USER"));
		return "index";
	}
	
	@GetMapping("/login_usuario")
	public String inicioSesion(Model model, HttpSession sesion){
			sesion.getAttribute("email");
			Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
			model.addAttribute("usuario",usuarioBuscado);
			return "login_usuario";
	}

	@PostMapping("/logearse")
	public String logearse(Model model, HttpSession sesion){
			return "login_correcto";
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
    public String registroUsuario(Model model,Usuario usuario,HttpSession sesion){
    	usuario.setPasswordHash(new BCryptPasswordEncoder().encode(usuario.getPasswordHash()));
    	usuario.setRoles("ROLE_USER");
    	repositorioUsuarios.save(usuario);
    	return "registro_correcto.html";
    }

	@GetMapping("/form_registro")
	public String mostrarForm(Model model){
		return "registro_usuario.html";
	}
	
	@PostMapping("/deslogearse")
	public String deslogearse(Model model,HttpSession sesion){
			return "deslogeo_correcto";
	}
	
    @GetMapping("/deslogeo_correcto")
    public String deslogueoCorrecto() {
    	return "deslogeo_correcto";
    }
	
	@RequestMapping("/pedidos")
	public String tablon(Model model,HttpSession sesion) {
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		model.addAttribute("pedidos", this.repositorioPedidos.findByComprador(usuarioBuscado));
		return "pedidos";
	}
	
	@RequestMapping("/pedido/{id}")
	public String verVideojuego(Model model, @PathVariable long id,HttpSession sesion){
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		Pedido pedido = repositorioPedidos.findByIdAndComprador(id, usuarioBuscado);
		model.addAttribute("pedido", pedido);
		System.out.println(pedido.getCesta());
		return "ver_pedido";
	}
	
	@GetMapping("/pedido/{idPedido}/eliminar_videojuego_pedido/{idVideojuego}")
	public String eliminarVideojuegoPedido(HttpSession sesion, @PathVariable long idPedido,@PathVariable long idVideojuego){
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		Videojuego videojuego = this.repositorioVideojuegos.findById(idVideojuego);
		Pedido pedido = this.repositorioPedidos.findByIdAndComprador(idPedido,usuarioBuscado);
		pedido.eliminarVideojuego(videojuego);
		videojuego.eliminarPedido(pedido);
		this.repositorioPedidos.save(pedido);
		this.repositorioVideojuegos.save(videojuego);
		return"videojuego_eliminado_pedido";
	}
	
	
	@GetMapping("/nuevo_pedido_actual")
	public String pedidoActualNuevo(HttpSession sesion) {
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		Pedido pedido = new Pedido();
		pedido.setComprador(usuarioBuscado);
		Calendar fecha = new GregorianCalendar();
		pedido.setFecha(fecha.get(Calendar.DATE) + "-" + fecha.get(Calendar.MONTH) + "-" + fecha.get(Calendar.YEAR));
		this.repositorioPedidos.save(pedido);
		usuarioBuscado.setNumeroPedidoActual(pedido.getId());
		this.repositorioUsuarios.save(usuarioBuscado);
		return"pedido_guardado";
	}
	
	@GetMapping("/cambiar_pedido_actual/{id}")
	public String cambiarPedidoActual(Model model,@PathVariable long id,HttpSession sesion){
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		usuarioBuscado.setNumeroPedidoActual(id);
		this.repositorioUsuarios.save(usuarioBuscado);
		model.addAttribute("id",id);
		return"pedido_actual_cambiado";
	}
	
	@GetMapping("/eliminar_pedido/{id}")
	public String eliminarPedido(@PathVariable long id,HttpSession sesion){
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		Pedido pedido = this.repositorioPedidos.findByIdAndComprador(id,usuarioBuscado);
		pedido.vaciarPedido();
		this.repositorioPedidos.delete(pedido);
		return"pedido_eliminado";
	}
	
	@GetMapping("/crearpdf/{id}")
	public String crearpdf(@PathVariable long id,HttpSession sesion){
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		Pedido pedidoPDF = this.repositorioPedidos.findByIdAndComprador(id, usuarioBuscado);
		ClientePDF c=new ClientePDF("127.0.0.1", 9991,pedidoPDF);
		c.iniciar();
		ClienteEmail e=new ClienteEmail("127.0.0.1",9990);
		e.iniciar();
		return "";
	}
	
	@PostMapping("/agregar_videojuego_pedido_actual/{id}")
	public String agregar_v_pactual(@PathVariable long id,HttpSession sesion){
			Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
			Videojuego videojuego = this.repositorioVideojuegos.getOne(id);
			Pedido pedido = this.repositorioPedidos.findByIdAndComprador(usuarioBuscado.getNumeroPedidoActual(),usuarioBuscado);
			videojuego.getPedidos().add(pedido);
			videojuego.comprarVideojuego();
			pedido.getCesta().add(videojuego);
			pedido.costeTotalPedido();
			this.repositorioPedidos.save(pedido);
			this.repositorioVideojuegos.save(videojuego);
			return"videojuego_agregado_pedido";
	}
	
	@GetMapping("/pedido_requerido")
	public String pedidoRequerido(){
		return"pedido_requerido";
	}
	
	@PostMapping("/videojuego/{id}/nueva_form_valoracion")
	public String agregarValoracion(Model model,@PathVariable long id, Valoracion valoracion,HttpSession sesion){
		Usuario usuarioBuscado = repositorioUsuarios.findByEmail((String) sesion.getAttribute("email"));
		model.addAttribute("id",id);
		Videojuego videojuego = this.repositorioVideojuegos.findOne(id);
		valoracion.setAutor(usuarioBuscado);
		videojuego.agregarValoracion(valoracion);
		repositorioValoraciones.save(valoracion);
		repositorioVideojuegos.save(videojuego);
		return "valoracion_guardada";	
	}
	
	@GetMapping("/videojuego/{id}/form_valoracion")
	public String mostrarForm(Model model, @PathVariable long id,HttpSession sesion){
			model.addAttribute("id",id);
			return "form_valoracion";
	}
}
