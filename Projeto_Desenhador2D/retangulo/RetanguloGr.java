package retangulo;

import reta.FiguraRetas;
import java.awt.Color;
import java.awt.Graphics;
import ponto.Ponto;

/**
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class RetanguloGr
{
    private Ponto p1, p2;
    private int esp;
    private Color corAtual;

    public RetanguloGr(int x1, int y1, int x2, int y2, String nome, int esp, Color cor)
    {
        setP1(x1, y1);
        setP2(x2, y2);
        setEsp(esp);
        setCor(cor);
    }

    private void setP1(int x, int y){
        Ponto p1 = new Ponto((double)x, (double)y);
        this.p1 = p1;
    }

    private void setP2(int x, int y){
        Ponto p2 = new Ponto((double)x, (double)y);
        this.p2 = p2;
    }

    private void setEsp(int espe){
        this.esp = espe;
    }

    private void setCor(Color cor){
        this.corAtual = cor;
    }

    public Ponto getP1(){
        return this.p1;
    }

    public Ponto getP2(){
        return this.p2;
    }

    public int getEsp(){
        return this.esp;
    }

    public Color getCor(){
        return this.corAtual;
    }

    public void desenharRetangulo(Graphics g){
        FiguraRetas.desenharReta(g, (int)getP1().getX(), (int)getP1().getY(), (int)getP2().getX(), (int)getP1().getY(), "", getEsp(), getCor()); 
        FiguraRetas.desenharReta(g, (int)getP1().getX(), (int)getP1().getY(), (int)getP1().getX(), (int)getP2().getY(), "", getEsp(), getCor());
        FiguraRetas.desenharReta(g,(int)getP1().getX(), (int)getP2().getY(), (int)getP2().getX(), (int)getP2().getY(), "", getEsp(), getCor());
        FiguraRetas.desenharReta(g, (int)getP2().getX(), (int)getP1().getY(), (int)getP2().getX(), (int)getP2().getY(), "", getEsp(), getCor());
    }
}
