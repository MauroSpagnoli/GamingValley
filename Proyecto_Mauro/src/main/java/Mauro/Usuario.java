package Mauro;

public class Usuario {
	
	private String Nombre;
	private String Nick;
	private String Email;
	private String Password;

	public Usuario(){
		
	}
	public Usuario(String Nombre, String Nick, String Email, String Password){
		this.Nombre = Nombre;
		this.Nick = Nick;
		this.Email = Email;
		this.Password = Password;
	}
	
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String getNick() {
		return Nick;
	}
	
	public void setNick(String nick) {
		Nick = nick;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "Usuario [Nombre=" + Nombre + ", Nick=" + Nick + ", Email=" + Email + ", Password=" + Password + "]";
	}
	
	
}
