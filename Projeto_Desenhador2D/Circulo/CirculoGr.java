package Circulo;

import java.awt.Color;
import java.awt.Graphics;
import ponto.PontoGr;

/**
 * circulo parte grafica
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class CirculoGr extends Circulo
{
    int espReta;
    Color cor;
    
    public CirculoGr(int x1, int y1, int x2, int y2, Color cor, int esp){
        super(x1, y1, x2, y2);
        setCor(cor);
        setEsp(esp);
    }
    
    public void setEsp(int esp) {
        this.espReta = esp;
    }

    public int getEsp(){
        return(this.espReta);
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    public Color getCor() {
        return cor;
    }
    
    public void desenharCirculo(Graphics g){
        PontoGr ponto;
        double raio = calculaRaio();
        double x,y,x1,y1;
        
        x1 = getP1().getX();
        y1 = getP1().getY();
    
        for(float theta = 0; theta < 360; theta += 0.1){
            x = Math.cos(Math.toRadians(theta)) * raio;
            y = Math.sin(Math.toRadians(theta)) * raio;

            ponto = new PontoGr((int)x + (int)x1, (int)y + (int)y1, getCor(),getEsp());
            ponto.desenharPonto(g);
        }
    }
}
