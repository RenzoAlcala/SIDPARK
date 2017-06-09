/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inferencia;

import OperadorDifuso.*;
import ReglaDifusa.Regla;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RALCALA
 */
public abstract class InferenciaBorrosa {

    static String expr = "1 AND 2 OR 3  ";
    protected ArrayList<String> listaOperadores;
    protected Map<String,String> operadoresFunciones;
    protected ArrayList<Regla> listaReglas;

    public abstract void cargarOperadores();
    public abstract void cargarReglas();
    public abstract Object implicacion(Map<String,Double> resultadoReglas);
    public abstract Object inferencia(Map<String,String> entradasDifusas,Map<String,Double> salidas);


}
