/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReglaDifusa;

import OperadorDifuso.Operador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RALCALA
 */
public class ReglaSugeno extends Regla {
    
    private ArrayList<String> listaOperadores;
    private Map<String, String> operadoresFunciones;
    
    public ReglaSugeno(ArrayList<String> listaOperadores, Map<String, String> operadoresFunciones){
        this.listaOperadores = listaOperadores;
        this.operadoresFunciones = operadoresFunciones;
    }
    
    private Double evaluarConsecuente(String exprConsecuente,Double valorDisparoRegla,Map<String, Double> salidasDifusas){
        //SE DEBE DEFINIR UNA FUNCION PARA MODELAR LA SALIDA
        return valorDisparoRegla*salidasDifusas.get(exprConsecuente.replaceAll(" ", ""));
        
    }
    
    @Override
    public Object evaluar(Map<String,Double> entradasDifusa,Map<String, Double> salidasDifusas){
        try{
            String expresion = getExpr();
            String[] valoresExpr = expresion.split("=>");
            if(valoresExpr.length!=2){
                throw new Exception("Ocurrio un error al interpretar la regla, no ha indicado correctamente el símbolo de implicación");
            }
            String exprAntecedente = valoresExpr[0];
            String exprConsecuente = valoresExpr[1];
            double valorDisparo =  Double.parseDouble(evaluarAntecedente(entradasDifusa,exprAntecedente));
            return evaluarConsecuente(exprConsecuente,valorDisparo,salidasDifusas);
        }catch(Exception e){
            System.out.println("ERROR ReglaSugeno.evaluar: "+e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    private String evaluarAntecedente(Map<String,Double> entradasDifusa, String expr) {
        try{
            String valDifuso;
            if ((valDifuso=obtenerValorDifuso(entradasDifusa,expr.trim()))!=null) {
                return valDifuso;
            }

            int indMinOp = Integer.MAX_VALUE;
            String operador = "";
            for (String oper : listaOperadores) {
                int index = expr.indexOf(oper);
                if (index != -1 && indMinOp > index) {
                    indMinOp = index;
                    operador = oper;
                }
            }
            if (indMinOp == -1) {
                throw new Exception("Error: No se encontro un operador para calcular. Verifique que las entradas y los términos esten registrados en entradas.config.");
            }
            String miembroIzq = expr.substring(0, indMinOp);
            String miembroDer = expr.substring(indMinOp + operador.length(), expr.length());
            String valIzq = "";
            String valDer = "";
            if (hayOperador(miembroIzq)) {
                valIzq = evaluarAntecedente(entradasDifusa,miembroIzq);
            } else {
                valIzq = miembroIzq;
            }
            if (hayOperador(miembroDer)) {
                valDer = evaluarAntecedente(entradasDifusa,miembroDer);
            } else {
                valDer = miembroDer;
            }

            for (String oper : listaOperadores) {
                if (operador.equals(oper)) {
                    String claseOp = operadoresFunciones.get(oper);
                    Operador op = (Operador) Class.forName("OperadorDifuso."+claseOp).newInstance();
                    String valDifIzq;
                    String valDifDer;
                    if(esDouble(valIzq.replaceAll(" ", ""))){
                        valDifIzq = valIzq.replaceAll(" ", "");
                    }else{
                        valDifIzq = entradasDifusa.get(valIzq.replaceAll(" ", ""))+"";
                    }
                    if(esDouble(valDer.replaceAll(" ", ""))){
                        valDifDer = valDer.replaceAll(" ", "");
                    }else{
                        valDifDer = entradasDifusa.get(valDer.replaceAll(" ", ""))+"";
                    }
                    
                    //String valDifIzq = entradasDifusa.get(valIzq.replaceAll(" ", ""));
                    //String valDifDer = entradasDifusa.get(valDer.replaceAll(" ", ""));
                    return ""+op.calcular(Double.parseDouble(valDifIzq), Double.parseDouble(valDifDer));
                }
            }
        }catch(Exception e){
            System.out.println("ERROR ReglaDifusa.ReglaSugeno: "+e.getMessage());
            e.printStackTrace();
        }
        return "";

    }
    
    private String obtenerValorDifuso(Map<String,Double> entradasDifusa,String expr){
        String exprLimpio = expr.replaceAll(" ", "");
        return (entradasDifusa.get(exprLimpio)==null)?null:entradasDifusa.get(exprLimpio)+"";
    }
    
    private boolean esDouble(String valor){
        try{Double.parseDouble(valor);return true;}catch(Exception e){}return false;
    }
    
    public String obtenerConsecuente(){
        return getExpr().split("=>")[1].trim().replaceAll(" ", "");
    }
    
    public String obtenerAntecedente(){
        return getExpr().split("=>")[0].trim().replaceAll(" ", "");
    }
    
    private  boolean hayOperador(String expresion) {
        for (String operador : listaOperadores) {
            if (expresion.contains(operador)) {
                return true;
            }
        }
        return false;
    }
 
    public static void main(String[] args) {
        ArrayList<String> listaOperadores = new ArrayList();
        listaOperadores.add("AND");
        listaOperadores.add("OR");
        Map<String, String> operadoresFunciones = new HashMap();
        operadoresFunciones.put("AND", "OperadorAND");
        operadoresFunciones.put("OR", "OperadorOR");
        ReglaSugeno rs = new ReglaSugeno(listaOperadores,operadoresFunciones);
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
        Object res = rs.evaluar(entradasDifusas,salidas);
        System.out.println("Resultado: "+res.toString());
        
    }
    
}
