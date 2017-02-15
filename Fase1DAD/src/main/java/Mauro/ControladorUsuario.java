package Mauro;

import javax.annotation.PostConstruct; 
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ControladorUsuario {
	
	private Usuario usuarioLogeado;
	
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	
	@PostConstruct
	public void init(){
		this.usuarioLogeado = null;
	}
	
	@GetMapping("/login_usuario")
	public String inicioSesion(Model model, HttpSession sesion){
		if(this.usuarioLogeado== null){
			return "login_usuario";
		}else{
			model.addAttribute("usuario",this.usuarioLogeado);
			return"login_correcto";
		}
	}
	
	@PostMapping("/logearse")
	public String logearse(Model model, HttpSession sesion, Usuario usuario){
		Usuario usuarioIntento = this.repositorioUsuarios.findByEmailAndPassword(usuario.getEmail(),usuario.getPassword());
		if(usuarioIntento==null){
			return "login_incorrecto";
		}else{
			this.usuarioLogeado = usuarioIntento;
			return "login_correcto";	
		}
	}
	
	@GetMapping("/registro")
	public String registroUsuario(Model model,Usuario usuario, HttpSession sesion){
		usuarioLogeado=usuario;
		repositorioUsuarios.save(usuarioLogeado);
		return "registro_correcto.html";
	}
	
	@GetMapping("/deslogearse")
	public String deslogearse(Model model,HttpSession sesion){
		if (this.usuarioLogeado != null){
			this.usuarioLogeado= null;
			return "deslogeo_correcto";
		}else{
			return "no_logeado";
		}
		
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
