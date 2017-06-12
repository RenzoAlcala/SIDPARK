/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inferencia;

import BorrosificadorDifuso.EvaluadorDifuso;
import OperadorDifuso.DefineOperador;
import OperadorDifuso.OperadorFuncion;
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
    public Object inferencia(Map<String,Double> entradasDifusas,Map<String,Double> salidas) {
        
        double resultadoFinal = 0;
        //SE ENCUENTRAN LOS VALORES DIFUSOS DE CADA REGLA
        try{
            Map<String,Double> salidasDif = new HashMap<>();
            for (Regla regla : listaReglas) {
                double resultadoDif = (Double)regla.evaluar(entradasDifusas, salidas);
                String exprSalida = ((ReglaSugeno)regla).obtenerConsecuente();
                if(salidasDif.containsKey(exprSalida)){
                    salidasDif.put(exprSalida, OperadorFuncion.calcular(listaOperadores.get(DefineOperador.K_AGREGACION),(double)salidasDif.get(exprSalida),resultadoDif));
                }else{
                    salidasDif.put(exprSalida, resultadoDif);
                }
            }

            //SE REALIZA LA AGREGACION DE LAS SALIDAS (MAX)
            //Map<String,Double> agregacionReglas = (Map<String,Double>)agregacion(salidasDif);

            //
            double denominador = 0;

            for (Map.Entry<String,Double> reglaDif: salidasDif.entrySet()) {
                String reglaKey = reglaDif.getKey();
                double valorDisparo = reglaDif.getValue();
                if(salidas.get(reglaKey)==null){
                    throw new Exception("InferenciaBorrosaSugena.inferencia: Error no se encontro valor de salida de la regla");
                }
                //double valorSalida = salidas.get(reglaKey);
                //resultadoFinal += valorSalida*valorDisparo;
                resultadoFinal += valorDisparo;
                denominador += salidas.get(reglaKey);

            }
            resultadoFinal /= (denominador);
        }catch(Exception e){
            System.out.println("ERROR InferenciaBorrosaSugena.inferencia: "+e.getMessage());
            e.printStackTrace();
        }
        
        return resultadoFinal;
    }
    /*
    @Override
    public Object agregacion(Map<String,Object> resultadoReglas){
        Map<String,Double> agregacionReglas = new HashMap();
        try{
            for (Map.Entry<String,Object> resultado1: resultadoReglas.entrySet()) {
                if(agregacionReglas.containsKey(resultado1.getKey())){
                    agregacionReglas.put(resultado1.getKey(),(Double)resultado1.getValue());
                }else{
                    Double val = agregacionReglas.get(resultado1.getKey());
                    String keyAgregacion = "AGREGACION";
                    String claseAgregacion = operadoresFunciones.get(keyAgregacion);
                    Operador op = (Operador) Class.forName("OperadorDifuso."+claseAgregacion).newInstance();
                    agregacionReglas.put(resultado1.getKey(), op.calcular(val,(Double)resultado1.getValue()));
                }
            }
        }catch(Exception e){
            System.out.println("ERROR InferenciaBorrosa.agregacion: agregacion");
            e.printStackTrace();
            return null;
        }
        
        return agregacionReglas;
    }
*/
    
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
                regla = new ReglaSugeno(listaOperadores);
                regla.setExpr(linea);
                regla.setId(i);
                listaReglas.add(regla);
                i++;
            }
            
            System.out.println("");
            
        } catch (Exception e) {
            System.out.println("ERROR InferenciaBorrosa.cargarReglas: Ocurrio un error al intentar leer el archivo de operadores");
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
                System.out.println("ERROR InferenciaBorrosa.cargarReglas: Ocurrio un error al intentar cerrar la conexion del archivo");
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void cargarOperadores() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        listaOperadores = new HashMap();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("config\\operadores.config");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                listaOperadores.put(linea.split(",")[0], linea.split(",")[1]);
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
        
        Map<String,String> listaOperadores = new HashMap();
        listaOperadores.put("AND","MIN");
        listaOperadores.put("OR","MAX");
        listaOperadores.put("AGREGACION","MAX");
        listaOperadores.put("IMPLICACION","MIN");
        ReglaSugeno rs = new ReglaSugeno(listaOperadores);
        rs.setExpr("Vel ==Bajo AND AccX== Alto AND AccX   ==   Bajo => Pel == Alto");
        Map<String, Double> entradasDifusas = new HashMap();
        entradasDifusas.put("Vel==Bajo", 0.1d);
        entradasDifusas.put("Vel==Alto", 0.2d);
        entradasDifusas.put("Vel==Medio", 0.3d);
        entradasDifusas.put("AccX==Alto", 0.4d);
        entradasDifusas.put("AccX==Bajo", 0.5d);
        entradasDifusas.put("AccX==Medio", 0.6d);
        Map<String, Double> salidas = new HashMap();
        salidas.put("Pel==Alto", 1d);
        System.out.println(re.evaluar(entradasDifusas, salidas));
    }
    
    
}
