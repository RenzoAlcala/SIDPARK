/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sib;

import Inferencia.InferenciaBorrosaSugeno;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RALCALA
 */
public class SistemaInferenciaBorrosa {

    
    public static double procesamientoDifuso(Map<String,Double> entradasCrisp,Map<String,Double> salidaCrisp){
        BorrosificadorDifuso.EvaluadorDifuso ed = new BorrosificadorDifuso.EvaluadorDifuso();
        Map<String,Double> borrosificados = ed.borrosificarEntradas(entradasCrisp);
        InferenciaBorrosaSugeno ibs = new InferenciaBorrosaSugeno();
        double peligrosidad = (Double)ibs.inferencia(borrosificados, salidaCrisp);
        return peligrosidad;
        
    }
    
    public static void main(String[] args) {
        Map<String, Double> entradasCrisp = new HashMap();
        entradasCrisp.put("AccX", 1d);
        entradasCrisp.put("AccY", 1d);
        entradasCrisp.put("AccZ", 1d);
        entradasCrisp.put("Vel", 1d);
        Map<String, Double> salidaCrisp = new HashMap();
        salidaCrisp.put("Pel==Alto", 1d);
        SistemaInferenciaBorrosa.procesamientoDifuso(entradasCrisp,salidaCrisp);
    }
}
