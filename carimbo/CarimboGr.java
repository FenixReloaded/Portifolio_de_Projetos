package carimbo;

import java.awt.Color;
import java.awt.Graphics;
import ponto.Ponto;

import ponto.FiguraPontos;
import reta.FiguraRetas;
import Circulo.Circulo;
import Circulo.CirculoGr;
import triangulo.TrianguloGr;
import retangulo.RetanguloGr;
import ponto.PontoGr;
import reta.RetaGr;

/**
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class CarimboGr //mandala
{
    private Ponto p1, p2;
    private int esp;
    private Color corAtual1, corAtual2;

    public CarimboGr(int x1, int y1, int x2, int y2, String nome, int esp, Color cor1, Color cor2){
        setP1(x1, y1);
        setP2(x2, y2);
        setEsp(esp);
        setCor1(cor1);
        setCor2(cor2);
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

    private void setCor1(Color cor){
        this.corAtual1 = cor;
    }
    
    private void setCor2(Color cor2){
        this.corAtual2 = cor2;
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

    public Color getCor1(){
        return this.corAtual1;
    }
    
    public Color getCor2(){
        return this.corAtual2;
    }
 
    public static void desenharCarimbo(Graphics g, int x1, int y1, int x2, int y2, int esp, Color cor1, Color cor2){
        Circulo cir = new Circulo(x1,y1,x2,y2);
        PontoGr P3, P4, P5, P6, P7;
        double r = cir.calculaRaio();

        CirculoGr circ = new CirculoGr(x1, y1, x2, y2, cor1, esp);
        circ.desenharCirculo(g);

        CirculoGr circ2 = new CirculoGr(x2,y1,x2 + (int)r, y2, cor1, esp);
        circ2.desenharCirculo(g);

        double a = 0, x4  = 0, x5  = 0, x6  = 0, x7  = 0, x8 = 0, x9 = 0;
        double b = 0, y4 = 0, y5 = 0, y6 = 0, y7 = 0, y8 = 0, y9 = 0;

        for(int teta = 0; teta < 360; teta++){
            if(teta == 60){
                a = (Math.cos(Math.toRadians(teta)) * r) + x1;
                b = (Math.sin(Math.toRadians(teta)) * r) + y1;
            }
            if(teta == 120){
                x4 = Math.cos(Math.toRadians(teta)) * r + x1;
                y4 = Math.sin(Math.toRadians(teta)) * r + y1;
            }
            if(teta == 180){
                x5 = Math.cos(Math.toRadians(teta)) * r + x1;
                y5 = Math.sin(Math.toRadians(teta)) * r + y1;
            }
            if(teta == 240){
                x6 = Math.cos(Math.toRadians(teta)) * r + x1;
                y6 = Math.sin(Math.toRadians(teta)) * r + y1;
            }
            if(teta == 300){
                x7 = Math.cos(Math.toRadians(teta)) * r + x1;
                y7 = Math.sin(Math.toRadians(teta)) * r + y1;
            }  
        }

        CirculoGr circ3 = new CirculoGr((int)a, (int)b, (int)a + (int)r, y2 + (int)r,cor1, esp);
        circ3.desenharCirculo(g);

        CirculoGr circ4 = new CirculoGr((int)x4, (int)b, (int)x4 + (int)r, y2 + (int)r , cor1, esp);
        circ4.desenharCirculo(g);

        CirculoGr circ5 = new CirculoGr((int)x5, (int)y5, (int)x5 + (int)r, y2 , cor1, esp);
        circ5.desenharCirculo(g);

        CirculoGr circ6 = new CirculoGr((int)x6, (int)y6, (int)x6 + (int)r, y2 - (int)r , cor1, esp);
        circ6.desenharCirculo(g);

        CirculoGr circ7 = new CirculoGr((int)x7, (int)y7, (int)x7 + (int)r, y2 - (int)r , cor1, esp);
        circ7.desenharCirculo(g);

        TrianguloGr trian1 = new TrianguloGr(x1, y1, x2, y2, (int)a, (int)b, (""), esp, cor2);
        trian1.desenharTriangulo(g);

        TrianguloGr trian2 = new TrianguloGr(x1, y1, (int)x5, (int)y5, (int)x4, (int)y4, (""), esp, cor2);
        trian2.desenharTriangulo(g);

        TrianguloGr trian3 = new TrianguloGr(x1, y1, (int)x5, (int)y5, (int)x6, (int)y6, (""), esp, cor2);
        trian3.desenharTriangulo(g);

        TrianguloGr trian4 = new TrianguloGr(x1, y1, x2, y2, (int)x7, (int)y7, (""), esp,cor1);
        trian4.desenharTriangulo(g);

        TrianguloGr trian5 = new TrianguloGr(x1, y1, (int)x6, (int)y6, (int)x7, (int)y7, (""), esp, cor2);
        trian5.desenharTriangulo(g);

        TrianguloGr trian6 = new TrianguloGr(x1, y1, (int)a, (int)b, (int)x4, (int)y4, (""), esp, cor2);
        trian6.desenharTriangulo(g);

        RetanguloGr retangulo = new RetanguloGr ((int)x6 - (int)r , (int)y6, (int)a + (int)r, (int)b, "", esp, cor2);
        retangulo.desenharRetangulo(g);

        TrianguloGr trian7 = new TrianguloGr((int)x4 - (int)r , (int)y4, (int)x6 - (int)r , (int)y6, x1, y1, (""), esp, cor2);
        trian7.desenharTriangulo(g);

        TrianguloGr trian8 = new TrianguloGr((int)x4 - (int)r , (int)y4, (int)x6 - (int)r , (int)y6, (int)x5, (int)y5, (""), esp, cor2);
        trian8.desenharTriangulo(g);

        TrianguloGr trian9 = new TrianguloGr((int)x7 + (int)r , (int)y7, (int)a + (int)r, (int)b, x1, y1, (""), esp, cor2);
        trian9.desenharTriangulo(g);

        TrianguloGr trian10 = new TrianguloGr((int)x7 + (int)r , (int)y7, (int)a + (int)r , (int)b, x2, y2, (""), esp, cor2);
        trian10.desenharTriangulo(g);

        for(int teta = 250; teta < 360; teta++){
            if(teta == 300){
                x8 = (Math.cos(Math.toRadians(teta)) * r) + x6;
                y8 = (Math.sin(Math.toRadians(teta)) * r) + y6;
            }
        }

        TrianguloGr trian11 = new TrianguloGr((int)x6 - (int)r , (int)y6, x1, y1, (int)x8, (int)y8, (""), esp, cor2);
        trian11.desenharTriangulo(g);

        TrianguloGr trian12 = new TrianguloGr((int)x7 + (int)r , (int)y7, x1, y1, (int)x8, (int)y8, (""), esp, cor2);
        trian12.desenharTriangulo(g);

        TrianguloGr trian13 = new TrianguloGr((int)x7 , (int)y7,(int)x6 , (int)y6, (int)x8, (int)y8, (""), esp, cor2);
        trian13.desenharTriangulo(g);

        for(int teta = 10; teta < 100; teta++){
            if(teta == 60){
                x9 = (Math.cos(Math.toRadians(teta)) * r) + x4;
                y9 = (Math.sin(Math.toRadians(teta)) * r) + y4;
            }
        }

        TrianguloGr trian14 = new TrianguloGr((int)x4 - (int)r , (int)y4, x1, y1, (int)x9, (int)y9, (""), esp, cor2);
        trian14.desenharTriangulo(g);

        TrianguloGr trian15 = new TrianguloGr((int)a + (int)r , (int)b, x1, y1, (int)x9, (int)y9, (""), esp, cor2);
        trian15.desenharTriangulo(g);

        TrianguloGr trian16 = new TrianguloGr((int)a, (int)b, (int)x4, (int)y4, (int)x9, (int)y9, (""), esp, cor2);
        trian16.desenharTriangulo(g);
    }
}
