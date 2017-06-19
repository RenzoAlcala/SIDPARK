package pe.trackingsafe.trackingsafewebservice.dao;

import java.sql.Connection;

import pe.trackingsafe.trackingsafewebservice.entidad.Vehiculo;


public interface VehiculoDAO {
	
	public Vehiculo obtenerVehiculo(Connection con,String placa);
	public void actualizarVehiculo(Connection con,Vehiculo vehiculo);
	public void eliminarVehiculo(Connection con,String placa);
	
	
}
