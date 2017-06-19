package pe.trackingsafe.trackingsafewebservice.dao.implpostgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pe.trackingsafe.trackingsafewebservice.dao.VehiculoDAO;
import pe.trackingsafe.trackingsafewebservice.entidad.Vehiculo;

public class PostgreSQLVehiculoDAO implements VehiculoDAO{

	public PostgreSQLVehiculoDAO(){
	}
	
	@Override
	public Vehiculo obtenerVehiculo(Connection con,String placa) {
		StringBuilder sb;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Vehiculo vehiculo = null;
		try{
			sb = new StringBuilder("");
			sb.append("SELECT codigo, conductor,marca,modelo,año,color,comentarios ");
			sb.append("FROM tlbVehiculo ");
			sb.append("WHERE placa = ?");
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, placa);
			rs = ps.executeQuery();
			
			if(rs.next()){
				vehiculo = new Vehiculo();
				vehiculo.setPlaca(placa);

				if(rs.getString(1)!=null){
					vehiculo.setCodigo(rs.getString(1));
				}
				if(rs.getString(2)!=null){
					vehiculo.setDniConductor(rs.getString(2));
				}
				if(rs.getString(5)!=null){
					vehiculo.setMarca(rs.getString(3));
				}
				if(rs.getString(4)!=null){
					vehiculo.setModelo(rs.getString(4));
				}
				if(rs.getString(5)!=null){
					vehiculo.setAño(Integer.parseInt(rs.getString(5)));
				}
				if(rs.getString(6)!=null){
					vehiculo.setColor(rs.getString(6));
				}
				if(rs.getString(7)!=null){
					vehiculo.setComentarios(rs.getString(7));
				}
				
			}
			
		}catch(Exception e){
			System.out.println("Error PostgreSQL:");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null)
				ps.close();
				
				if(rs!=null)
				rs.close();
				
				if(con!=null)
				con.close();
			}catch(Exception e){
				System.out.println("Error PostgreSQL finally:");
				e.printStackTrace();
			}
		}


		return vehiculo;
	}

	@Override
	public void actualizarVehiculo(Connection con,Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarVehiculo(Connection con,String placa) {
		// TODO Auto-generated method stub
		
	}
	

}
