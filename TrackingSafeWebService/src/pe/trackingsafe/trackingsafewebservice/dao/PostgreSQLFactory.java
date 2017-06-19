package pe.trackingsafe.trackingsafewebservice.dao;

import pe.trackingsafe.trackingsafewebservice.dao.implpostgresql.PostgreSQLVehiculoDAO;

public class PostgreSQLFactory extends DAOFactory {

	@Override
	public VehiculoDAO getVehiculoDAO() {
		return new PostgreSQLVehiculoDAO();
	}

	
	
}
