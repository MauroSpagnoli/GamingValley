package Mauro;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ControladorTablon{

		@Autowired
		private NoticiaRepository repositorioNoticias;
		@Autowired
		private VideojuegoRepository repositorioVideojuegos;
		
		@PostConstruct
		public void init() {
			repositorioNoticias.save(new Noticia("Nueva PS5", "Se ha diseñado y creado la nueva PS5.", "29/01/2017"));
			repositorioNoticias.save(new Noticia("Resident Evil 7", "Resident Evil 7 rompe los records de ventas en un día.", "29/01/2017"));
			repositorioVideojuegos.save(new Videojuego("Resident Evil 7", "Miedo", 60.0f, 50));
			repositorioVideojuegos.save(new Videojuego("FIFA 17", "Deporte", 60.0f, 50));
		}

		@GetMapping("/")
		public String index(Model model) {
			return "index";
		}
		

		
		
		@RequestMapping("/noticias")
		public String tablon(Model model) {
			model.addAttribute("noticias", repositorioNoticias.findAll());
			return "noticias";
		}
		
		//Este Controlador funciona
		
		@RequestMapping("/noticia/{id}")
		public String verNoticia(Model model, @PathVariable long id) {
			Noticia noticia = repositorioNoticias.findOne(id);
			model.addAttribute("Noticia", noticia);
			return "ver_noticia";
		}
		
		@RequestMapping("/videojuegos")
		public String verVideojuegos(Model model){
			model.addAttribute("videojuegos",repositorioVideojuegos.findAll());
			return "videojuegos";
		}
		
		@RequestMapping("/videojuego/{id}")
		public String verVideojuego(Model model, @PathVariable long id){
			Videojuego videojuego = repositorioVideojuegos.findOne(id);
			model.addAttribute("Videojuego", videojuego);
			return "ver_videojuego";
		}


}
