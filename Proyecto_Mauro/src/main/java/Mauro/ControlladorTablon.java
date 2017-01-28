package Mauro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControlladorTablon {

	private List<Noticia> Noticias = new ArrayList<>();
	
	@PostMapping("/noticia/nuevo")
	public String nuevaNoticia(Model model, Noticia noticia) {

		Noticias.add(noticia);

		return "anuncio_guardado";
	}
	
	@GetMapping("/anuncio/{num}")
	public String verAnuncio(Model model, @PathVariable int num) {

		Noticia noticia = Noticias.get(num - 1);

		model.addAttribute("noticia", noticia);

		return "MostrarNoticia";
	}
}
