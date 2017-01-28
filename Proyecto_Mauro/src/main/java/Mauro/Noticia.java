package Mauro;

public class Noticia {
	
	private String Titulo;
	private String Descripcion;
	public Noticia(){
		
	}
	
	public Noticia(String Titulo, String Descripcion){
		this.Descripcion = Descripcion;
		this.Titulo = Titulo;
	}

	public String getTitulo() {
		return this.Titulo;
	}

	public void setTitulo(String titulo) {
		this.Titulo = titulo;
	}

	public String getDescripcion() {
		return this.Descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Noticia [Titulo=" + this.Titulo + ", Descripcion=" + this.Descripcion + "]";
	}
	
	
}
