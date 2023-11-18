package reta;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Desenha figuras com retas.
 * 
 * Caio Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

public class FiguraRetas {
    public static void desenharReta(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        RetaGr r = new RetaGr(x1, y1, x2, y2, cor, nome, esp);
        r.desenharReta(g );
    }

    public static void desenharRetas(Graphics g, int qtde, int esp){

        for(int i=0; i < qtde; i++) {
            int x1 = (int) (Math.random() * 801);
            int y1 = (int) (Math.random() * 801);
            int x2 = (int) (Math.random() * 801);
            int y2 = (int) (Math.random() * 801);

            Color cor = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));
            RetaGr r = new RetaGr(x1, y1, x2, y2, cor, "", esp);
            r.desenharReta(g);
        }
    }
}
