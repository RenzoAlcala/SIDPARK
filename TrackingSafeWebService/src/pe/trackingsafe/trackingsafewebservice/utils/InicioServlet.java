package pe.trackingsafe.trackingsafewebservice.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.trackingsafe.trackingsafewebservice.dao.DAOFactory;
import pe.trackingsafe.trackingsafewebservice.firebase.FirebaseConfig;

public class InicioServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("");
		Properties prop = new Properties();
		prop.load(new FileInputStream("db.properties"));
		UtilConexion.inicializaDataSource(prop);
		UtilConexion.getConnection();
		DAOFactory.setTipobd(prop.getProperty("bd"));
		FirebaseConfig.initFirebase(prop);

	}

	
	
}
