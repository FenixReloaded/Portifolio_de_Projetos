import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

class Painel implements ActionListener{
    public static void main(String args[]) {
        //Painel painel = new Painel();
        SwingUtilities.invokeLater(() -> new Painel());
    }

    public static JFrame frame;
    private JButton lerButton;
    private JButton cinzaButton;
    private JButton binarioButton;
    private JButton blurButton;
    private JButton convertButton;
    private JButton histogramaButton;
    public static JLabel imagemLabel; // Adiciona um JLabel para exibir a imagem

    Painel() {
        frame = new JFrame("Processamento de Imagens");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 1000);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        lerButton = new JButton("Ler Arquivo");
        lerButton.setBounds(25, 25, 150, 75);
        lerButton.addActionListener(this);

        cinzaButton = new JButton("Padrões de Cinza");
        cinzaButton.setBounds(200, 25, 150, 75);
        cinzaButton.addActionListener(this);

        binarioButton = new JButton("Branco e Preto");
        binarioButton.setBounds(375, 25, 150, 75);
        binarioButton.addActionListener(this);

        blurButton = new JButton("Aplicar Blur");
        blurButton.setBounds(550, 25, 150, 75);
        blurButton.addActionListener(this);

        convertButton = new JButton("Inverter Imagem");
        convertButton.setBounds(725, 25, 150, 75);
        convertButton.addActionListener(this);

        histogramaButton = new JButton("Criar Histograma");
        histogramaButton.setBounds(900, 25, 150, 75);
        histogramaButton.addActionListener(this);

        imagemLabel = new JLabel(); // Cria um JLabel para exibir a imagem
        imagemLabel.setBounds(200, 200, 700, 650);

        //Adiciona uma borda ao redor do JLabel
        // Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // 5 é a largura da borda
        // imagemLabel.setBorder(border);

        //Adiciona uma borda sofisticada com sombreamento ao redor do JLabel
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(1, 1, 1, 1), // Ajusta a largura da sombra
                    BorderFactory.createLineBorder(Color.WHITE, 1)
                )
            );

        imagemLabel.setBorder(border);
        mainPanel.add(lerButton);
        mainPanel.add(cinzaButton);
        mainPanel.add(binarioButton);
        mainPanel.add(blurButton);
        mainPanel.add(convertButton);
        mainPanel.add(histogramaButton);
        mainPanel.add(imagemLabel); // Adiciona o JLabel ao painel principal

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    public static void exibirImagemNaTela(BufferedImage imagem) {

        ImageIcon icon = new ImageIcon(imagem);

        int larguraImagem = icon.getIconWidth();
        int alturaImagem = icon.getIconHeight();

        // Atualiza o tamanho do JLabel com base nas dimensões reais da imagem
        imagemLabel.setBounds(235, 200, larguraImagem, alturaImagem);

        Image yourResizedImage = imagem.getScaledInstance(larguraImagem, alturaImagem, java.awt.Image.SCALE_SMOOTH); 

        ImageIcon iconRedimensionado = new ImageIcon(yourResizedImage);

        imagemLabel.setIcon(iconRedimensionado);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lerButton) {
            lerArquivo();
        } else if (e.getSource() == cinzaButton) {
            padroesDeCinza();
        } else if (e.getSource() == binarioButton) {
            binario();
        } else if (e.getSource() == blurButton) {
            blur();
        } else if (e.getSource() == convertButton) {
            converter();
        } else if (e.getSource() == histogramaButton) {
            histograma();
        }
    }

    private void lerArquivo(){
        BufferedImage img = null;
        File f = null;
        // read image
        try {
            String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo a ser carregado . terminação (ex: teste.png)\nCertifique-se de incluir a extensão do arquivo.");

            if (nomeArquivo != null) {
                f = new File(nomeArquivo);

                if (f.exists() && f.isFile()) {
                    img = ImageIO.read(f);
                    JOptionPane.showMessageDialog(null, "Imagem lida com sucesso!");
                    exibirImagemNaTela(img);
                } else {
                    JOptionPane.showMessageDialog(null, "Arquivo não encontrado. Digite novamente!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "       Voltando ao menu...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo: " + e.getMessage() + "!");
        }
    }

    private void padroesDeCinza(){
        BufferedImage img = null;
        File f = null;

        String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo a ser carregado . terminação (ex: teste.png)\nCertifique-se de incluir a extensão do arquivo.");

        if(nomeArquivo != null){
            //BufferedImage img2 = null;
            //read image
            try{
                f = new File(nomeArquivo);
                img = ImageIO.read(f);
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado. Digite novamente!");
            }

            if(img != null){
                //get image width and height
                int width = img.getWidth();
                int height = img.getHeight();

                //convert to grayscale
                for(int y = 0; y < height; y++){
                    for(int x = 0; x < width; x++){
                        int p = img.getRGB(x,y);

                        int a = (p>>24)&0xff;
                        int r = (p>>16)&0xff;
                        int g = (p>>8)&0xff;
                        int b = p&0xff;

                        //calculate average
                        int avg = (r+g+b)/3;

                        //replace RGB value with avg
                        p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                        img.setRGB(x, y, p);
                    }
                }

                try{
                    String nomeNovoArquivo = JOptionPane.showInputDialog("Digite o nome do novo arquivo que terá o filtro aplicado . png (ex: testenovo.png)\nCertifique-se de incluir a extensão do arquivo.");

                    if(nomeNovoArquivo != null && nomeNovoArquivo.length() != 0){
                        f = new File(nomeNovoArquivo);
                        ImageIO.write(img, "png", f);

                        JOptionPane.showMessageDialog(null, f + " " + "criado com sucesso no diretório!");
                        exibirImagemNaTela(img);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Arquivo não criado. Tente novamente!");
                    }
                }catch(IOException e){
                    JOptionPane.showMessageDialog(null, "Arquivo não criado. Tente novamente!");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "       Voltando ao menu...");
        }
    }

    private void binario(){
        BufferedImage image = null;
        File input = null;

        String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo a ser carregado . terminação (ex: teste.png)\nCertifique-se de incluir a extensão do arquivo.");

        if(nomeArquivo != null){
            try{
                input = new File(nomeArquivo);
                image = ImageIO.read(input);
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado. Digite novamente!");
            }

            if(image != null){
                try {
                    BufferedImage result = new BufferedImage(
                            image.getWidth(),
                            image.getHeight(),
                            BufferedImage.TYPE_BYTE_BINARY);

                    Graphics2D graphic = result.createGraphics();
                    graphic.drawImage(image, 0, 0, Color.WHITE, null);
                    graphic.dispose();

                    String nomeNovoArquivo = JOptionPane.showInputDialog("Digite o nome do novo arquivo que terá o filtro aplicado . png (ex: testenovo.png)\nCertifique-se de incluir a extensão do arquivo.");

                    if(nomeNovoArquivo != null && nomeNovoArquivo.length() != 0){
                        File output = new File(nomeNovoArquivo);
                        ImageIO.write(result, "png", output);

                        JOptionPane.showMessageDialog(null, output + " " + "criado com sucesso no diretório!");
                        exibirImagemNaTela(result);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Arquivo não criado. Tente novamente");
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Arquivo não criado. Tente novamente!");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "       Voltando ao menu...");
        }
    }

    private void blur(){
        try{
            try{
                BlurImage.blurNaImagem();
            }
            catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        catch (InterruptedException ie){
            ie.printStackTrace();
        }     
    }

    private void converter(){
        Convert.convertImagem();
    }

    private void histograma(){
        ImageToHistogram.desenharHistograma();
    }
}
