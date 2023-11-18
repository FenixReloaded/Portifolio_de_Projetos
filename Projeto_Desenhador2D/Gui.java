/**
 * 
 * Caio Pereira Guimarães 
 * Gustavo Leite Ioels 
 * Pedro França de Godoi 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")

class Gui extends JFrame {
    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;

    private Color corDeFundoPadrao = Color.WHITE; // Cor padrão
    private Color corDeFundoAtual = corDeFundoPadrao;

    private Color corAtual = Color.BLACK;

    //Cores padroes do CarimboFlor
    private Color corCarimboFlor = Color.ORANGE; // Cor padrão para CARIMBOFLOR
    public static Color corCircunferenciaCarimbo = Color.ORANGE; //Cor padrão para Circunferencia no CARIMBOFLOR
    public static Color corRetaCarimbo = Color.RED; //Cor padrão para Retangulo no CARIMBOFLOR
    //private Color corAtualTriangulo = Color.GREEN; //Cor padrao para Triangulo no CARIMBOFLOR

    // Espessura atual do primitivo
    private int espAtual = 2;
    int A = 0;

    // Componentes de GUI
    // barra de menu (inserir componente)
    private JToolBar barraComandos = new JToolBar();
    // mensagens
    private JLabel msg = new JLabel("Msg: ");
    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, corAtual, 10);
    // Botoes
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbReta = new JButton("Reta");
    private JButton jbTriangulo = new JButton("Triangulo");
    private JButton jbRetangulo = new JButton("Retangulo");
    private JButton jbCirculo = new JButton("Circulo");
    private JButton jbCarimboFlor = new JButton("Mandala");
    private JButton jbCorCarimbo = new JButton("Cor Mandala");

    private JButton jbRedesenhar = new JButton("Redesenhar");
    private JButton jbLimpar = new JButton("Limpar Tela");
    private JButton jbContrZ = new JButton("Voltar");
    private JButton jbRemover = new JButton("Remover");
    private JButton jbReiniciar = new JButton("Limpar tudo");
    private JButton jbCor = new JButton("Cor Traco");

    private JButton jbRotacao = new JButton("Rotacao");
    private JButton jbEscala = new JButton("Escala");
    private JButton jbSalvar = new JButton("Salvar");
    private JButton jbLer = new JButton("Ler");

    private JButton jbSair = new JButton("Sair");

    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: " + String.format("%-5s", 2));
    private JSlider jsEsp = new JSlider(2, 50, 2);

    // Função para configurar a cor de texto e de fundo dos botões

    private void configurarCoresBotoes(JButton[] botoes, Color corTexto, Color corFundo) {
        for (JButton botao : botoes) {
            botao.setForeground(corTexto);
            botao.setBackground(corFundo);
        }
    }

    /**
     * Constroi a GUI
     *
     * @param larg largura da janela
     * @param alt altura da janela
     */
    public Gui(int larg, int alt) {
        super("Testa Primitivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        setResizable(false);

        // Criar um array de todos os botões
        JButton[] botoes = {jbPonto, jbReta, jbTriangulo, jbRetangulo, jbCirculo, jbCarimboFlor, jbCorCarimbo, jbRedesenhar, jbLimpar, jbCor, 
                jbRotacao, jbEscala, jbSalvar, jbSair};
        Graphics g = getGraphics();  

        // Adicionando os componentes
        barraComandos.add(jbPonto);
        barraComandos.add(jbReta);
        barraComandos.add(jbTriangulo);
        barraComandos.add(jbRetangulo);
        barraComandos.add(jbCirculo);
        barraComandos.add(jbCarimboFlor);

        barraComandos.add(jbRedesenhar);
        barraComandos.add(jbLimpar); 
        barraComandos.add(jbCor); 
        barraComandos.add(jbCorCarimbo);

        barraComandos.add(jbContrZ);
        barraComandos.add(jbRemover);
        barraComandos.add(jbReiniciar);

        barraComandos.add(jbRotacao);
        barraComandos.add(jbEscala);
        barraComandos.add(jbSalvar);
        barraComandos.add(jbLer);

        // adiciona os componentes com os respectivos layouts
        barraComandos.add(jlEsp); 
        barraComandos.add(jsEsp); 
        areaDesenho.setEsp(espAtual); 

        barraComandos.add(jbSair); 

        add(barraComandos, BorderLayout.NORTH);                
        add(areaDesenho, BorderLayout.CENTER);                
        add(msg, BorderLayout.SOUTH);

        jbCorCarimbo.addActionListener(e -> {
                    Color novaCor = JColorChooser.showDialog(null, "Escolha uma cor para a circunferencia do CarimboFlor", corCircunferenciaCarimbo);
                    if (novaCor != null) {
                        corCircunferenciaCarimbo  = novaCor;
                        areaDesenho.setcorCircunferencia(corCircunferenciaCarimbo ); // Define a nova cor para o CARIMBOFLOR
                    }
                    Color novaCor2 = JColorChooser.showDialog(null, "Escolha uma cor para a reta do CarimboFlor", corRetaCarimbo);
                    if (novaCor2 != null) {
                        corRetaCarimbo = novaCor2;
                        areaDesenho.setcorTriangulo(corRetaCarimbo); // Define a nova cor para o retângulo do CARIMBOFLOR
                    }
            });

        jbPonto.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.PONTO;
                    areaDesenho.setTipo(tipoAtual);
                    
                    //JOptionPane.showMessageDialog(null, "MODO DE USO:\nUm clique do mouse em um qualquer ponto da tela para desenhar um ponto");
            });

        jbReta.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.RETA;
                    areaDesenho.setTipo(tipoAtual);
                    
                    //JOptionPane.showMessageDialog(null, "MODO DE USO:\nDois cliques do mouse em um qualquer ponto da tela para desenhar uma reta");
            });

        jbTriangulo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.TRIANGULO;
                    areaDesenho.setTipo(tipoAtual);
                    
                    //JOptionPane.showMessageDialog(null, "MODO DE USO:\nTres cliques do mouse em um qualquer ponto da tela para desenhar um triangulo");
            });

        jbRetangulo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.RETANGULO;
                    areaDesenho.setTipo(tipoAtual);
                    
                    //JOptionPane.showMessageDialog(null, "MODO DE USO:\nDois cliques do mouse em um qualquer ponto da tela para desenhar um retangulo");
            }); 

        jbCirculo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.CIRCULO;
                    areaDesenho.setTipo(tipoAtual);
                    
                    JOptionPane.showMessageDialog(null, "MODO DE USO:\nDois cliques do mouse em um qualquer ponto da tela para desenhar um circulo");
            });

        jbCarimboFlor.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.CARIMBOFLOR;
                    areaDesenho.setTipo(tipoAtual);
                    
                    JOptionPane.showMessageDialog(null, "MODO DE USO:\nDois cliques do mouse em um qualquer ponto da tela para desenhar um mandala");
            });

        jbLimpar.addActionListener(e -> {
                    areaDesenho.limparDesenho();
                    
                    JOptionPane.showMessageDialog(null, "Todos os desenhos apagados. Caso os queira por completo, clique em 'redesenhar'");
            });  

        jbRedesenhar.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.REDESENHAR;
                    areaDesenho.setTipo(tipoAtual);
                    paint(g);
                    
                    //JOptionPane.showMessageDialog(null, "Todos os desenhos redesenhados");
            });     

        jbCor.addActionListener(e -> {
                    Color c = JColorChooser.showDialog(null, "Escolha uma cor para o traco", msg.getForeground()); 
                    if (c != null){ 
                        corAtual = c; 
                        areaDesenho.setcor(corAtual);
                        corDeFundoAtual = getContentPane().getBackground(); // Atualiza a cor de fundo atual
                    }
            });  

        jbContrZ.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.CONTRZ;
                    areaDesenho.setTipo(tipoAtual);
                    repaint();
            });

        jbRemover.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.REMOVER;
                    areaDesenho.remover();          
            });

        jbReiniciar.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.REINICIAR;
                    areaDesenho.setTipo(tipoAtual);
                    repaint();
                    
                    JOptionPane.showMessageDialog(null, "Todos os desenhos apagados. Nao e possivel recupera-los");
            });

        jbRotacao.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.ROTACAO;
                    //areaDesenho.rotacionar();
                    areaDesenho.setTipo(tipoAtual);
                    
                    JOptionPane.showMessageDialog(null, "Clique em um ponto qualquer para rotacionar em relacao a esse ponto");
            });

        jbEscala.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.ESCALA;
                    areaDesenho.setTipo(tipoAtual);
                    
                    JOptionPane.showMessageDialog(null, "Clique em um ponto qualquer para escalonar em relacao a esse ponto");
            });

        jbSalvar.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.SALVAR;
                    areaDesenho.salvar(larg,alt);
            });

        jbLer.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.LER;
                    areaDesenho.ler(larg,alt);
                    repaint();
                    
                    JOptionPane.showMessageDialog(null, "Clique em 'redesenhar' para desenhar o arquivo salvo");
            });

        jsEsp.addChangeListener(e -> {
                    espAtual = jsEsp.getValue();
                    jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
                    areaDesenho.setEsp(espAtual);        
            });   

        jbSair.addActionListener(e -> {
                    System.exit(0);
            });        
    }
}
