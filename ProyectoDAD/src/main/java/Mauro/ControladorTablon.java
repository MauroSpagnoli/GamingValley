package Mauro;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ControladorTablon{

		@Autowired
		private NoticiaRepository repositorioNoticias;
		private VideojuegoRepository repositorioVideojuegos;
		
		@PostConstruct
		public void init() {
			repositorioNoticias.save(new Noticia("Nueva PS5", "Se ha diseñado y creado la nueva PS5.", "29/01/2017"));
			repositorioNoticias.save(new Noticia("Resident Evil 7", "Resident Evil 7 rompe los records de ventas en un día.", "29/01/2017"));
		}

		@GetMapping("/index")
		public String index(Model model) {
			model.addAttribute("noticias", repositorioNoticias.findAll());
			model.addAttribute("videojuegos", repositorioVideojuegos.findAll());
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

}
