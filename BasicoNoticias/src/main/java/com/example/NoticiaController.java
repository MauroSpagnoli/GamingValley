package com.example;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoticiaController {
	private ArrayList<Noticia> noticias = new ArrayList<>();
	
	public NoticiaController(){
		noticias.add(new Noticia("Sacan la nueva PS5", "23-10-2018", "Han presentado en el E4 la nueva PS5"));
		noticias.add(new Noticia("En breves llegará Gaming Controller", "23-10-2018", "En un par de días, estara en la tienda en nuevo gamepad de Sonty"));
	}
	
	@GetMapping("/")
	public String tablon(Model model) {
		model.addAttribute("noticias", noticias);

		return "tablonNoticias";
	}

	@PostMapping("/noticia/nuevo")
	public String nuevoAnuncio(Model model, Noticia noticia) {

		noticias.add(noticia);

		return "noticiaGuardada";

	}

	@GetMapping("/noticia/{num}")
	public String verAnuncio(Model model, @PathVariable int num) {

		Noticia noticia = noticias.get(num - 1);

		model.addAttribute("noticia", noticia);

		return "verNoticia";
	}
	
}
