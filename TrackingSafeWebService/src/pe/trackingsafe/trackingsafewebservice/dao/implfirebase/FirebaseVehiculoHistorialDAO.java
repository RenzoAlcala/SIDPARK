package pe.trackingsafe.trackingsafewebservice.dao.implfirebase;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.trackingsafe.trackingsafewebservice.dao.VehiculoHistorialDAO;
import pe.trackingsafe.trackingsafewebservice.entidad.VehiculoHistorial;
import pe.trackingsafe.trackingsafewebservice.firebase.FirebaseConfig;

public class FirebaseVehiculoHistorialDAO implements VehiculoHistorialDAO{

	@Override
	public VehiculoHistorial getVehiculoHistorialFecha(String placa,
			String fechainicio, String fechafin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehiculoHistorial getPosicionActual(String placa) {
		return null;
	}
	
	@Override
	public void setPosicionActual(VehiculoHistorial vh) {
		DatabaseReference ref = FirebaseConfig.database.getReference("vehiculos/");
		Map<String, VehiculoHistorial> vehiculo = new HashMap<String, VehiculoHistorial>();
		
		ref.setValue(vehiculo);
	}
	

}
