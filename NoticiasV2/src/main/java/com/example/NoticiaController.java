package com.example;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoticiaController {
	//private ArrayList<Noticia> noticias = new ArrayList<>();
	@Autowired
	private NoticiaRepository noticiasRepositorio;
	
	/*public NoticiaController(){
		noticias.add(new Noticia("Sacan la nueva PS5", "23-10-2018", "Han presentado en el E4 la nueva PS5"));
		noticias.add(new Noticia("En breves llegará Gaming Controller", "23-10-2018", "En un par de días, estara en la tienda en nuevo gamepad de Sonty"));
	}*/
	
	@PostConstruct
	public void init(){
		noticiasRepositorio.save(new Noticia("Sacan la nueva PS5", "23-10-2018", "Han presentado en el E4 la nueva PS5"));
		noticiasRepositorio.save(new Noticia("En breves llegará Gaming Controller", "23-10-2018", "En un par de días, estara en la tienda en nuevo gamepad de Sonty"));
	}

	
	@GetMapping("/")
	public String tablon(Model model) {
		model.addAttribute("noticias", noticiasRepositorio.findAll());

		return "tablonNoticias";
	}

	/* POR SI SE QUIERE AGREGAR UNA NUEVA NOTICIA
	@PostMapping("/noticia/nuevo")
	public String nuevoAnuncio(Model model, Noticia noticia) {
		noticias.save(noticia);
		return "noticiaGuardada";
	}*/

	@GetMapping("/noticia/{num}")
	public String verAnuncio(Model model, @PathVariable long num) {
		Noticia noticia = noticiasRepositorio.findOne(num);

		model.addAttribute("noticia", noticia);

		return "verNoticia";
	}
	
}
