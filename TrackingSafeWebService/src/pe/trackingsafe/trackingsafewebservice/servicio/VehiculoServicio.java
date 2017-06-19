package pe.trackingsafe.trackingsafewebservice.servicio;

import pe.trackingsafe.trackingsafewebservice.entidad.Vehiculo;

public interface VehiculoServicio {

	public Vehiculo obtenerVehiculo(String placa);
	public boolean actualizarVehiculo(Vehiculo vehiculo);
	public boolean eliminarVehiculo(String placa);
}
