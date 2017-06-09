/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inferencia;

import BorrosificadorDifuso.EvaluadorDifuso;
import ReglaDifusa.Regla;
import ReglaDifusa.ReglaSugeno;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RALCALA
 */
public class InferenciaBorrosaSugeno extends InferenciaBorrosa {

    
    public InferenciaBorrosaSugeno(){
        cargarOperadores();
        cargarReglas();
    }
    
    @Override
    public Object inferencia(Map<String,String> entradasDifusas,Map<String,Double> salidas) {
        Map<String,Double> salidasDif = new HashMap<String,Double>();
        for (Regla regla : listaReglas) {
            double resultadoDif = (Double)regla.evaluar(entradasDifusas, salidas);
            String exprSalida = ((ReglaSugeno)regla).obtenerConsecuente();
            salidasDif.put(exprSalida, resultadoDif);
        }
        
        
        
        return null;
    }
    
    @Override
    public Object implicacion(Map<String,Double> resultadoReglas){
        
        for (Map.Entry<String,Double> resultado: resultadoReglas.entrySet()) {
            resultado.
        }
        
    }

    
    @Override
    public void cargarReglas() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        listaReglas = new ArrayList();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("config\\baseconocimiento.def");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            Regla regla;
            int i=0;
            while ((linea = br.readLine()) != null) {
                regla = new ReglaSugeno(listaOperadores,operadoresFunciones);
                regla.setExpr(linea);
                regla.setId(i);
                listaReglas.add(regla);
                i++;
            }
            
            System.out.println("");
            
        } catch (Exception e) {
            System.out.println("ERROR InferenciaBorrosa.cargarOperadores: Ocurrio un error al intentar leer el archivo de operadores");
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println("ERROR InferenciaBorrosa.cargarOperadores: Ocurrio un error al intentar cerrar la conexion del archivo");
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void cargarOperadores() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        operadoresFunciones = new HashMap(); 
        listaOperadores = new ArrayList();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("config\\operadores.config");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                operadoresFunciones.put(linea.split(",")[0], linea.split(",")[1]);
                listaOperadores.add(linea.split(",")[0]);
            }
            
            
            
        } catch (Exception e) {
            System.out.println("ERROR InferenciaBorrosa.cargarOperadores: Ocurrio un error al intentar leer el archivo de operadores");
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println("ERROR InferenciaBorrosa.cargarOperadores: Ocurrio un error al intentar cerrar la conexion del archivo");
                e2.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        InferenciaBorrosaSugeno ed = new InferenciaBorrosaSugeno();
        ReglaSugeno re = (ReglaSugeno)ed.listaReglas.get(0);
        
        ArrayList<String> listaOperadores = new ArrayList();
        listaOperadores.add("AND");
        listaOperadores.add("OR");
        Map<String, String> operadoresFunciones = new HashMap();
        operadoresFunciones.put("AND", "OperadorAND");
        operadoresFunciones.put("OR", "OperadorOR");
        ReglaSugeno rs = new ReglaSugeno(listaOperadores,operadoresFunciones);
        rs.setExpr("Vel ==Bajo AND AccX== Alto AND AccX   ==   Bajo => Pel == Alto");
        Map<String, String> entradasDifusas = new HashMap();
        entradasDifusas.put("Vel==Bajo", "0.1");
        entradasDifusas.put("Vel==Alto", "0.2");
        entradasDifusas.put("Vel==Medio", "0.3");
        entradasDifusas.put("AccX==Alto", "0.4");
        entradasDifusas.put("AccX==Bajo", "0.5");
        entradasDifusas.put("AccX==Medio", "0.6");
        Map<String, Double> salidas = new HashMap();
        salidas.put("Pel==Alto", 1d);
        System.out.println(re.evaluar(entradasDifusas, salidas));
    }
    
    
}
