package triangulo;

import reta.FiguraRetas;
import java.awt.Color;
import java.awt.Graphics;
import ponto.Ponto;

/**
 * Write a description of class TrianguloGR here.
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class TrianguloGr
{
    private Ponto P1, P2, P3;
    private int esp;
    private Color corAtual;

    public TrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, String nome, int esp, Color cor){
        setP1(x1,y1);
        setP2(x2,y2);
        setP3(x3,y3);
        setEsp(esp);
        setCor(cor);
    }

    private void setP1(int x, int y){
        Ponto P1 = new Ponto((double)x,(double)y);
        this.P1 = P1;
    }

    private void setP2(int x, int y){
        Ponto P2 = new Ponto((double)x,(double)y);
        this.P2 = P2;
    }

    private void setP3(int x, int y){
        Ponto P3 = new Ponto((double)x,(double)y);
        this.P3 = P3;
    }

    private void setEsp(int esp){
        this.esp = esp;
    }

    private void setCor(Color cor){
        this.corAtual = cor;
    }

    public Ponto getP1(){
        return this.P1;
    }

    public Ponto getP2(){
        return this.P2;
    }

    public Ponto getP3(){
        return this.P3;
    }

    public int getEsp(){
        return this.esp;
    }

    public Color getCor(){
        return this.corAtual;
    }

    public void desenharTriangulo(Graphics g){
        FiguraRetas.desenharReta(g, (int)getP1().getX(), (int)getP1().getY(), (int)getP2().getX(), (int)getP2().getY(), "", getEsp(), getCor()); 
        FiguraRetas.desenharReta(g, (int)getP1().getX(), (int)getP1().getY(), (int)getP3().getX(), (int)getP3().getY(), "", getEsp(), getCor());
        FiguraRetas.desenharReta(g,(int)getP2().getX(), (int)getP2().getY(), (int)getP3().getX(), (int)getP3().getY(), "", getEsp(), getCor());
    }
}
