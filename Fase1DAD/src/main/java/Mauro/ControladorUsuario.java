package Mauro;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorUsuario {
	
	@Autowired
	private Usuario usuarioLogeado;
	private UsuarioRepository repositorioUsuarios;
	
	@PostMapping("/login_usuario")
	public String inicioSesion(Model model, HttpSession sesion){
		return "login_correcto";
	}
	
	@GetMapping("/registro")
	public String registroUsuario(@RequestParam String nombre, @RequestParam String apellidos, @RequestParam String password, @RequestParam String email, @RequestParam String direccion, @RequestParam String ciudad, HttpSession sesion){
		usuarioLogeado.setInfoUser(nombre, apellidos, password, email, direccion, ciudad);
		repositorioUsuarios.save(usuarioLogeado);
		return ("registro_correcto");
	}
	
	//Método de prueba para ver si se registran los usuarios
	@RequestMapping("/usuario/lista_usuarios")
	public String verUsuariosLogeados(Model model){
		model.addAttribute("usuarios",repositorioUsuarios.findAll());
		return "usuarios";
	}
}
