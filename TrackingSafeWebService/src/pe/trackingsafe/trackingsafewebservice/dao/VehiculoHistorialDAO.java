package pe.trackingsafe.trackingsafewebservice.dao;

import pe.trackingsafe.trackingsafewebservice.entidad.VehiculoHistorial;


public interface VehiculoHistorialDAO {

	public VehiculoHistorial getVehiculoHistorialFecha(String placa, String fechainicio, String fechafin);
	public VehiculoHistorial getPosicionActual(String placa);
	public void setPosicionActual(VehiculoHistorial vh);
}
