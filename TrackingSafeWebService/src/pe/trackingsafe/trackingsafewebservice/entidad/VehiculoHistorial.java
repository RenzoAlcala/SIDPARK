package pe.trackingsafe.trackingsafewebservice.entidad;

public class VehiculoHistorial {

	private String codigoVehiculo;
	private String fecha;
	private Double velocidad;
	private Double accX;
	private Double accY;
	private Double accZ;
	private Double longitud;
	private Double latitud;
	
	public String getCodigoVehiculo() {
		return codigoVehiculo;
	}
	public void setCodigoVehiculo(String codigoVehiculo) {
		this.codigoVehiculo = codigoVehiculo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(Double velocidad) {
		this.velocidad = velocidad;
	}
	public Double getAccX() {
		return accX;
	}
	public void setAccX(Double accX) {
		this.accX = accX;
	}
	public Double getAccY() {
		return accY;
	}
	public void setAccY(Double accY) {
		this.accY = accY;
	}
	public Double getAccZ() {
		return accZ;
	}
	public void setAccZ(Double accZ) {
		this.accZ = accZ;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	
}
