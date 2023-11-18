package reta;
import ponto.Ponto;

/**
 * Reta matematica.
 *
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class Reta {
    public Ponto p1, p2;

    public Reta(int x1, int y1, int x2, int y2) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
    }

    public Reta(double x1, double y1, double x2, double y2) {
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
    }

    public Reta(Ponto p1, Ponto p2) {
        setP1(p1);
        setP2(p2);
    }

    public Reta (Reta r){
        setP1(r.getP1());
        setP2(r.getP2());
    }

    public void setP1(Ponto p){
        this.p1 = p;
    }

    public void setP2(Ponto p){
        this.p2 = p;
    }

    public Ponto getP1(){
        return this.p1;
    }

    public Ponto getP2(){
        return this.p2;
    }

    public double calcularM(){
        double m = (getP2().getY() - getP1().getY())/(getP2().getX() - getP1().getX());
        return m;
    }

    public double calcularB(){
        double b = getP1().getY() - calcularM()*getP1().getX();
        return b;
    }

    public double distanPonto(Ponto p1, Ponto p2){
        double a,b,x;

        a = getP1().getY() - getP2().getY();
        b = getP1().getX() - getP2().getX();

        x = Math.pow(a,2) + Math.pow(b,2);
        x = Math.sqrt(x);

        return x;
    }

    public String toString(){
        String s = "P1: " + getP1().toString() + " P2: " + getP2().toString();
        s = s + "\nEq. da reta: y = " + calcularM() + "*x + " + calcularB();
        return s;
    }
}
