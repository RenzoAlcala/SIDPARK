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
public class OperadorAND extends Operador {

    public OperadorAND(){
        setId(1);
        setNombre("AND");
    }
    
    @Override
    public double calcular(double a, double b) {
        return Math.min(a, b);
    }
    
}
