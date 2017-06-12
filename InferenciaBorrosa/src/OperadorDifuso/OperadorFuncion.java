/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperadorDifuso;

/**
 *
 * @author RALCALA
 */
public class OperadorFuncion {
    
    public static double calcular(String keyOperador, double a, double b){
      if(keyOperador.equals(DefineOperador.O_MAX)){
          return Math.max(a, b);
      }  
      if(keyOperador.equals(DefineOperador.O_MIN)){
          return Math.min(a, b);
      }  
      if(keyOperador.equals(DefineOperador.O_PROD)){
          return (a*b);
      }  
      
      return -1;
    }
    
}
