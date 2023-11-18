package reta;
import java.awt.Color;
import java.awt.Graphics;
import ponto.PontoGr;

/**
 * Implementacao da classe reta grafica.
 *
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class RetaGr extends Reta{
    Color corReta = Color.BLACK; 
    String nomeReta = ""; 
    Color corNomeReta  = Color.BLACK;
    int espReta = 1; 

    public RetaGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){
        super (x1, y1, x2, y2);
        setCorReta(cor);
        setNomeReta(nome);
        setEspReta(esp);
    }    

    public RetaGr(int x1, int y1, int x2, int y2, Color cor){
        super (x1, y1, x2, y2);
        setCorReta(cor);
        setNomeReta("");
    }   

    public RetaGr(int x1, int y1, int x2, int y2, Color cor, int esp){
        super (x1, y1, x2, y2);
        setCorReta(cor);
        setNomeReta("");
        setEspReta(esp);
    }   

    public RetaGr(int x1, int y1, int x2, int y2){
        super (x1, y1, x2, y2);
        setCorReta(Color.black);
        setNomeReta("");
    }   

    public RetaGr(PontoGr p1, PontoGr p2){
        super(p1, p2);
        setCorReta(Color.black);
        setNomeReta("");
    }    

    public RetaGr(PontoGr p1, PontoGr p2, Color cor){
        super(p1, p2);
        setCorReta(cor);
        setNomeReta("");
    }    

    public RetaGr(PontoGr p1, PontoGr p2, Color cor, String str){
        super(p1, p2);
        setCorReta(cor);
        setNomeReta(str);
    }    

    public void setCorReta(Color cor) {
        this.corReta = cor;
    }

    public void setNomeReta(String str) {
        this.nomeReta = str;
    }

    public void setEspReta(int esp) {
        this.espReta = esp;
    }

    public int getEspReta() {
        return(this.espReta);
    }

    public Color getCorReta() {
        return corReta;
    }

    public String getNomeReta() {
        return nomeReta;
    }

    public Color getCorNomeReta() {
        return corNomeReta;
    }

    public void setCorNomeReta(Color corNomeReta) {
        this.corNomeReta = corNomeReta;
    }

        /**
     * Desenha reta utilizando o algoritmo de MidPoint (Bresenham)
     * @param g
     */
    public void desenharReta(Graphics g){

        // Pontos que definem a reta
        int x1 = (int)getP1().getX(), x2 = (int)getP2().getX();
        int y1 = (int)getP1().getY(), y2 = (int)getP2().getY();

        // Calculo de deltax e deltay
        int deltax = x2-x1;
        int deltay = y2-y1;

        int decisao; // Variavel para decisao
        int incx, incy; // Variaveis para incremento
        int x,y, i;

        // Define a direcao das retas e incrementos
        if (deltax < 0) deltax = -deltax;
        if (deltay < 0) deltay = -deltay;
        incx = 1;
        if (x2 < x1) incx = -1;
        incy = 1;
        if (y2 < y1) incy = -1;
        
        // Ponto inicial
        x = x1; y = y1;
        
        // Instancia do ponto para acender os pntos da reta
        PontoGr ponto = new PontoGr(); 
        
        // desenha nome da reta
        g.setColor(getCorNomeReta());
        g.drawString(getNomeReta(), (int)getP1().getX() + getEspReta(), (int)getP1().getY());

        // define a cor e espessura da reta
        ponto.setCorPto(getCorReta());
        ponto.setDiametro(getEspReta());

        // Desenhar ponto inicial da reta
        ponto.setX(x);
        ponto.setY(y);
        ponto.desenharPonto(g);
        
        if (deltax > deltay) { // se deltax > deltay, varre pelo x
            // Inicio
            decisao = 2 * deltay-deltax;
            
            for (i=0; i<deltax; i++) {
                if (decisao >= 0) { // Pixel A
                    y += incy;
                    decisao += 2*(deltay-deltax);
                } else {// Pixel B
                    decisao += 2*deltay;
                }
                x += incx;
                
                // Desenhar ponto
                ponto.setX(x);
                ponto.setY(y);
                ponto.desenharPonto(g);
            }
        } else { // se deltax <= deltay, varre pelo y
            // Inicio
            decisao = 2*deltax-deltay;
                        
            for (i=0; i<deltay; i++) {
                if (decisao >= 0) { // Pixel A
                    x += incx;
                    decisao += 2*(deltax-deltay);
                } else {// Pixel B
                    decisao += 2*deltax;
                }
                y += incy;
                
                // Desenhar ponto
                ponto.setX(x);
                ponto.setY(y);
                ponto.desenharPonto(g);
            }
        }
    }
}

