import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

public class Convert
{
    public static void convertImagem() {
        String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo a ser carregado . terminação (ex: teste.png)\nCertifique-se de incluir a extensão do arquivo.");

        if(nomeArquivo != null){
            invertImage(nomeArquivo);
        }
        else{
            JOptionPane.showMessageDialog(null, "       Voltando ao menu...");
        }
    }

    public static void invertImage(String imageName) {
        BufferedImage inputFile = null;
        File input = null;

        try {
            input = new File(imageName);
            inputFile = ImageIO.read(input);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado. Digite novamente!");
        }

        if(inputFile != null){
            for (int x = 0; x < inputFile.getWidth(); x++) {
                for (int y = 0; y < inputFile.getHeight(); y++) {
                    int rgba = inputFile.getRGB(x, y);
                    Color col = new Color(rgba, true);
                    col = new Color(255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue());
                    inputFile.setRGB(x, y, col.getRGB());
                }
            }

            try {
                String nomeNovoArquivo = JOptionPane.showInputDialog("Digite o nome do novo arquivo que terá ter o filtro aplicado . png (ex: testenovo.png)\nCertifique-se de incluir a extensão do arquivo.");

                if(nomeNovoArquivo != null && nomeNovoArquivo.length() != 0){
                    File outputFile = new File(nomeNovoArquivo);
                    ImageIO.write(inputFile, "png", outputFile);

                    JOptionPane.showMessageDialog(null, outputFile + " " + "criado com sucesso no diretorio!");
                    Painel.exibirImagemNaTela(inputFile);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Arquivo não criado. Tente novamente!");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Arquivo não criado. Tente novamente!");
            }
        }
    }
}