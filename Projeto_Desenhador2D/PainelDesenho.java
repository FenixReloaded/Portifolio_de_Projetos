import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ponto.FiguraPontos;
import reta.FiguraRetas;
import Circulo.Circulo;
import Circulo.CirculoGr;
import triangulo.TrianguloGr;
import retangulo.RetanguloGr;
import ponto.PontoGr;
import reta.RetaGr;

import carimbo.CarimboGr;

import pilha.Pilha;
import pilha.ListaDuplamenteLigadaCircular;

import Armazenador.VetDin;
import Armazenador.IArmazenador;
import Armazenador.ListaArray;
import Armazenador.ListaLigadaSimples;
import Armazenador.No;

import javax.swing.JOptionPane;
import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Cria desenhos de acordo com o tipo e eventos do mouse
 * 
 * Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 */

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
    JLabel mensag; 
    TipoPrimitivo tipo; 
    Color cor; 
    int esp; 
    No fig;
    String fig1 = "1"; //ponto
    String fig2 = "2"; //reta
    String fig3 = "3"; //triangulo
    String fig4 = "4"; //retangulo
    String fig5 = "5"; //circulo
    String fig6 = "6"; //carimbo
    int x, y, A = 0;
    int x1, y1, x2, y2, x3, y3;

    int xp1, yp1;
    int xreta1, yreta1, xreta2, yreta2;
    int xretang1, yretang1, xretang2, yretang2;
    int xt1, yt1, xt2, yt2, xt3, yt3;
    int xc1, yc1, xc2, yc2;
    int xpontoescala, ypontoescala;
    int xpontorotacao, ypontorotacao;

    boolean first = true;
    boolean second = true;
    String aux=null;

    private Color corAtualCircunferencia = Color.orange; // Cor padrão do CarimboFlor
    private Color corAtualTriangulo = Color.RED; // Cor padrão para triângulos no CarimboFlor
    private Color corAtualRetangulo = Color.GREEN; // Cor padrão para retângulos no CarimboFlor

    ListaLigadaSimples salvarPonto = new ListaLigadaSimples();// salva Ponto
    ListaLigadaSimples salvarReta = new ListaLigadaSimples(); // salva Reta
    ListaLigadaSimples salvarCirculo = new ListaLigadaSimples(); // salva Circulo
    ListaLigadaSimples salvarTriangulo = new ListaLigadaSimples(); // salva triangulo
    ListaLigadaSimples salvarRetangulo = new ListaLigadaSimples(); // salva Retangulo

    ListaLigadaSimples salvarCarimbo = new ListaLigadaSimples(); // salva CarimboFlor

    ListaLigadaSimples vazio = new ListaLigadaSimples();

    Pilha pilhaComandos = new Pilha(1000); //pilha tamanho 100

    Pilha pilhaCoordCarimbo = new Pilha(1000);

    //Pilha pilhaEspessuras = new Pilha(1000);

    ListaDuplamenteLigadaCircular filaAux = new ListaDuplamenteLigadaCircular();

    ListaDuplamenteLigadaCircular filaAuxEsp = new ListaDuplamenteLigadaCircular();

    ListaLigadaSimples salvarPontoRemocao = new ListaLigadaSimples();
    String figrem1 = "7"; //ponto removido

    ListaLigadaSimples salvarRetaRemocao = new ListaLigadaSimples();
    String figrem2 = "8"; //reta removida

    ListaLigadaSimples salvarTrianguloRemocao = new ListaLigadaSimples();
    String figrem3 = "9"; //triangulo removido

    ListaLigadaSimples salvarRetanguloRemocao = new ListaLigadaSimples();
    String figrem4 = "10"; //retangulo removido

    ListaLigadaSimples salvarCirculoRemocao = new ListaLigadaSimples();
    String figrem5 = "11"; //circulo removido

    ListaLigadaSimples salvarCarimboRemocao = new ListaLigadaSimples();
    String figrem6 = "12"; //carimbo removido

    //

    ListaLigadaSimples salvarTrianguloPreEscalonado = new ListaLigadaSimples();
    String figesc1 = "13"; //triangulo escalonado

    ListaLigadaSimples salvarTrianguloPreRotacionado = new ListaLigadaSimples();
    String figesc2 = "14"; //triangulo rotacionado

    /**
     * Constroi o painel de desenho
     *
     * @param mensag mensagem a ser escrita no rodape do painel
     * @param tipo tipo atual do primitivo
     * @param cor cor atual do primitivo
     * @param esp espessura atual do primitivo
     */
    public PainelDesenho(JLabel mensag, TipoPrimitivo tipo, Color cor, int esp){
        setTipo(tipo);
        setEsp(esp);
        setmensag(mensag);
        setcor(cor);
        this.addMouseMotionListener(this);
        this.addMouseListener(this); 
    }

    public void setTipo(TipoPrimitivo tipo){
        this.tipo = tipo;
    }

    public TipoPrimitivo getTipo(){
        return this.tipo;
    }

    public void setEsp(int esp){
        this.esp = esp;
    }

    public int getEsp(){
        return this.esp;
    }

    public void setcor(Color cor){
        this.cor = cor;
    }

    public Color getcor(){
        return this.cor;
    }

    public void setmensag(JLabel mensag){
        this.mensag = mensag;
    }

    public JLabel getmensag(){
        return this.mensag;
    }

    public void paintComponent(Graphics g) {   
        desenharPrimitivos(g);
    }

    public void setcorCircunferencia(Color cor) {
        this.corAtualCircunferencia = cor;
    }

    public void getcorCircunferencia(Color cor){
        this.corAtualCircunferencia = cor;
    }

    public void setcorTriangulo(Color cor) {
        this.corAtualTriangulo = cor;
    }

    public void getcorTriangulo(Color cor){
        this.corAtualTriangulo = cor;
    }

    public void setcorRetangulo(Color cor) {
        this.corAtualRetangulo = cor;
    }

    public void getcorRetangulo(Color cor){
        this.corAtualRetangulo = cor;
    }

    public void mousePressed(MouseEvent e) { 
        Graphics g = getGraphics();  
        if (tipo == TipoPrimitivo.PONTO){
            xp1 = e.getX();
            yp1 = e.getY();
            paint(g);
        } 
        else if (tipo == TipoPrimitivo.RETA){
            if (first == true) {
                xreta1 = (int)e.getX();
                yreta1 = (int)e.getY();
                first = false;
            } 
            else {
                xreta2 = (int)e.getX();
                yreta2 = (int)e.getY();
                first = true;
                paint(g);
            } 
        }
        else if(tipo == TipoPrimitivo.RETANGULO){
            if (first == true) {
                xretang1 = (int)e.getX();
                yretang1 = (int)e.getY();
                first = false;
            } else {
                xretang2 = (int)e.getX();
                yretang2 = (int)e.getY();
                first = true;
                paint(g);
            } 
        }
        else if(tipo == TipoPrimitivo.TRIANGULO){
            if (first == true) {
                xt1 = (int)e.getX();
                yt1 = (int)e.getY();
                first = false;
            } else if(second == true) {
                xt2 = (int)e.getX();
                yt2 = (int)e.getY();
                second = false;
            } else{
                xt3 = (int)e.getX();
                yt3 = (int)e.getY();
                second = true;
                first = true;
                paint(g);
            }        
        }else if(tipo == TipoPrimitivo.CIRCULO){
            if(first == true){
                xc1 = (int)e.getX();
                yc1 = (int)e.getY();
                first = false;
            } else {
                xc2 = (int)e.getX();
                yc2 = (int)e.getY();
                first = true;
                paint(g);
            }
        }
        else if(tipo == TipoPrimitivo.REDESENHAR){
            paint(g);
        }
        else if(tipo == TipoPrimitivo.CARIMBOFLOR){
            if(first == true){
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                first = false;
            }else{
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                first = true;
                paint(g);
            }
        }
        else if(tipo == TipoPrimitivo.CONTRZ){
            paint(g);
        }else if(tipo == TipoPrimitivo.REINICIAR){
            paint(g);
        }else if(tipo == TipoPrimitivo.REMOVER){
            //
        }
        else if(tipo == TipoPrimitivo.ROTACAO){
            xpontorotacao = e.getX();
            ypontorotacao = e.getY();

            paint(g);
        }
        else if(tipo == TipoPrimitivo.ESCALA){
            xpontoescala = e.getX();
            ypontoescala = e.getY();

            paint(g);
        }
    }

    public void mouseReleased(MouseEvent e) { 
    }           

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        this.mensag.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
    }

    public void limparDesenho() {
        Graphics g = getGraphics();
        g.setColor(getBackground()); // Define a cor do pincel como a cor de fundo
        g.fillRect(0, 0, getWidth(), getHeight()); // Preenche o Canvas com a cor de fundo
    }

    public void desenharPrimitivos(Graphics g){
        if (tipo == TipoPrimitivo.PONTO){
            PontoGr p = new PontoGr(xp1, yp1, getcor(), "", getEsp());
            p.desenharPonto(g);
            salvarPonto.adicionar(p);

            pilhaComandos.inserir(fig1);
        }

        if (tipo == TipoPrimitivo.RETA){
            RetaGr r = new RetaGr(xreta1, yreta1, xreta2, yreta2, getcor(),"",getEsp());
            r.desenharReta(g);
            salvarReta.adicionar(r);

            pilhaComandos.inserir(fig2);
        }

        if (tipo == TipoPrimitivo.TRIANGULO){
            TrianguloGr triangulo = new TrianguloGr(xt1, yt1, xt2, yt2, xt3, yt3, (""), getEsp(), getcor());
            triangulo.desenharTriangulo(g);
            salvarTriangulo.adicionar(triangulo);

            pilhaComandos.inserir(fig3);
        }

        if (tipo == TipoPrimitivo.RETANGULO){
            RetanguloGr retan = new RetanguloGr (xretang1, yretang1, xretang2, yretang2, "", getEsp(), getcor());
            retan.desenharRetangulo(g);
            salvarRetangulo.adicionar(retan);

            pilhaComandos.inserir(fig4);
        }

        if (tipo == TipoPrimitivo.CIRCULO){
            CirculoGr circ = new CirculoGr (xc1, yc1, xc2, yc2, getcor(), getEsp());
            circ.desenharCirculo(g);
            salvarCirculo.adicionar(circ);

            pilhaComandos.inserir(fig5);
        }

        if (tipo == TipoPrimitivo.CARIMBOFLOR){
            CarimboGr carimb = new CarimboGr (x1, y1, x2, y2, "", getEsp(), Gui.corCircunferenciaCarimbo, Gui.corRetaCarimbo );
            carimb.desenharCarimbo(g, x1, y1, x2, y2, getEsp(), Gui.corCircunferenciaCarimbo, Gui.corRetaCarimbo);
            salvarCarimbo.adicionar(carimb);

            pilhaCoordCarimbo.inserir(y2);
            pilhaCoordCarimbo.inserir(x2);
            pilhaCoordCarimbo.inserir(y1);
            pilhaCoordCarimbo.inserir(x1);

            //pilhaEspessuras.inserir(getEsp());

            pilhaComandos.inserir(fig6);
        }

        if (tipo==TipoPrimitivo.CONTRZ){
            if(salvarPonto.estaVazia() == false){
                contrZ();
                redesenhar(g);
            }

            else if(salvarReta.estaVazia() == false){
                contrZ();
                redesenhar(g);
            }

            else if(salvarTriangulo.estaVazia() == false ){
                contrZ();
                redesenhar(g);
            }

            else if(salvarRetangulo.estaVazia() == false){
                contrZ();
                redesenhar(g);
            }

            else if(salvarCirculo.estaVazia() == false){
                contrZ();
                redesenhar(g);
            }

            else if(salvarCarimbo.estaVazia() == false){
                contrZ();
                redesenhar(g);
            }
            /*
            else{
            System.out.println("vazia");
            }
             */
        }

        if (tipo==TipoPrimitivo.REMOVER){
            remover();
        }

        if (tipo==TipoPrimitivo.REINICIAR){
            while(salvarPonto.estaVazia() == false){
                salvarPonto.removerInicio();
            }

            while(salvarReta.estaVazia() == false){
                salvarReta.removerInicio();
            }

            while(salvarTriangulo.estaVazia() == false){
                salvarTriangulo.removerInicio();
            }

            while(salvarRetangulo.estaVazia() == false){
                salvarRetangulo.removerInicio();
            }

            while(salvarCirculo.estaVazia() == false){
                salvarCirculo.removerInicio();
            }

            while(salvarCarimbo.estaVazia() == false){
                salvarCarimbo.removerInicio();
            }
        }

        if (tipo==TipoPrimitivo.REDESENHAR){
            redesenhar(g);
        }

        if(tipo==TipoPrimitivo.ROTACAO){
            rotacionar();
        }

        if(tipo==TipoPrimitivo.ESCALA){
            escala();
        }
    }

    public void rotacionar(){
        //System.out.println(xpontorotacao);
        //System.out.println(ypontorotacao);

        Graphics g= getGraphics();

        int p=1, i=0, qtd;
        double ang = 0;
        String[] options = {"Triangulo"};

        int x = JOptionPane.showOptionDialog(null, "Escolha qual tipo de figura deseja rotacionar", "Click a button", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String[] opcoes = {"Cancelar/Sair", "Anterior", "Rotacionar", "Proximo"};

        ang = Double.parseDouble(JOptionPane.showInputDialog("Rotacionar utilizando qual angulo?"));

        switch(x){
            case 0:
                TrianguloGr rTri = new TrianguloGr(xt1, yt1, xt2, yt2, xt3, yt3, (""), getEsp(), getcor());
                qtd = salvarTriangulo.getQtd();

                while(p!=0){
                    rTri = (TrianguloGr)salvarTriangulo.buscar(i);

                    if(rTri!=null){
                        FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), Color.RED); 
                        FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), Color.RED);
                        FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), Color.RED);
                        p = JOptionPane.showOptionDialog(null, "Deseja rotacionar a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), rTri.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                i = i-1;
                            }
                        }else if(p == 2){
                            FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), getBackground()); 
                            FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), getBackground());
                            FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), getBackground());

                            salvarTrianguloPreRotacionado.adicionar(rTri);
                            pilhaComandos.inserir(figesc2);

                            rTri = new TrianguloGr((int)(xpontorotacao + ((int)rTri.getP1().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP1().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang))), 
                                ((int)(ypontorotacao + ((int)rTri.getP1().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP1().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang)))), 
                                ((int)(xpontorotacao + ((int)rTri.getP2().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP2().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang)))), 
                                ((int)(ypontorotacao + ((int)rTri.getP2().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP2().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang)))), 
                                ((int)(xpontorotacao + ((int)rTri.getP3().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP3().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang)))), 
                                ((int)(ypontorotacao + ((int)rTri.getP3().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP3().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang)))), 
                                (""), getEsp(), getcor());

                            /*
                            FiguraRetas.desenharReta(g, (int)(xpontorotacao + ((int)rTri.getP1().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP1().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang))), 
                            (int)(ypontorotacao + ((int)rTri.getP1().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP1().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang))),
                            (int)(xpontorotacao + ((int)rTri.getP2().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP2().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang))), 
                            (int)(ypontorotacao + ((int)rTri.getP2().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP2().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang))),
                            "", rTri.getEsp(), rTri.getCor()); 

                            FiguraRetas.desenharReta(g, (int)(xpontorotacao + ((int)rTri.getP1().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP1().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang))), 
                            (int)(ypontorotacao + ((int)rTri.getP1().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP1().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang))),
                            (int)(xpontorotacao + ((int)rTri.getP3().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP3().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang))), 
                            (int)(ypontorotacao + ((int)rTri.getP3().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP3().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang))),
                            "", rTri.getEsp(), rTri.getCor()); 

                            FiguraRetas.desenharReta(g, (int)(xpontorotacao + ((int)rTri.getP2().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP2().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang))), 
                            (int)(ypontorotacao + ((int)rTri.getP2().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP2().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang))),
                            (int)(xpontorotacao + ((int)rTri.getP3().getX() - xpontorotacao) * Math.cos(Math.toRadians(ang)) - ((int)rTri.getP3().getY() - ypontorotacao) * Math.sin(Math.toRadians(ang))), 
                            (int)(ypontorotacao + ((int)rTri.getP3().getX() - xpontorotacao) * Math.sin(Math.toRadians(ang)) + ((int)rTri.getP3().getY() - ypontorotacao) * Math.cos(Math.toRadians(ang))),
                            "", rTri.getEsp(), rTri.getCor()); 
                             */

                            salvarTriangulo.remover(i);
                            salvarTriangulo.adicionar(rTri);

                            p=0;
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), rTri.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                i = i+1;
                            }
                        }   
                    }
                    else{
                        p=0;
                    }
                }

                redesenhar(g);
        }
    }

    public void escala(){
        //System.out.println(xpontoescala);
        //System.out.println(ypontoescala);

        Graphics g= getGraphics();

        int p=1, i=0, qtd;
        double escx = 1, escy = 1;
        String[] options = {"Triangulo"};

        int x = JOptionPane.showOptionDialog(null, "Escolha qual tipo de figura deseja escalonar", "Click a button", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String[] opcoes = {"Cancelar/Sair", "Anterior", "Escalonar", "Proximo"};

        escx = Double.parseDouble(JOptionPane.showInputDialog("Escalonar em quantos pixels em x?"));
        escy = Double.parseDouble(JOptionPane.showInputDialog("Escalonar em quantos pixels em y?"));

        switch(x){
            case 0:
                TrianguloGr rTri = new TrianguloGr(xt1, yt1, xt2, yt2, xt3, yt3, (""), getEsp(), getcor());
                qtd = salvarTriangulo.getQtd();

                while(p!=0){
                    rTri = (TrianguloGr)salvarTriangulo.buscar(i);

                    if(rTri!=null){
                        FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), Color.RED); 
                        FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), Color.RED);
                        FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), Color.RED);
                        p = JOptionPane.showOptionDialog(null, "Deseja escalonar a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), rTri.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                i = i-1;
                            }
                        }else if(p == 2){
                            FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), getBackground()); 
                            FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), getBackground());
                            FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), getBackground());

                            salvarTrianguloPreEscalonado.adicionar(rTri);
                            pilhaComandos.inserir(figesc1);

                            rTri = new TrianguloGr((int)(rTri.getP1().getX() * escx + xpontoescala * (1 - escx)), 
                                ((int)(rTri.getP1().getY() * escy + ypontoescala * (1 - escy))), 
                                ((int)(rTri.getP2().getX() * escx + xpontoescala * (1 - escx))), 
                                ((int)(rTri.getP2().getY() * escy + ypontoescala * (1 - escy))), 
                                ((int)(rTri.getP3().getX() * escx + xpontoescala * (1 - escx))), 
                                ((int)(rTri.getP3().getY() * escy + ypontoescala * (1 - escy))), 
                                (""), getEsp(), getcor());

                            //FiguraRetas.desenharReta(g, ((int)rTri.getP1().getX() * escx + xpontoescala * (1 - escx)), ((int)rTri.getP1().getY() * escy + ypontoescala * (1 - escy)), ((int)rTri.getP2().getX() * escx + xpontoescala * (1 - escx)), ((int)rTri.getP2().getY() * escy + ypontoescala * (1 - escy)), "", rTri.getEsp(), rTri.getCor()); 
                            //FiguraRetas.desenharReta(g, ((int)rTri.getP1().getX() * escx + xpontoescala * (1 - escx)), ((int)rTri.getP1().getY() * escy + ypontoescala * (1 - escy)), ((int)rTri.getP3().getX() * escx + xpontoescala * (1 - escx)), ((int)rTri.getP3().getY() * escy + ypontoescala * (1 - escy)), "", rTri.getEsp(), rTri.getCor());
                            //FiguraRetas.desenharReta(g, ((int)rTri.getP2().getX() * escx + xpontoescala * (1 - escx)), ((int)rTri.getP2().getY() * escy + ypontoescala * (1 - escy)), ((int)rTri.getP3().getX() * escx + xpontoescala * (1 - escx)), ((int)rTri.getP3().getY() * escy + ypontoescala * (1 - escy)), "", rTri.getEsp(), rTri.getCor());

                            salvarTriangulo.remover(i);
                            salvarTriangulo.adicionar(rTri);

                            p=0;
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), rTri.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                i = i+1;
                            }
                        }   
                    }
                    else{
                        p=0;
                    }
                }

                redesenhar(g);
        }
    }

    public void redesenhar(Graphics g){
        int count,xpont1, ypont1, xpont2, ypont2, espCarimb;
        PontoGr p; 
        RetaGr r;
        TrianguloGr trian;
        RetanguloGr retan;
        CirculoGr circ; 
        CarimboGr carimb;

        if(salvarPonto.estaVazia() == false){
            for(count = 0; count< salvarPonto.getQtd(); count++){
                p = (PontoGr)salvarPonto.buscar(count);
                p.desenharPonto(g);
            }
        }
        if(salvarReta.estaVazia() == false){
            count= 0;
            while(count < salvarReta.getQtd()){
                r = (RetaGr) salvarReta.buscar(count);
                r.desenharReta(g);
                count++;
            }
        }
        if(salvarTriangulo.estaVazia() == false ){
            for(count = 0;count< salvarTriangulo.getQtd(); count++){
                trian = (TrianguloGr) salvarTriangulo.buscar(count);
                trian.desenharTriangulo(g);
            }
        }
        if(salvarRetangulo.estaVazia() == false){
            for(count = 0;count< salvarRetangulo.getQtd(); count++){
                retan = (RetanguloGr) salvarRetangulo.buscar(count);
                retan.desenharRetangulo(g);
            }
        }
        if(salvarCirculo.estaVazia() == false){
            for(count = 0;count< salvarCirculo.getQtd(); count++){
                circ = (CirculoGr) salvarCirculo.buscar(count);
                circ.desenharCirculo(g);
            }
        }
        if(salvarCarimbo.estaVazia() == false){
            for(count = 0;count< salvarCarimbo.getQtd(); count++){
                carimb = (CarimboGr) salvarCarimbo.buscar(count);

                // xpont1 = Integer.parseInt(pilhaCoordCarimbo.topo().toString());
                // pilhaCoordCarimbo.remover();

                // ypont1 = Integer.parseInt(pilhaCoordCarimbo.topo().toString());
                // pilhaCoordCarimbo.remover();

                // xpont2 = Integer.parseInt(pilhaCoordCarimbo.topo().toString());
                // pilhaCoordCarimbo.remover();

                // ypont2 = Integer.parseInt(pilhaCoordCarimbo.topo().toString());
                // pilhaCoordCarimbo.remover();

                // espCarimb = Integer.parseInt(pilhaEspessuras.topo().toString());
                // pilhaEspessuras.remover();

                carimb.desenharCarimbo(g, (int)carimb.getP1().getX(), (int)carimb.getP1().getY(), (int)carimb.getP2().getX(), (int)carimb.getP2().getY(), (int)carimb.getEsp(), carimb.getCor1() , carimb.getCor2());

                // filaAux.inserirInicio(xpont1);
                // filaAux.inserirInicio(ypont1);
                // filaAux.inserirInicio(xpont2);
                // filaAux.inserirInicio(ypont2);

                // filaAuxEsp.inserirInicio(espCarimb);
            }

            while (filaAux.estaVazia() == false){
                pilhaCoordCarimbo.inserir(filaAux.getInicio());
                filaAux.removerInicio();

                pilhaCoordCarimbo.inserir(filaAux.getInicio());
                filaAux.removerInicio();

                pilhaCoordCarimbo.inserir(filaAux.getInicio());
                filaAux.removerInicio();

                pilhaCoordCarimbo.inserir(filaAux.getInicio());
                filaAux.removerInicio();
            }

            /*
            while(filaAuxEsp.estaVazia() == false){
                pilhaEspessuras.inserir(filaAuxEsp.getInicio());
                filaAuxEsp.removerInicio();
            }
            */
        }
    }

    public void remover(){
        Graphics g= getGraphics();

        int p=1, i=0, qtd;
        // cria as opcoes base de selecao de primitivos
        String[] options = {"Ponto", "Reta", "Triangulo", "Retangulo", "Circulo", "Carimbo"};
        
        int x = JOptionPane.showOptionDialog(null, "Escolha qual tipo de figura deseja remover", "Click a button", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        // cria as opcoes base de todas instancias do remover        
        String[] opcoes = {"Cancelar/Sair", "Anterior", "Remover", "Proximo"};

        // cada caso a partir do zero representa uma das opcoes de primitivos definidas acima
        switch(x){
            case 0:
                PontoGr rP = new PontoGr(xp1, yp1, getcor(), "", getEsp());
                qtd = salvarPonto.getQtd();

                while(p!=0){   
                    rP = (PontoGr)salvarPonto.buscar(i); 
                    
                    if(rP!=null){
                        //cria a figura selecionada em vermelho para destaque
                        FiguraPontos.desenharPonto(g, (int)rP.getX(), (int)rP.getY(), "",  rP.getDiametro(), Color.RED);
                        p = JOptionPane.showOptionDialog(null, "Deseja remover a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                //retorna a figura a sua cor original
                                FiguraPontos.desenharPonto(g, (int)rP.getX(), (int)rP.getY(), "", rP.getDiametro(), rP.getCorPto());
                                i = i-1;
                            }
                        }else if(p == 2){
                            //remove a figura da tela e remove ela da ED
                            FiguraPontos.desenharPonto(g, (int)rP.getX(), (int)rP.getY(), "", rP.getDiametro(), getBackground());
                            
                            salvarPontoRemocao.adicionar(rP);
                            pilhaComandos.inserir(figrem1);
                            
                            salvarPonto.remover(i);
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                //retorna a figura a sua cor original
                                FiguraPontos.desenharPonto(g, (int)rP.getX(), (int)rP.getY(), "", rP.getDiametro(), rP.getCorPto());
                                i = i+1;
                            }
                        }
                    }
                    else{
                        //garante que caso o acesso seja nulo o programa nao entra em loop
                        p=0;
                    }
                }
            
                redesenhar(g);

            case 1:
                RetaGr rT = new RetaGr(xreta1, yreta1, xreta2, yreta2, getcor(),"",getEsp());
                qtd = salvarReta.getQtd();

                while(p!=0){
                    rT = (RetaGr)salvarReta.buscar(i);

                    if(rT!=null){
                        //cria a figura selecionada em vermelho para destaque
                        FiguraRetas.desenharReta(g, (int)rT.getP1().getX(), (int)rT.getP1().getY(), (int)rT.getP2().getX(), (int)rT.getP2().getY(), "", rT.getEspReta(), Color.RED);
                        p = JOptionPane.showOptionDialog(null, "Deseja remover a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                //retorna a figura a sua cor original
                                FiguraRetas.desenharReta(g, (int)rT.getP1().getX(), (int)rT.getP1().getY(), (int)rT.getP2().getX(), (int)rT.getP2().getY(), "", rT.getEspReta(), rT.getCorReta());
                                i = i-1;
                            }
                        }else if(p == 2){
                            //remove a figura da tela e remove ela da ED
                            FiguraRetas.desenharReta(g, (int)rT.getP1().getX(), (int)rT.getP1().getY(), (int)rT.getP2().getX(), (int)rT.getP2().getY(), "", rT.getEspReta(), getBackground());
                            
                            salvarRetaRemocao.adicionar(rT);
                            pilhaComandos.inserir(figrem2);
                            
                            salvarReta.remover(i);
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                //retorna a figura a sua cor original
                                FiguraRetas.desenharReta(g, (int)rT.getP1().getX(), (int)rT.getP1().getY(), (int)rT.getP2().getX(), (int)rT.getP2().getY(), "", rT.getEspReta(), rT.getCorReta());
                                i = i+1;
                            }
                        }
                    }
                    else{
                        //garante que caso o acesso seja nulo o programa nao entra em loop
                        p=0;
                    }
                }

                redesenhar(g);

            case 2:
                TrianguloGr rTri = new TrianguloGr(xt1, yt1, xt2, yt2, xt3, yt3, (""), getEsp(), getcor());
                qtd = salvarTriangulo.getQtd();

                while(p!=0){
                    rTri = (TrianguloGr)salvarTriangulo.buscar(i);

                    if(rTri!=null){
                        //cria a figura selecionada em vermelho para destaque
                        FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), Color.RED); 
                        FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), Color.RED);
                        FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), Color.RED);
                        p = JOptionPane.showOptionDialog(null, "Deseja remover a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), rTri.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                i = i-1;
                            }
                        }else if(p == 2){
                            //remove a figura da tela e remove ela da ED
                            FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), getBackground()); 
                            FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), getBackground());
                            FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), getBackground());
                            
                            salvarTrianguloRemocao.adicionar(rTri);
                            pilhaComandos.inserir(figrem3);
                            
                            salvarTriangulo.remover(i);
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                //retorna a figura a sua cor original
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), "", rTri.getEsp(), rTri.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rTri.getP1().getX(), (int)rTri.getP1().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                FiguraRetas.desenharReta(g, (int)rTri.getP2().getX(), (int)rTri.getP2().getY(), (int)rTri.getP3().getX(), (int)rTri.getP3().getY(), "", rTri.getEsp(), rTri.getCor());
                                i = i+1;
                            }
                        }   
                    }
                    else{
                        //garante que caso o acesso seja nulo o programa nao entra em loop
                        p=0;
                    }
                }

                redesenhar(g);

            case 3:    
                RetanguloGr rRetan = new RetanguloGr (xretang1, yretang1, xretang2, yretang2, "", getEsp(), getcor());
                qtd = salvarRetangulo.getQtd();

                while(p!=0){
                    rRetan = (RetanguloGr)salvarRetangulo.buscar(i);

                    if(rRetan!=null){
                        //cria a figura selecionada em vermelho para destaque
                        FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), "", rRetan.getEsp(), Color.RED); 
                        FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), Color.RED);
                        FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), Color.RED);
                        FiguraRetas.desenharReta(g, (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), Color.RED);
                        p = JOptionPane.showOptionDialog(null, "Deseja remover a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), "", rRetan.getEsp(), rRetan.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), rRetan.getCor());
                                FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), rRetan.getCor());
                                FiguraRetas.desenharReta(g, (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), rRetan.getCor());
                                i = i-1;
                            }
                        }else if(p == 2){
                            //remove a figura da tela e remove ela da ED
                            FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), "", rRetan.getEsp(), getBackground()); 
                            FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), getBackground());
                            FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), getBackground());
                            FiguraRetas.desenharReta(g, (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), getBackground());
                            
                            salvarRetanguloRemocao.adicionar(rRetan);
                            pilhaComandos.inserir(figrem4);
                            
                            salvarRetangulo.remover(i);
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                //retorna a figura a sua cor original
                                FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), "", rRetan.getEsp(), rRetan.getCor()); 
                                FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), rRetan.getCor());
                                FiguraRetas.desenharReta(g, (int)rRetan.getP1().getX(), (int)rRetan.getP2().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), rRetan.getCor());
                                FiguraRetas.desenharReta(g, (int)rRetan.getP2().getX(), (int)rRetan.getP1().getY(), (int)rRetan.getP2().getX(), (int)rRetan.getP2().getY(), "", rRetan.getEsp(), rRetan.getCor());
                                i = i+1;
                            }
                        }   
                    }
                    else{
                        //garante que caso o acesso seja nulo o programa nao entra em loop
                        p=0;
                    }
                }

                redesenhar(g);

            case 4:
                CirculoGr rCirc = new CirculoGr (xc1, yc1, xc2, yc2, getcor(), getEsp());
                qtd = salvarCirculo.getQtd(); 

                while(p!=0){
                    rCirc = (CirculoGr)salvarCirculo.buscar(i);
                    
                    //cria a figura selecionada em vermelho para destaque
                    if(rCirc!=null){
                        PontoGr ponto;
                        double raio = rCirc.calculaRaio();
                        double xc1,yc1, xc2, yc2;

                        xc1 = rCirc.getP1().getX();
                        yc1 = rCirc.getP1().getY();

                        for(float theta = 0; theta < 360; theta += 0.1){
                            xc2 = Math.cos(Math.toRadians(theta)) * raio;
                            yc2 = Math.sin(Math.toRadians(theta)) * raio;

                            ponto = new PontoGr((int)xc1 + (int)xc2, (int)yc1 + (int)yc2, Color.RED,rCirc.getEsp());
                            ponto.desenharPonto(g);
                        }
                        p = JOptionPane.showOptionDialog(null, "Deseja remover a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                for(float theta = 0; theta < 360; theta += 0.1){
                                    xc2 = Math.cos(Math.toRadians(theta)) * raio;
                                    yc2 = Math.sin(Math.toRadians(theta)) * raio;

                                    ponto = new PontoGr((int)xc1 + (int)xc2, (int)yc1 + (int)yc2, getcor(),rCirc.getEsp());
                                    ponto.desenharPonto(g);
                                }
                                i = i-1;
                            }
                        }else if(p == 2){
                            //remove a figura da tela e remove ela da ED
                            for(float theta = 0; theta < 360; theta += 0.1){
                                xc2 = Math.cos(Math.toRadians(theta)) * raio;
                                yc2 = Math.sin(Math.toRadians(theta)) * raio;

                                ponto = new PontoGr((int)xc1 + (int)xc2, (int)yc1 + (int)yc2, getBackground(),rCirc.getEsp());
                                ponto.desenharPonto(g);
                            }
                            
                            salvarCirculoRemocao.adicionar(rCirc);
                            pilhaComandos.inserir(figrem5);
                            
                            salvarCirculo.remover(i);
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                for(float theta = 0; theta < 360; theta += 0.1){
                                    xc2 = Math.cos(Math.toRadians(theta)) * raio;
                                    yc2 = Math.sin(Math.toRadians(theta)) * raio;

                                    ponto = new PontoGr((int)xc1 + (int)xc2, (int)yc1 + (int)yc2, getcor(),rCirc.getEsp());
                                    ponto.desenharPonto(g);
                                }
                                i = i+1;
                            }
                        }
                    }
                    else{
                        //garante que caso o acesso seja nulo o programa nao entra em loop
                        p=0;
                    }
                }

                redesenhar(g);

            case 5: 
                // CarimboGr rCarimb = new CarimboGr (x1, y1, x2, y2, "", getEsp(), getCor1() , getCor2());
                CarimboGr rCarimb;
                qtd = salvarCarimbo.getQtd(); 

                while(p!=0){
                    rCarimb = (CarimboGr)salvarCarimbo.buscar(i);

                    if(rCarimb!=null){
                        //cria a figura selecionada em vermelho para destaque
                        rCarimb.desenharCarimbo(g, (int)rCarimb.getP1().getX(),(int)rCarimb.getP1().getY(), (int)rCarimb.getP2().getX(), (int)rCarimb.getP2().getY(), rCarimb.getEsp(), Color.RED, Color.RED);
                        //
                        p = JOptionPane.showOptionDialog(null, "Deseja remover a figura em vermelho?",
                            "Click a button",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                        if(p == 1){
                            if(i==0){
                                JOptionPane.showMessageDialog(null, "Não existe anterior");
                            }else{
                                //retorna a figura a sua cor original
                                rCarimb.desenharCarimbo(g, (int)rCarimb.getP1().getX(),(int)rCarimb.getP1().getY(), (int)rCarimb.getP2().getX(), (int)rCarimb.getP2().getY(), rCarimb.getEsp(), rCarimb.getCor1(), rCarimb.getCor2());
                                //
                                i = i-1;
                            }
                        }else if(p == 2){
                            //remove a figura da tela e remove ela da ED
                            rCarimb.desenharCarimbo(g, (int)rCarimb.getP1().getX(),(int)rCarimb.getP1().getY(), (int)rCarimb.getP2().getX(), (int)rCarimb.getP2().getY(), rCarimb.getEsp(), getBackground(), getBackground());
                            //
                            
                            salvarCarimboRemocao.adicionar(rCarimb);
                            pilhaComandos.inserir(figrem6);
                            
                            salvarCarimbo.remover(i);
                        }else if(p == 3){
                            if(i+1 == qtd){
                                JOptionPane.showMessageDialog(null, "Não existe proximo");
                            }else{
                                //retorna a figura a sua cor original
                                rCarimb.desenharCarimbo(g, (int)rCarimb.getP1().getX(),(int)rCarimb.getP1().getY(), (int)rCarimb.getP2().getX(), (int)rCarimb.getP2().getY(), rCarimb.getEsp(), rCarimb.getCor1(), rCarimb.getCor2());
                                //
                                i = i+1;
                            }
                        }
                    }
                    else{
                        //garante que caso o acesso seja nulo o programa nao entra em loop
                        p=0;
                    }
                }

                redesenhar(g);
        }
    }

    public void contrZ(){
        //fim = salvarPonto.getFim();
        Graphics g = getGraphics();

        Object aux = pilhaComandos.topo();

        if(aux.toString()==fig1){
            salvarPonto.remover(salvarPonto.getQtd()-1);
            pilhaComandos.remover();
        }

        else if(aux.toString()==fig2){
            salvarReta.remover(salvarReta.getQtd()-1);
            pilhaComandos.remover();
        }

        else if(aux.toString()==fig3){
            salvarTriangulo.remover(salvarTriangulo.getQtd()-1);
            pilhaComandos.remover();
        }

        else if(aux.toString()==fig4){
            salvarRetangulo.remover(salvarRetangulo.getQtd()-1);
            pilhaComandos.remover();
        }

        else if(aux.toString()==fig5){
            salvarCirculo.remover(salvarCirculo.getQtd()-1);
            pilhaComandos.remover();
        }

        else if(aux.toString()==fig6){
            salvarCarimbo.remover(salvarCarimbo.getQtd()-1);
            pilhaComandos.remover();

            pilhaCoordCarimbo.remover();
            pilhaCoordCarimbo.remover();
            pilhaCoordCarimbo.remover();
            pilhaCoordCarimbo.remover();

            //pilhaEspessuras.remover();
        }

        else if(aux.toString()==figrem1){
            int qtd;

            PontoGr rP = new PontoGr(xp1, yp1, getcor(), "", getEsp());
            qtd = salvarPontoRemocao.getQtd();

            rP = (PontoGr)salvarPontoRemocao.buscar(qtd-1); 

             salvarPonto.adicionar(rP);
             pilhaComandos.remover();

             salvarPontoRemocao.remover(qtd-1);
        }

        else if(aux.toString()==figrem2){
            int qtd;

            RetaGr rT = new RetaGr(xreta1, yreta1, xreta2, yreta2, getcor(),"",getEsp());
            qtd = salvarRetaRemocao.getQtd();

            rT = (RetaGr)salvarRetaRemocao.buscar(qtd-1);

            salvarReta.adicionar(rT);
            pilhaComandos.remover();

            salvarRetaRemocao.remover(qtd-1);
        }

        else if(aux.toString()==figrem3){
            int qtd;

            TrianguloGr rTri = new TrianguloGr(xt1, yt1, xt2, yt2, xt3, yt3, (""), getEsp(), getcor());
            qtd = salvarTrianguloRemocao.getQtd();

            rTri = (TrianguloGr)salvarTrianguloRemocao.buscar(qtd-1);

            salvarTriangulo.adicionar(rTri);
            pilhaComandos.remover();

            salvarTrianguloRemocao.remover(qtd-1);
        }

        else if(aux.toString()==figrem4){
            int qtd;

            RetanguloGr rRetan = new RetanguloGr (xretang1, yretang1, xretang2, yretang2, "", getEsp(), getcor());
            qtd = salvarRetanguloRemocao.getQtd();

            rRetan = (RetanguloGr)salvarRetanguloRemocao.buscar(qtd-1);

            salvarRetangulo.adicionar(rRetan);
            pilhaComandos.remover();

            salvarRetanguloRemocao.remover(qtd-1);
        }

        else if(aux.toString()==figrem5){
            int qtd;

            CirculoGr rCirc = new CirculoGr (xc1, yc1, xc2, yc2, getcor(), getEsp());
            qtd = salvarCirculoRemocao.getQtd(); 

            rCirc = (CirculoGr)salvarCirculoRemocao.buscar(qtd-1);

            salvarCirculo.adicionar(rCirc);
            pilhaComandos.remover();

            salvarCirculoRemocao.remover(qtd-1);
        }

        else if(aux.toString()==figrem6){
            int qtd;

            //CarimboGr rCarimb = new CarimboGr (x1, y1, x2, y2, "", getEsp(), getcor());
            CarimboGr rCarimb;
            qtd = salvarCarimboRemocao.getQtd(); 

            rCarimb = (CarimboGr)salvarCarimboRemocao.buscar(qtd-1);

            salvarCarimbo.adicionar(rCarimb);
            pilhaComandos.remover();

            salvarCarimboRemocao.remover(qtd-1);
        }

        else if(aux.toString()==figesc1){
            int qtd;

            TrianguloGr rTri = new TrianguloGr(xt1, yt1, xt2, yt2, xt3, yt3, (""), getEsp(), getcor());
            qtd = salvarTrianguloPreEscalonado.getQtd();

            rTri = (TrianguloGr)salvarTrianguloPreEscalonado.buscar(qtd-1);

            salvarTriangulo.remover(qtd-1);
            salvarTriangulo.adicionar(rTri);
            pilhaComandos.remover();

            salvarTrianguloPreEscalonado.remover(qtd-1);
        }

        else if(aux.toString()==figesc2){
            int qtd;

            TrianguloGr rTri = new TrianguloGr(xt1, yt1, xt2, yt2, xt3, yt3, (""), getEsp(), getcor());
            qtd = salvarTrianguloPreRotacionado.getQtd();

            rTri = (TrianguloGr)salvarTrianguloPreRotacionado.buscar(qtd-1);

            salvarTriangulo.remover(qtd-1);
            salvarTriangulo.adicionar(rTri);
            pilhaComandos.remover();

            salvarTrianguloPreRotacionado.remover(qtd-1);
        }
    }

    public void salvar(int larg, int alt){
        JSONObject desenho = new JSONObject();
        JSONObject root = new JSONObject();
        JSONArray pontoArray = new JSONArray();
        JSONArray retaArray = new JSONArray();
        JSONArray trianguloArray = new JSONArray();
        JSONArray retanguloArray = new JSONArray();
        JSONArray circuloArray = new JSONArray();
        JSONArray mandalaArray = new JSONArray();

        if(salvarPonto.getInicio()!=null){
            for(int i=0;i<salvarPonto.getQtd();i++){
                PontoGr auxpt= (PontoGr)salvarPonto.buscar(i);
                Color cores = auxpt.getCorPto();

                double xn = (double)auxpt.getX()/ larg;
                double yn = (double)auxpt.getY()/ alt;

                JSONObject ponto1 = new JSONObject();
                ponto1.put("id", "ponto_1");
                ponto1.put("esp", auxpt.getDiametro());
                ponto1.put("x", xn);
                ponto1.put("y", yn);

                // Criar um objeto JSON para a cor
                JSONObject cor = new JSONObject();
                cor.put("r", cores.getRed());
                cor.put("g", cores.getGreen());
                cor.put("b", cores.getBlue());

                // Adicionar o objeto de cor ao objeto de ponto
                ponto1.put("cor", cor);

                // Adicionar o objeto de ponto ao array de pontos
                pontoArray.put(ponto1);
                desenho.put("ponto", pontoArray);
            }
        }

        if(salvarReta.getInicio()!=null){
            for(int i=0;i<salvarReta.getQtd();i++){
                RetaGr auxret= (RetaGr)salvarReta.buscar(i);
                Color cores = auxret.getCorReta();

                JSONObject reta1 = new JSONObject();
                reta1.put("id", "reta_1");
                reta1.put("esp", auxret.getEspReta());

                // Acessar os pontos p1 e p2
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();

                double xn1 = (double) auxret.getP1().getX()/ larg;
                double yn1 = (double) auxret.getP1().getY()/ alt;

                double xn2 = (double) auxret.getP2().getX()/ larg;
                double yn2 = (double)auxret.getP2().getY()/ alt;

                p1.put("x", xn1);
                p1.put("y", yn1);
                p2.put("x", xn2);
                p2.put("y", yn2);
                reta1.put("p1", p1);
                reta1.put("p2", p2);
                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = new JSONObject();
                cor.put("r", cores.getRed());
                cor.put("g", cores.getGreen());
                cor.put("b", cores.getBlue());
                reta1.put("cor", cor);

                // Adicionar a reta1 no array de retas
                retaArray.put(reta1);// Adicione os arrays ao objeto desenho
                desenho.put("reta", retaArray);
            }
        }

        if(salvarCirculo.getInicio()!=null){
            for(int i=0;i<salvarCirculo.getQtd();i++){
                CirculoGr auxcirc= (CirculoGr)salvarCirculo.buscar(i);
                Color cores = auxcirc.getCor();

                // Circulos
                JSONObject circ1 = new JSONObject();
                circ1.put("id", "circulo_1");
                circ1.put("esp", auxcirc.getEsp());

                // Criar pontos p1 e p2 das diagonais do retangulo
                JSONObject p1 = new JSONObject();

                double xn1 = (double)auxcirc.getP1().getX()/ larg;
                double yn1 = (double)auxcirc.getP1().getY()/ alt;
                double raio =(double)auxcirc.calculaRaio()/larg;

                p1.put("x", xn1);
                p1.put("y", yn1);
                circ1.put("centro", p1);
                circ1.put("raio", raio );
                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = new JSONObject();
                cor.put("r", cores.getRed());
                cor.put("g", cores.getGreen());
                cor.put("b", cores.getBlue());
                circ1.put("cor", cor);

                // Adicionar a reta1 no array de retas
                circuloArray.put(circ1);// Adicione os arrays ao objeto desenho
                desenho.put("circulo", circuloArray);
            }
        }

        if(salvarRetangulo.getInicio()!=null){
            for(int i=0;i<salvarRetangulo.getQtd();i++){
                RetanguloGr auxretang = (RetanguloGr)salvarRetangulo.buscar(i);
                Color cores = auxretang.getCor();

                JSONObject retang1 = new JSONObject();
                retang1.put("id", "retangulo_1");
                retang1.put("esp", auxretang.getEsp());

                // Criar pontos p1 e p2 das diagonais do retangulo
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();

                double xn1 = (double)auxretang.getP1().getX()/ larg;
                double yn1 = (double)auxretang.getP1().getY()/ alt;

                double xn2 = (double)auxretang.getP2().getX()/ larg;
                double yn2 =(double) auxretang.getP2().getY()/ alt;

                p1.put("x", xn1);
                p1.put("y", yn1);
                p2.put("x", xn2);
                p2.put("y", yn2);
                retang1.put("p1", p1);
                retang1.put("p2", p2);
                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = new JSONObject();
                cor.put("r", cores.getRed());
                cor.put("g", cores.getGreen());
                cor.put("b", cores.getBlue());
                retang1.put("cor", cor);

                // Adicionar a reta1 no array de retas
                retanguloArray.put(retang1);// Adicione os arrays ao objeto desenho
                desenho.put("retangulo", retanguloArray);
            }
        }

        if(salvarTriangulo.getInicio()!=null){
            for(int i=0;i<salvarTriangulo.getQtd();i++){
                TrianguloGr auxtri = (TrianguloGr)salvarTriangulo.buscar(i);
                Color cores = auxtri.getCor();

                JSONObject triang1 = new JSONObject();
                triang1.put("id", "triangulo_1");
                triang1.put("esp", auxtri.getEsp());

                // Criar pontos p1, p2 e p3
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();
                JSONObject p3 = new JSONObject();

                double xn1 = (double)auxtri.getP1().getX()/ larg;
                double yn1 = (double)auxtri.getP1().getY()/ alt;

                double xn2 = (double)auxtri.getP2().getX()/ larg;
                double yn2 = (double)auxtri.getP2().getY()/ alt;

                double xn3 = (double)auxtri.getP3().getX()/ larg;
                double yn3 = (double)auxtri.getP3().getY()/ alt;

                p1.put("x", xn1);
                p1.put("y", yn1);
                p2.put("x", xn2);
                p2.put("y", yn2);
                p3.put("x", xn3);
                p3.put("y", yn3);

                triang1.put("p1", p1);
                triang1.put("p2", p2);
                triang1.put("p3", p3);
                // Acessar a chave "cor" para obter os valores de R, G e B
                // Acessar a chave "cor" para obter os valores de R, G e B
                JSONObject cor = new JSONObject();
                cor.put("r", cores.getRed());
                cor.put("g", cores.getGreen());
                cor.put("b", cores.getBlue());
                triang1.put("cor", cor);

                // Adicionar a triang1 no array de triangulos
                trianguloArray.put(triang1);// Adicione os arrays ao objeto desenho
                desenho.put("triangulo", trianguloArray);                
            }
        }

        if(salvarCarimbo.getInicio()!=null){
            for(int i=0;i<salvarCarimbo.getQtd();i++){
                CarimboGr auxcarimbo = (CarimboGr)salvarCarimbo.buscar(i);
                Color cores = auxcarimbo.getCor1();

                JSONObject mand1 = new JSONObject();
                mand1.put("id", "mandala_1");
                mand1.put("esp", auxcarimbo.getEsp());

                // Criar pontos p1 e p2 das diagonais do retangulo
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();

                double xn1 = (double)auxcarimbo.getP1().getX()/ larg;
                double yn1 = (double)auxcarimbo.getP1().getY()/ alt;

                double xn2 = (double)auxcarimbo.getP2().getX()/ larg;
                double yn2 = (double)auxcarimbo.getP2().getY()/ alt;

                p1.put("x", xn1);
                p1.put("y", yn1);
                p2.put("x", xn2);
                p2.put("y", yn2);

                mand1.put("p1", p1);
                mand1.put("p2", p2);

                // Criar cor1 e cor2 com os valores de R, G e B
                JSONObject cor1 = new JSONObject();
                JSONObject cor2 = new JSONObject();
                cor1.put("r", cores.getRed());
                cor1.put("g", cores.getGreen());
                cor1.put("b", cores.getBlue());

                mand1.put("cor1", cor1);

                cores = auxcarimbo.getCor2();

                cor2.put("r", cores.getRed());
                cor2.put("g", cores.getGreen());
                cor2.put("b", cores.getBlue());
                mand1.put("cor2", cor2);

                // Adicionar a reta1 no array de retas
                mandalaArray.put(mand1);// Adicione os arrays ao objeto desenho
                desenho.put("mandala", mandalaArray);

            }
        }

        //----------------------------------------------------------
        // Desenho completo
        // Adiciona o objeto desenho ao objeto root
        root.put("desenho", desenho);

        // Escrever o JSON em um arquivo
        try (FileWriter file = new FileWriter(JOptionPane.showInputDialog("Digite o nome do arquivo + terminação .json (ex: teste.json)"))) {
            file.write(root.toString(4)); // O argumento 4 indica a quantidade de espaços de recuo para a formatação
            JOptionPane.showMessageDialog (null,"Arquivo JSON criado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }  

    public void ler(int larg, int alt){
        limparED();
        try {
            // Ler o arquivo JSON
            FileReader reader = new FileReader(JOptionPane.showInputDialog("Digite o nome do arquivo +terminação .json (ex: teste.json)"));

            // Criar um objeto JSON a partir do arquivo
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));

            // Acessar a chave "desenho"
            JSONObject desenho = jsonObject.getJSONObject("desenho");

            // Acessar as chaves dentro de "desenho" 
            if(!desenho.isNull("ponto")){
                JSONArray pontoArray = desenho.getJSONArray("ponto");
                // Processar os PONTOS
                for (int i = 0; i < pontoArray.length(); i++) {
                    JSONObject dot = pontoArray.getJSONObject(i);
                    String id = dot.getString("id");
                    double esp = dot.getDouble("esp");
                    double x = dot.getDouble("x") *larg;
                    double y = dot.getDouble("y") *alt;
                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = dot.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    Color cores = new Color(r, g, b); 
                    //instancia de objeto para salvar o primitivo na lista ligada
                    Object pt = new PontoGr((int) x, (int) y, cores, "", (int) esp);
                    salvarPonto.adicionar(pt);

                    // System.out.println("Ponto " + id + ": Espessura=" + esp + ", X=" + x + ", Y=" + y + ", Cor (R, G, B)=" + r + ", " + g + ", " + b);
                }
            }       
            // Processar as RETAS
            if(!desenho.isNull("reta")){
                JSONArray retaArray = desenho.getJSONArray("reta");
                for (int i = 0; i < retaArray.length(); i++) {
                    JSONObject ret = retaArray.getJSONObject(i);
                    String id = ret.getString("id");
                    double esp = ret.getDouble("esp");

                    // Acessar os pontos p1 e p2
                    JSONObject p1 = ret.getJSONObject("p1");
                    JSONObject p2 = ret.getJSONObject("p2");

                    double p1x = p1.getDouble("x")*larg;
                    double p1y = p1.getDouble("y")*alt;
                    double p2x = p2.getDouble("x")*larg;
                    double p2y = p2.getDouble("y")*alt;

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = ret.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    Color cores = new Color(r, g, b); 

                    Object rt = new RetaGr((int) p1x, (int) p1y, (int) p2x, (int) p2y, cores, "", (int) esp);
                    salvarReta.adicionar(rt);

                    // // Mostrar os valores lidos
                    // System.out.println("Reta " + id + ": Espessura=" + esp);
                    // System.out.println("Ponto 1 (X, Y): " + p1x + ", " + p1y);
                    // System.out.println("Ponto 2 (X, Y): " + p2x + ", " + p2y);
                    // System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
                }
            }
            // Processar os CIRCULOS
            if(!desenho.isNull("circulo")){
                JSONArray circuloArray = desenho.getJSONArray("circulo");
                for (int i = 0; i < circuloArray.length(); i++) {
                    JSONObject circ = circuloArray.getJSONObject(i);
                    String id = circ.getString("id");
                    double esp = circ.getDouble("esp");

                    // Acessar informacoes do circulo
                    JSONObject centro = circ.getJSONObject("centro");
                    double centroX = centro.getDouble("x")*larg;
                    double centroY = centro.getDouble("y")*alt;
                    double raio =   circ.getDouble("raio")*larg;

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = circ.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    Color cores = new Color(r, g, b); 

                    Object rt = new CirculoGr((int) centroX, (int) centroY, (int)(centroX+ raio), (int)(centroY), cores, (int) esp);
                    salvarCirculo.adicionar(rt);

                    // // Mostrar os valores lidos
                    // System.out.println("Circulo " + id + ": Espessura=" + esp);
                    // System.out.println("Centro (X, Y): " + centroX + ", " + centroY);
                    // System.out.println("Raio: " + raio);
                    // System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
                }
            }
            // Processar os TRIANGULOS
            if(!desenho.isNull("triangulo")){
                JSONArray trianguloArray = desenho.getJSONArray("triangulo");
                for (int i = 0; i < trianguloArray.length(); i++) {
                    JSONObject tri = trianguloArray.getJSONObject(i);
                    String id = tri.getString("id");
                    double esp = tri.getDouble("esp");

                    // Acessar informacoes dos tres pontos do triangulo
                    JSONObject p1 = tri.getJSONObject("p1");
                    JSONObject p2 = tri.getJSONObject("p2");
                    JSONObject p3 = tri.getJSONObject("p3");

                    double p1x = p1.getDouble("x")*larg;
                    double p1y = p1.getDouble("y")*alt;
                    double p2x = p2.getDouble("x")*larg;
                    double p2y = p2.getDouble("y")*alt;
                    double p3x = p3.getDouble("x")*larg;
                    double p3y = p3.getDouble("y")*alt;

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = tri.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    Color cores = new Color(r, g, b); 

                    Object trg = new TrianguloGr((int) p1x, (int) p1y, (int)p2x,(int) p2y,(int) p3x,(int)p3y, "", (int) esp, cores);
                    salvarTriangulo.adicionar(trg);

                    // Mostrar os valores lidos
                    // System.out.println("Triangulo " + id + ": Espessura=" + esp);
                    // System.out.println("Ponto 1 (X, Y): " + p1x + ", " + p1y);
                    // System.out.println("Ponto 2 (X, Y): " + p2x + ", " + p2y);
                    // System.out.println("Ponto 3 (X, Y): " + p3x + ", " + p3y);
                    // System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
                }
            }
            // Processar RETANGULOS
            if(!desenho.isNull("retangulo")){
                JSONArray retanguloArray = desenho.getJSONArray("retangulo");
                for (int i = 0; i < retanguloArray.length(); i++) {
                    JSONObject retang = retanguloArray.getJSONObject(i);
                    String id = retang.getString("id");
                    double esp = retang.getDouble("esp");

                    // Acessar informacoes dos dois pontos do retangulo
                    JSONObject p1 = retang.getJSONObject("p1");
                    JSONObject p2 = retang.getJSONObject("p2");

                    double p1x = p1.getDouble("x")*larg;
                    double p1y = p1.getDouble("y")*alt;
                    double p2x = p2.getDouble("x")*larg;
                    double p2y = p2.getDouble("y")*alt;

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    JSONObject cor = retang.getJSONObject("cor");
                    int r = cor.getInt("r");
                    int g = cor.getInt("g");
                    int b = cor.getInt("b");

                    Color cores = new Color(r, g, b); 

                    Object rtg = new RetanguloGr((int) p1x, (int) p1y, (int) p2x, (int) p2y, "", (int) esp, cores);
                    salvarRetangulo.adicionar(rtg);

                    // Mostrar os valores lidos
                    // System.out.println("Retangulo " + id + ": Espessura=" + esp);
                    // System.out.println("Ponto 1 (X, Y): " + p1x + ", " + p1y);
                    // System.out.println("Ponto 2 (X, Y): " + p2x + ", " + p2y);
                    // System.out.println("Cor (R, G, B): " + r + ", " + g + ", " + b);
                }
            }
            // Processar MANDALAS
            if(!desenho.isNull("mandala")){
                JSONArray mandalaArray = desenho.getJSONArray("mandala");
                for (int i = 0; i < mandalaArray.length(); i++) {
                    JSONObject mandala = mandalaArray.getJSONObject(i);
                    String id = mandala.getString("id");
                    double esp = mandala.getDouble("esp");

                    // Acessar informacoes dos pontos da mandala
                    JSONObject p1 = mandala.getJSONObject("p1");
                    JSONObject p2 = mandala.getJSONObject("p2");

                    double p1x = p1.getDouble("x")*larg;
                    double p1y = p1.getDouble("y")*alt;
                    double p2x = p2.getDouble("x")*larg;
                    double p2y = p2.getDouble("y")*alt;

                    // Acessar informacoes das cores da mandala
                    JSONObject cor1 = mandala.getJSONObject("cor1");
                    JSONObject cor2 = mandala.getJSONObject("cor2");

                    int r1 = cor1.getInt("r");
                    int g1 = cor1.getInt("g");
                    int b1 = cor1.getInt("b");

                    Color cores1 = new Color(r1, g1, b1); 

                    int r2 = cor2.getInt("r");
                    int g2 = cor2.getInt("g");
                    int b2 = cor2.getInt("b");

                    Color cores2 = new Color(r2, g2, b2); 

                    Object cb = new CarimboGr((int) p1x, (int) p1y, (int)p2x,(int) p2y, "", (int) esp, cores1,cores2);
                    salvarCarimbo.adicionar(cb);

                    // Mostrar os valores lidos
                    // System.out.println("Mandala " + id + ": Espessura=" + esp);
                    // System.out.println("Ponto 1 (X, Y): " + p1x + ", " + p1y);
                    // System.out.println("Ponto 2 (X, Y): " + p2x + ", " + p2y);
                    // System.out.println("Cor 1 (R, G, B): " + r1 + ", " + g1 + ", " + b1);
                    // System.out.println("Cor 2 (R, G, B): " + r2 + ", " + g2 + ", " + b2);
                }
            }
            JOptionPane.showMessageDialog (null, "Arquivo lido com sucesso!");
            // Feche o leitor
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void limparED(){
        if (salvarPonto.getQtd()!= 0){
            for(int i = 0; i<salvarPonto.getQtd(); i++){
                salvarPonto.remover(salvarPonto.getQtd()-1);
            }
        }
        if (salvarReta.getQtd()!= 0){
            for(int i = 0; i<salvarReta.getQtd(); i++){
                salvarReta.remover(salvarReta.getQtd()-1);
            }
        }
        if (salvarTriangulo.getQtd()!= 0){
            for(int i = 0; i<salvarTriangulo.getQtd(); i++){
                salvarTriangulo.remover(salvarTriangulo.getQtd()-1);
            }
        }
        if (salvarCirculo.getQtd()!= 0){
            for(int i = 0; i<salvarCirculo.getQtd(); i++){
                salvarCirculo.remover(salvarCirculo.getQtd()-1);
            }
        }
        if (salvarRetangulo.getQtd()!= 0){
            for(int i = 0; i<salvarRetangulo.getQtd(); i++){
                salvarRetangulo.remover(salvarRetangulo.getQtd()-1);
            }
        }
        if (salvarCarimbo.getQtd()!= 0){
            for(int i = 0; i<salvarCarimbo.getQtd(); i++){
                salvarCarimbo.remover(salvarCarimbo.getQtd()-1);
            }
        }
    }
}
