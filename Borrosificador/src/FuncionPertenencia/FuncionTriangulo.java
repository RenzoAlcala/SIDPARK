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
public class FuncionTriangulo extends MembershipFunction {

    
    double a;
    double b;
    double c;

    public FuncionTriangulo(){
    
    }
    
    public FuncionTriangulo(double min, double max, double step, double param[]) {
        super.min = min;
        super.max = max;
        super.step = step; 
        this.a = param[0];
        this.b = param[1];
        this.c = param[2];
    }

    double param(int _i) {
        switch (_i) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            default:
                return 0;
        }
    }

    @Override
    public double compute(double x) {
        return (a < x && x <= b ? (x - a) / (b - a) : (b < x && x < c ? (c - x) / (c - b) : 0));
    }

    double isGreaterOrEqual(double x) {
        return (x < a ? 0 : (x > b ? 1 : (x - a) / (b - a)));
    }

    double isSmallerOrEqual(double x) {
        return (x < b ? 1 : (x > c ? 0 : (c - x) / (c - b)));
    }

    double center() {
        return b;
    }

    double basis() {
        return (c - a);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
    
    
    
    public static void main(String[] args) {
        /*MF_xfl_triangle tri = new MF_xfl_triangle(0,10,0.1d,new double[]{0,5,10});
        System.out.println(tri.isEqualD(2.5d));
        System.out.println(tri.isGreaterOrEqualD(8.5d));
        System.out.println(tri.isGreaterOrEqualD(7.5d));
        System.out.println(tri.isGreaterOrEqualD(6.5d));
                System.out.println(tri.isGreaterOrEqualD(5.5d));
                System.out.println(tri.isGreaterOrEqualD(5d));
                System.out.println(tri.isGreaterOrEqualD(4d));
        //(x < a ? 0 : (x > b ? 1 : (x - a) / (b - a)))*/
        //(a < x && x <= b ? (x - a) / (b - a) : (b < x && x < c ? (c - x) / (c - b) : 0))
    }
}
