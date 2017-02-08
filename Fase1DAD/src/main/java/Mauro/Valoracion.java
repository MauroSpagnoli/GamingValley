package Mauro;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Valoracion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private	int calificacion;
	private String comentario;
	private String fecha;
	@OneToOne 
	private Usuario autor;
	
	
	public Valoracion(int calificacion, String comentario, String fecha, Usuario autor) {
		super();
		this.calificacion = calificacion;
		this.comentario = comentario;
		this.fecha = fecha;
		this.autor = autor;
	}
	public Valoracion(){
		
	}

	//Getter y Setters

	public int getCalificacion(){
		return this.calificacion;
	}
	
	public void setCalificacion(int calificacion){
		this.calificacion=calificacion;
	}
	
	public String getComentario(){
		return this.comentario;
	}
	
	public void setComentario(String comentario){
		this.comentario=comentario;
	}
	
	public String getFecha(){
		return this.fecha;
	}
	
	public void setFecha(String fecha){
		this.fecha=fecha;
	}
	
	public Usuario getAutor(){
		return this.autor;
	}
	
	public void setAutor(Usuario autor){
		this.autor=autor;
	}

	
	@Override
	public String toString() {
		return "Valoracion [id=" + id + ", calificacion=" + calificacion + ", comentario=" + comentario + ", fecha="
				+ fecha + ", autor=" + autor + "]";
	}
	
	
	
	

}