package Circulo;

import ponto.Ponto;
import reta.Reta;

/**
 * circulo matematica.
 *
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class Circulo
{
    Ponto p1;
    Ponto p2;
    double raio;

    public Circulo(int x1, int y1, int x2, int y2){
        setP1(new Ponto(x1, y1)); //ponto cental
        setP2(new Ponto(x2, y2));
    }

    private void setP1(Ponto p1){
        this.p1 = p1;
    }

    private void setP2(Ponto p2){
        this.p2= p2;
    }

    public Ponto getP1(){
        return this.p1;
    }

    public Ponto getP2(){
        return this.p2;
    }

    public double calculaRaio(){
        Reta reta = new Reta(getP1(), getP2());
        raio = reta.distanPonto(getP1(), getP2());
        return raio;
    }

    public String toString(){
        String s;
        double r = calculaRaio();

        r = Math.pow(r, 2);
        s = "Equacao da reta = (x - " + getP1().getX() + ")Â² + " + "(y - " + getP1().getY() + ")Â² = " + r;

        return s;
    }
}
