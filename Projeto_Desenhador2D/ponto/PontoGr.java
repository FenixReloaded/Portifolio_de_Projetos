package ponto;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class PontoGr extends Ponto {
    Color corPto = Color.BLACK; 
    String nomePto = ""; 
    Color corNomePto  = Color.BLACK;  
    int diametro = 1; 

    public PontoGr(int x, int y){
        super((double)x, (double)y);
        setCorPto(Color.black);     
        setCorNomePto(Color.black);     
        setNomePto("");     
    }

    public PontoGr(int x, int y, Color cor){
        super((double)x, (double)y);
        setCorPto(cor);     
        setCorNomePto(Color.black);     
        setNomePto("");     
    }

    public PontoGr(int x, int y, Color corPonto, int diametro){
        this(x, y, corPonto);
        setDiametro(diametro);
    }

    public PontoGr(int x, int y, Color corPonto, String nomePonto, int diametro){
        this(x, y, corPonto, diametro);
        setNomePto(nomePonto);
    }

    public  PontoGr(int x, int y, Color cor, String str){
        super((double)x, (double)y);
        setCorPto(cor);     
        setCorNomePto(Color.black);     
        setNomePto(str);     
    }

    public PontoGr(PontoGr p2d, Color cor){
        super(p2d);     
        setCorPto(cor);     
        setCorNomePto(Color.black);     
        setNomePto("");     
    }

    public PontoGr(){
        super();     
        setCorPto(Color.black);     
        setCorNomePto(Color.black);     
        setNomePto("");     
    }

    public Color getCorPto() {
        return corPto;
    }

    public void setCorPto(Color corPto) {
        this.corPto = corPto;
    }

    public String getNomePto() {
        return nomePto;
    }

    public void setNomePto(String nomePto) {
        this.nomePto = nomePto;
    }

    public Color getCorNomePto() {
        return corNomePto;
    }

    public void setCorNomePto(Color corNomePto) {
        this.corNomePto = corNomePto;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public void desenharPonto(Graphics g){
        g.setColor(getCorPto());
        g.fillOval((int)getX() -(getDiametro()/2), (int)getY() - (getDiametro()/2), getDiametro(), getDiametro());

        g.setColor(getCorNomePto());
        g.drawString(getNomePto(), (int)getX() + getDiametro(), (int)getY());
    }
}
