package pe.trackingsafe.trackingsafewebservice.dao;

public abstract class DAOFactory {

	private static String tipobd;
	
	public static DAOFactory getDAOFactory(){
		try{
			if(tipobd!=null && !tipobd.equals("")){
				if(tipobd.equals("postgresql")){
					return new PostgreSQLFactory();
				}else{
					throw new Exception("Error DAOFactory.getDAOFactory:No se encuentra implementado los métodos para ese tipo de BD.");
				}
				
			}else{
				String mensaje = "Error DAOFactory.getDAOFactory: El campo BD en la configuración esta vacio o es nulo.";
				System.out.println(mensaje);
				throw new Exception(mensaje);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	public abstract VehiculoDAO getVehiculoDAO();
	
	public static void setTipobd(String tipobd) {
		DAOFactory.tipobd = tipobd;
	}
	
	
}
