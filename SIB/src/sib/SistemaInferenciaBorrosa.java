/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sib;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RALCALA
 */
public class SistemaInferenciaBorrosa {

    
    public static Map<String,Double> procesamientoDifuso(Map<String,Double> entradasCrisp){
        BorrosificadorDifuso.EvaluadorDifuso ed = new BorrosificadorDifuso.EvaluadorDifuso();
        Map<String,Double> borrosificados = ed.borrosificarEntradas(entradasCrisp);
        return borrosificados;
        
    }
    
    public static void main(String[] args) {
        Map<String, Double> entradasCrisp = new HashMap();
        entradasCrisp.put("AccX", 1d);
        entradasCrisp.put("AccY", 1d);
        entradasCrisp.put("AccZ", 1d);
        entradasCrisp.put("Vel", 1d);
        Map<String, Double> salidaCrisp = new HashMap();
        entradasCrisp.put("Pel", 1d);
        SistemaInferenciaBorrosa.procesamientoDifuso(entradasCrisp);
    }
}
