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
public class FuncionTrapezoide extends MembershipFunction {

    
    double a;
    double b;
    double c;
    double d;

    public FuncionTrapezoide(){
    
    }
    
    public FuncionTrapezoide(double min, double max, double step, double param[]) {
        super.min = min;
        super.max = max;
        super.step = step;
        this.a = param[0];
        this.b = param[1];
        this.c = param[2];
        this.d = param[3];
    }

    double param(int _i) {
        switch (_i) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            case 3:
                return d;
            default:
                return 0;
        }
    }

    @Override
    public double compute(double x) {
        return (x < a || x > d ? 0 : (x < b ? (x - a) / (b - a) : (x < c ? 1 : (d - x) / (d - c))));
    }

    double isGreaterOrEqual(double x) {
        return (x < a ? 0 : (x > b ? 1 : (x - a) / (b - a)));
    }

    double isSmallerOrEqual(double x) {
        return (x < c ? 1 : (x > d ? 0 : (d - x) / (d - c)));
    }

    double center() {
        return (b + c) / 2;
    }

    double basis() {
        return (d - a);
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

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
    
    
}
