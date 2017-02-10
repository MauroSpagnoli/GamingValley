package Mauro;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorUsuario {
	
	@Autowired
	private Usuario usuarioLogeado;
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	
	@GetMapping("/login_usuario")
	public String inicioSesion(Model model, HttpSession sesion){
		return "login_usuario";
	}
	
	@PostMapping("/logearse")
	public String logearse(Model model, HttpSession sesion, Usuario usuario){
		this.usuarioLogeado = usuario;
		this.repositorioUsuarios.save(usuario);
		return "login_correcto";
	}
	
	@GetMapping("/registro")
	public String registroUsuario(Model model,Usuario usuario, HttpSession sesion){
		usuarioLogeado=usuario;
		repositorioUsuarios.save(usuarioLogeado);
		return "registro_correcto.html";
	}
	@GetMapping("/form_registro")
	public String mostrarForm(Model model){
		return "registro_usuario.html";
	}
	
	//Método de prueba para ver si se registran los usuarios
	@RequestMapping("/usuario/lista_usuarios")
	public String verUsuariosLogeados(Model model){
		model.addAttribute("usuarios",repositorioUsuarios.findAll());
		return "usuarios";
	}
}
