package pe.trackingsafe.trackingsafewebservice.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class UtilConexion {

	public static BasicDataSource dataSource;
	
    public static void inicializaDataSource(Properties prop){


        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(prop.getProperty("driver"));
        basicDataSource.setUsername(prop.getProperty("user"));
        basicDataSource.setPassword(prop.getProperty("password"));
        //basicDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        basicDataSource.setUrl(prop.getProperty("url"));
        basicDataSource.setMaxActive(Integer.parseInt(prop.getProperty("cantconexiones")));

        dataSource = basicDataSource;

    }

    public static Connection getConnection(){
    	
    	Connection conn = null;
    	try {
    	
			 conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return conn;
    	
    }
}

