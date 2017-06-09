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
public class OperadorOR extends Operador{

    public OperadorOR(){
        setId(2);
        setNombre("OR");
    }
    
    @Override
    public double calcular(double a, double b) {
        return Math.max(a,b);
    }
    
}
