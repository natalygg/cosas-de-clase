package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

@ManagedBean(name="DAOUsuario")
@ApplicationScoped
public class DAOUsuario {

	public enum Resultado{
		OK,ERROR,YA_EXISTE
	}
	
	@Resource(name="Administracion_Usuarios_DS")
	private DataSource ds;
	
	public Resultado create(Usuario u){
		Resultado r=Resultado.ERROR;
		
		String sql="insert into usuarios (dni,nombre,password) values (?,?,?)";
		try(Connection con=ds.getConnection();
			PreparedStatement ps=con.prepareStatement(sql)){
			
			ps.setString(1, u.getDni());
			ps.setString(1,u.getNombre());
			ps.setString(2,u.getPassword());
			int n=ps.executeUpdate();
			if(n>0){
				r=Resultado.OK;
			}
			else{
				r=Resultado.YA_EXISTE;
			}
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return r;
	}
	
	
	public ArrayList<Usuario> getLista(){
		ArrayList<Usuario> lista=new ArrayList<Usuario>();
		
		String sql="select * from usuarios order by nombre asc";
		try(Connection con=ds.getConnection();
		    Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sql)){
			
			while(rs.next()){
				lista.add(new Usuario(
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("password")));
			}
			
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return lista;
	}
	
	public boolean borrar(Usuario u){
		boolean r=false;
		
		String sql="delete from usuarios where dni=?";
		try(Connection con=ds.getConnection();
			PreparedStatement ps=con.prepareStatement(sql)){
			
			ps.setString(1, u.getDni());
			ps.executeUpdate();
			
			r=true;
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return r;
	}
	
}
