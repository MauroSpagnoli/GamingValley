package Mauro;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorTablon{

		@Autowired
		private NoticiaRepository repositorioNoticias;
		@Autowired
		private VideojuegoRepository repositorioVideojuegos;
		@Autowired
		private ValoracionRepository repositorioValoraciones;
		@Autowired
		private UsuarioRepository repositorioUsuarios;
		
		@PostConstruct
		public void init() {
			repositorioNoticias.save(new Noticia("Nueva PS5", "2017-01-29", "Se ha diseñado y creado la nueva PS5."));
			repositorioNoticias.save(new Noticia("Resident Evil 7", "2017-01-29", "Resident Evil 7 rompe los records de ventas en un día."));
			Videojuego ResidentEvil7 = new Videojuego("Resident Evil 7", "Miedo", 60, 50.0f);
			Videojuego FIFA17 = new Videojuego("FIFA 17", "Deporte", 60, 60.0f);
			Usuario usuarioMauro = new Usuario("Mauro","Spagnoli","C/Alcalde de Mostoles 33","Mostoles");
			Usuario usuarioMiguel= new Usuario("Miguel","Robledo","C/Alcalde de Mostoles 33","Mostoles");
			repositorioUsuarios.save(usuarioMauro);
			repositorioUsuarios.save(usuarioMiguel);
			Valoracion valoracion1 = new Valoracion(6, "Buen juego", "28/01/2016",usuarioMauro);
			Valoracion valoracion2 = new Valoracion(8,"Decente", "28/01/2016",usuarioMiguel);
			repositorioValoraciones.save(valoracion1);
			repositorioValoraciones.save(valoracion2);
			//ResidentEvil7.getValoraciones().add(valoracion1);
			//ResidentEvil7.getValoraciones().add(valoracion2);
			FIFA17.getValoraciones().add(valoracion1);
			FIFA17.getValoraciones().add(valoracion2);
			repositorioVideojuegos.save(ResidentEvil7);
			repositorioVideojuegos.save(FIFA17);	
		}
				
		@RequestMapping("/noticias")
		public String tablon(Model model) {
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
		
		@RequestMapping("/videojuego/{id1}/ver_valoracion/{id2}")
		public String verValoracion(Model model, @PathVariable long id1, @PathVariable int id2){
			Videojuego videojuego = repositorioVideojuegos.findOne(id1);
			Valoracion valoracion = videojuego.getValoraciones().get(id2);
			model.addAttribute("Valoracion",valoracion);
			return "ver_valoracion";
		}
		
		@PostMapping("/noticias/nueva_noticia")
		public String agregarNoticia(Model model,Noticia noticia) {
			repositorioNoticias.save(noticia);
			return "noticia_guardada";
		}
		@PostMapping("videojuego/{id}/nueva_valoracion")
		public String agregarValoracion(Model model, @PathVariable long id, Valoracion valoracion){
			Videojuego videojuego = this.repositorioVideojuegos.findOne(id);
			model.addAttribute("id",id);
			Usuario usuarioEncontrado = repositorioUsuarios.getOne(1L);
			valoracion.setAutor(usuarioEncontrado);
			repositorioValoraciones.save(valoracion);
			return "valoracion_guardada";
		}
		
		@PostMapping("/videojuegos/agregar_videojuego")
		public String nuevoJuego(Model model, Videojuego videojuego) {
			repositorioVideojuegos.save(videojuego);
			return "videojuego_guardado";
		}


}
