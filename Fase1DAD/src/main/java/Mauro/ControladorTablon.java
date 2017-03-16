package Mauro;

import javax.servlet.http.HttpServletRequest; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*Cuando se necesite un formulario con post y que sea una plantilla se ha de 
hacer lo mismo que en los métodos de agregar valoracion */

@Controller
public class ControladorTablon{

		@Autowired
		private NoticiaRepository repositorioNoticias;
		@Autowired
		private VideojuegoRepository repositorioVideojuegos;
				
		@RequestMapping("/noticias")
		public String tablon(Model model, HttpServletRequest request) {
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			model.addAttribute("noticias", repositorioNoticias.findAll());
			return "noticias";
		}
				
		@RequestMapping("/noticia/{id}")
		public String verNoticia(Model model, @PathVariable long id) {
			Noticia noticia = repositorioNoticias.findOne(id);
			model.addAttribute("Noticia", noticia);
			return "ver_noticia";
		}
		
		@RequestMapping("/videojuegos")
		public String verVideojuegos(Model model, HttpServletRequest request){
			model.addAttribute("admin",request.isUserInRole("ADMIN"));
			model.addAttribute("videojuegos",repositorioVideojuegos.findAll());
			return "videojuegos";
		}
		
		@RequestMapping("/videojuego/{id}")
		public String verVideojuego(Model model, @PathVariable long id, HttpServletRequest request){
			model.addAttribute("user", request.isUserInRole("USER"));
			Videojuego videojuego = repositorioVideojuegos.findOne(id);
			model.addAttribute("Videojuego", videojuego);
			return "ver_videojuego";
		}
		
		@RequestMapping("/videojuego/{id1}/ver_valoracion/{id2}")
		public String verValoracion(Model model, @PathVariable long id1, @PathVariable int id2){
			Videojuego videojuego = repositorioVideojuegos.findOne(id1);
			Valoracion valoracion = videojuego.getValoraciones().get(id2-1);
			model.addAttribute("Valoracion",valoracion);
			return "ver_valoracion";
		}
		
		
		@GetMapping("/noticias/noticia_nueva")
		public String noticiaNueva(Model model){
			return"nueva_noticia";
		}
		
		@PostMapping("/noticias/nueva_noticia")
		public String agregarNoticia(Model model,Noticia noticia) {
			repositorioNoticias.save(noticia);
			return "noticia_guardada";
		}
		
		@GetMapping("/videojuegos/nuevo_videojuego")
		public String juegoNuevo(){
			return "agregar_videojuego";
		}
		
		@PostMapping("/videojuegos/agregar_videojuego")
		public String nuevoJuego(Model model, Videojuego videojuego) {
			repositorioVideojuegos.save(videojuego);
			return "videojuego_guardado";
		}
}
