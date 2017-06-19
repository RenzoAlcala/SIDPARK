package pe.trackingsafe.trackingsafewebservice.entidad;

public class MensajeError {
	private String mensaje;
	private String clase;
	
	public MensajeError(String mensaje, String clase){
		this.mensaje=mensaje;
		this.clase = clase;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	
	
}
