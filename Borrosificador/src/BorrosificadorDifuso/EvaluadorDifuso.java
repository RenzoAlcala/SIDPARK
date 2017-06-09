package BorrosificadorDifuso;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import FuncionPertenencia.FuncionSingleton;
import FuncionPertenencia.FuncionTrapezoide;
import FuncionPertenencia.FuncionTriangulo;
import FuncionPertenencia.MembershipFunction;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author RALCALA
 */
public class EvaluadorDifuso {

    private ArrayList<VariableLinguistica> entradas;
    private ArrayList<VariableLinguistica> salidas;
    
    public EvaluadorDifuso(){
        try {
            inicializarVariables();
        } catch (JDOMException ex) {
            System.out.println("ERROR EvaluadorDifuso: Ocurrio un error al inicializar las variables");
        }
    }
    
    private void inicializarVariables() throws JDOMException {
        entradas = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("config\\variables.def");
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);

            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'tables'
            Element campoListaEntrada = rootNode.getChild("listaEntrada");
            List campoEntrada = campoListaEntrada.getChildren("entrada");
            VariableLinguistica vl;
            for (Object element: campoEntrada) {
                Element campo = (Element) element;
                int min=0, max=0;
                Element campoListaTerminos = campo.getChild("listaTermino");
                
                
                vl = new VariableLinguistica();
                vl.setNombre(campo.getChildTextTrim("nombreEntrada"));
                vl.setMin(min=Integer.parseInt(campo.getChildTextTrim("min")));
                vl.setMax(max=Integer.parseInt(campo.getChildTextTrim("max")));
                
                MembershipFunction mf = null;
                for(Object term: campoListaTerminos.getChildren("termino")){
                    
                    Element campoTermino = (Element) term;
                    String tipoTermino = campoTermino.getChildTextTrim("tipo");
                    if(tipoTermino.equals("triangulo")){
                        mf = new FuncionTriangulo();
                        ((FuncionTriangulo) mf ).setA(Double.parseDouble(campoTermino.getChildTextTrim("paramA")));
                        ((FuncionTriangulo) mf ).setB(Double.parseDouble(campoTermino.getChildTextTrim("paramB")));
                        ((FuncionTriangulo) mf ).setC(Double.parseDouble(campoTermino.getChildTextTrim("paramC")));
                    }else if(tipoTermino.equals("trapezoide")){
                        mf = new FuncionTrapezoide();
                        ((FuncionTrapezoide) mf ).setA(Double.parseDouble(campoTermino.getChildTextTrim("paramA")));
                        ((FuncionTrapezoide) mf ).setB(Double.parseDouble(campoTermino.getChildTextTrim("paramB")));
                        ((FuncionTrapezoide) mf ).setC(Double.parseDouble(campoTermino.getChildTextTrim("paramC")));
                        ((FuncionTrapezoide) mf ).setD(Double.parseDouble(campoTermino.getChildTextTrim("paramC")));
                    }
                    
                    if(mf!=null){
                        mf.setMin(min);
                        mf.setMax(max);
                    }
                    
                    mf.setNombre(campoTermino.getChildTextTrim("nombreTermino"));
                    vl.getTerminosLinguisticos().add(mf);
                    
                }
                entradas.add(vl);
                
            }
            
            vl = null;
            
            Element campoListaSalida = rootNode.getChild("listaSalida");
            List campoSalida = campoListaSalida.getChildren("salida");
            salidas = new ArrayList<>();

            for (Object element: campoSalida) {
                Element campo = (Element) element;
                int min=0, max=0;
                Element campoListaTerminos = campo.getChild("listaTermino");
                
                
                vl = new VariableLinguistica();
                vl.setNombre(campo.getChildTextTrim("nombreSalida"));
                //vl.setMin(min=Integer.parseInt(campo.getChildTextTrim("min")));
                //vl.setMax(max=Integer.parseInt(campo.getChildTextTrim("max")));
                
                MembershipFunction mf = null;
                for(Object term: campoListaTerminos.getChildren("termino")){
                    
                    Element campoTermino = (Element) term;
                    String tipoTermino = campoTermino.getChildTextTrim("tipo");
                    if(tipoTermino.equals("triangulo")){
                        mf = new FuncionTriangulo();
                        ((FuncionTriangulo) mf ).setA(Double.parseDouble(campoTermino.getChildTextTrim("paramA")));
                        ((FuncionTriangulo) mf ).setB(Double.parseDouble(campoTermino.getChildTextTrim("paramB")));
                        ((FuncionTriangulo) mf ).setC(Double.parseDouble(campoTermino.getChildTextTrim("paramC")));
                    }else if(tipoTermino.equals("trapezoide")){
                        mf = new FuncionTrapezoide();
                        ((FuncionTrapezoide) mf ).setA(Double.parseDouble(campoTermino.getChildTextTrim("paramA")));
                        ((FuncionTrapezoide) mf ).setB(Double.parseDouble(campoTermino.getChildTextTrim("paramB")));
                        ((FuncionTrapezoide) mf ).setC(Double.parseDouble(campoTermino.getChildTextTrim("paramC")));
                        ((FuncionTrapezoide) mf ).setD(Double.parseDouble(campoTermino.getChildTextTrim("paramC")));
                    }else if(tipoTermino.equals("singleton")){
                        mf = new FuncionSingleton();
                        ((FuncionSingleton) mf ).setValor(Double.parseDouble(campoTermino.getChildTextTrim("valor")));
                    }
                    
                    if(mf!=null && !tipoTermino.equals("singleton")){
                        mf.setMin(min);
                        mf.setMax(max);
                    }
                    
                    mf.setNombre(campoTermino.getChildTextTrim("nombreTermino"));
                    vl.getTerminosLinguisticos().add(mf);
                    
                }
                salidas.add(vl);
                
            }
            
            
            System.out.println("");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("");
            
        }
    }
    
    public Map<String,Double> borrosificarEntradas(Map<String,Double> listaEntradas){
        Map<String,Double> borrosificados = new HashMap<String,Double>();
        try{
            for (VariableLinguistica vl: entradas) {
                for (MembershipFunction mf:vl.getTerminosLinguisticos()) {
                    String nombreVar = vl.getNombre();
                    String nombreLlave = nombreVar + "=="+mf.getNombre();
                    nombreLlave = nombreLlave.replaceAll(" ", "");
                    Double valBorr = mf.compute(listaEntradas.get(vl.getNombre()));
                    borrosificados.put(nombreLlave, valBorr);
                }

            }
        }catch(Exception e){
            System.out.println("ERROR EvaluadorDifuso.borrosificarEntradas");
            e.printStackTrace();
            return null;
        }
        return borrosificados;
    }

    public static void main(String[] args) {
        EvaluadorDifuso ed = new EvaluadorDifuso();
        try {
            ed.inicializarVariables();
        } catch (JDOMException ex) {
            Logger.getLogger(EvaluadorDifuso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        double a = ((FuncionTriangulo)ed.entradas.get(0).getTerminosLinguisticos().get(0)).compute(1);
        double b =((FuncionTriangulo)ed.entradas.get(0).getTerminosLinguisticos().get(0)).compute(0);
        double c =((FuncionTriangulo)ed.entradas.get(0).getTerminosLinguisticos().get(0)).compute(0.5);
        double d =((FuncionTriangulo)ed.entradas.get(0).getTerminosLinguisticos().get(0)).compute(1.5);
        System.out.println("");
    }
    
}
