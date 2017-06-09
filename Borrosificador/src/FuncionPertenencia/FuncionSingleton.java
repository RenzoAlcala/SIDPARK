/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionPertenencia;

/**
 *
 * @author RALCALA
 */
public class FuncionSingleton extends MembershipFunction {
    private double valor;
    

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
    @Override
    public double compute(double x) {
        return valor;
    }
    
}
