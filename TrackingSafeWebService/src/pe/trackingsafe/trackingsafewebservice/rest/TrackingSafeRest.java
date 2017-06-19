package pe.trackingsafe.trackingsafewebservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import pe.trackingsafe.trackingsafewebservice.entidad.MensajeError;
import pe.trackingsafe.trackingsafewebservice.entidad.Vehiculo;
import pe.trackingsafe.trackingsafewebservice.servicio.VehiculoServicio;
import pe.trackingsafe.trackingsafewebservice.servicioimpl.VehiculoServicioImpl;
 
@Path("/trackingsafeservice")
public class TrackingSafeRest {
 
    @GET
    @Path("/vehiculo/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getVehiculo(@PathParam("placa") String placa) throws JSONException {
    	VehiculoServicio vs;
    	Vehiculo vehiculo;
    	try{
    		 vs = new VehiculoServicioImpl();
    		 vehiculo = vs.obtenerVehiculo(placa);
    		 
    		 if(vehiculo==null){
    			 return new MensajeError("No se ha encontrado dicho vehiculo.","Error ObtenerVehiculo");
    		 }
    		 
    	}catch(Exception e){
    		e.printStackTrace();
    		return new MensajeError(e.getMessage(),e.toString());
    	}
        return vehiculo;
    }
 
    @Path("{word}")
    @GET
    @Produces("application/json")
    public Response reverser(@PathParam("word") String word) throws JSONException {
 
        StringBuilder sb = new StringBuilder();
        sb.append(word);
 
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("original", sb.toString());
        jsonObject.put("reversed", sb.reverse().toString());
 
        String result = "" + jsonObject;
        return Response.status(200).entity(result).build();
    }
 
}