/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReglaDifusa;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author RALCALA
 */
public abstract class Regla {
    private int id;
    private String expresion;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpr() {
        return expresion;
    }

    public void setExpr(String expr) {
        this.expresion = expr;
    }
    
    
     public abstract Object evaluar(Map<String,Double> entradasDifusa,Map<String, Double> salidasDifusasa);
    
    
}
