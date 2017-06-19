package pe.trackingsafe.trackingsafewebservice.firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {

	public static FirebaseDatabase database;
	
	public static void initFirebase(Properties prop) throws IOException{
		FileInputStream serviceAccount = new FileInputStream(prop.getProperty("keytiemporeal"));

		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		    .setDatabaseUrl(prop.getProperty("urltiemporeal"))
		    .setDatabaseAuthVariableOverride(null)
		    .build();
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		FirebaseApp myApp=null;
		boolean hasBeenInitialized = false;
		for(FirebaseApp app : firebaseApps){
			 if(app.getName().equals("TrackingSafe")){
				 hasBeenInitialized=true;
				 myApp = app;
			 }
		}
		
		if(!hasBeenInitialized)
			myApp = FirebaseApp.initializeApp(options,"TrackingSafe");
		
		
		database = FirebaseDatabase.getInstance(myApp);
	}
	
}
