package BorrosificadorDifuso;


import FuncionPertenencia.MembershipFunction;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RALCALA
 */
public class VariableLinguistica {
 
    private String nombre;
    private ArrayList<MembershipFunction> terminosLinguisticos;
    private int min=0, max=0;
    
    public VariableLinguistica(){
        terminosLinguisticos = new ArrayList<>();
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<MembershipFunction> getTerminosLinguisticos() {
        return terminosLinguisticos;
    }

    public void setTerminosLinguisticos(ArrayList<MembershipFunction> terminosLinguisticos) {
        this.terminosLinguisticos = terminosLinguisticos;
    }
    
    
}
