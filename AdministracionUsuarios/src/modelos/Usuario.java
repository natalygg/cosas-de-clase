package modelos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="usuario")
@SessionScoped
public class Usuario {

	@ManagedProperty(value="DAOUsuario")
	private DAOUsuario dao;
	private String nombre;
	private String dni;
	private String password;
	
	
	public Usuario(){
		
	}
	
	public Usuario(String dni,String nombre,String password){
		this.dni=dni;
		this.nombre=nombre;
		this.password=password;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public String insertar(){
		
		DAOUsuario.Resultado r=dao.create(this);
		if(r==DAOUsuario.Resultado.OK){
			return "index.xhtml";
		}
		else if(r==DAOUsuario.Resultado.YA_EXISTE){
			return "yaexiste.xhtml";
		}
		else{
			return "error.xhtml";
		}
		
	}
	
	
}
