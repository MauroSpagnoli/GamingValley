package es.MiguelMauroAlvaro;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class ControlladorTablonNoticias {

		@Autowired
		private NoticiaRepository repositorioNoticias;

		
		@PostConstruct
		public void init() {
			repositorioNoticias.save(new Noticia("Nueva PS5", "Se ha diseñado y creado la nueva PS5.", "29/01/2017"));
			repositorioNoticias.save(new Noticia("Resident Evil 7", "Resident Evil 7 rompe los records de ventas en un día.", "29/01/2017"));
		}
		
		
		@RequestMapping("/noticias/")
		public String tablon(Model model, Pageable page) {
			model.addAttribute("noticias", repositorioNoticias.findAll(page));
			return "noticias";
		}
		
		@RequestMapping("/noticia/{id}")
		public String verNoticia(Model model, @PathVariable long id) {
			Noticia noticia = repositorioNoticias.findOne(id);
			model.addAttribute("Noticia", noticia);
			return "ver_noticia";
		}

}
