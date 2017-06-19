package pe.trackingsafe.trackingsafewebservice.servicioimpl;

import java.sql.Connection;

import pe.trackingsafe.trackingsafewebservice.dao.DAOFactory;
import pe.trackingsafe.trackingsafewebservice.dao.VehiculoDAO;
import pe.trackingsafe.trackingsafewebservice.entidad.Vehiculo;
import pe.trackingsafe.trackingsafewebservice.servicio.VehiculoServicio;
import pe.trackingsafe.trackingsafewebservice.utils.UtilConexion;

public class VehiculoServicioImpl implements VehiculoServicio{

	@Override
	public Vehiculo obtenerVehiculo(String placa) {
		VehiculoDAO vehiculoDAO = DAOFactory.getDAOFactory().getVehiculoDAO();
		Connection con = UtilConexion.getConnection();
		String plac = "";
		Vehiculo vehiculo;
		try{
			if(placa!=null){
				plac = placa.trim();
				 vehiculo = vehiculoDAO.obtenerVehiculo(con, plac);
			}else{
				return null;
			}
		}catch(Exception e){
			throw e;
		}
		return vehiculo;
	}

	@Override
	public boolean actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarVehiculo(String placa) {
		// TODO Auto-generated method stub
		return false;
	}

}
