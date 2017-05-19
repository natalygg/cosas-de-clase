package dao;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.Equipo;

public class DAOEquipo {
	
	
	
	class EquipoRowMapper implements RowMapper<Equipo>{
		
		public Equipo mapRow(ResultSet rs,int numRow) throws SQLException{
			Equipo e=new Equipo(
					rs.getInt("id"),
					Equipo.TipoEquipo.valueOf(rs.getString("tipo")),
					rs.getString("ubicacion"),
					rs.getString("modelo"),
					new java.util.Date(rs.getDate("fecha_instalacion").getTime()));
			
			return e;
		}
		
	}
	
	private DataSource dataSource;
	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean create(Equipo e){
		boolean r=false;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="insert into equipos (tipo,ubicacion,modelo,fecha_instalacion)"
				+ "values (?,?,?,?)";
		java.sql.Date d=new java.sql.Date(e.getFechaInstalacion().getTime());
		try{
			jdbc.update(
				sql,
				new Object[]{e.getTipo().name(),e.getUbicacion(),e.getModelo(),d});
			r=true;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		return r;		
	}
	
	public Equipo read(int id){
		Equipo e=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from equipos where id=?";
		try{
			e=jdbc.queryForObject(sql,new Object[]{id},new EquipoRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return e;
	}
	
	public boolean update(Equipo e){
		boolean r=false;
		
		String sql="update equipos set "
					+ "tipo=?,"
					+ "ubicacion=?,"
					+ "modelo=?,"
					+ "fecha_instalacion=? "
				+ "where id=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							e.getTipo().name(),
							e.getUbicacion(),
							e.getModelo(),
							new java.sql.Date(e.getFechaInstalacion().getTime()),
							e.getId()});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return r;
	}
	
	public List<Equipo> listar(){
		List<Equipo> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from equipos";
		lista=jdbc.query(sql,new EquipoRowMapper());
		return lista;
	}
	
	public boolean delete(int id){
		boolean r=false;
		
		String sql="delete from equipos where id=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{id});
		r=n>0;
		
		return r;
	}
	
}
