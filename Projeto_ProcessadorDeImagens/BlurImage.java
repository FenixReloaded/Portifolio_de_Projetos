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

public class BlurImage {
    Color c[];

    BufferedImage im = null;
    File f = null;

    String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo a ser carregado . terminação (ex: teste.png)\nCertifique-se de incluir a extensão do arquivo.");

    BlurImage() throws IOException, InterruptedException {
        if(nomeArquivo != null){
            try{
                f = new File(nomeArquivo);
                im = ImageIO.read(f);
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado. Digite novamente!");
            }

            if(im != null){
                BufferedImage bi = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_RGB);
                int i = 0;
                int max = 49, radius = 3;
                int a1 = 0, r1 = 0, g1 = 0, b1 = 0,t=0;
                c = new Color[max];
                float xx[] = {1,1,1,1,1,1,1,
                        1,3,3,3,3,3,1,
                        1,3,4,4,4,3,1,
                        1,3,4,15,4,3,1,
                        1,3,4,4,4,3,1,
                        1,3,3,3,3,3,1,
                        1,1,1,1,1,1,1,

                    };
                float h=0;
                for(t=0;t<xx.length;t++){
                    h+=xx[t];
                }
                //System.out.println(h);
                int x = 1, y = 1, x1, y1, ex = 5, d = 0, ll = 0;
                for (x = radius;x < im.getHeight()- radius; x++) {
                    for (y = radius; y < im.getWidth() - radius; y++) {

                        for (x1 = x - radius; x1 <= x + radius; x1++) {
                            for (y1 = y - radius; y1 <= y + radius; y1++) {
                                c[i] = new Color(im.getRGB(y1, x1));
                                //System.out.println(i);
                                //ll+=xx[i];
                                //System.out.println(ll);
                                i++;

                            }
                        }
                        i = 0;
                        ll = 0;
                        for (d = 0; d < max; d++) {
                            float o = xx[d] * c[d].getAlpha();
                            a1 = (int) (a1 + o);
                        }
                        a1 = (int) (a1 / h);

                        for (d = 0; d < max; d++) {
                            float o = xx[d] * c[d].getRed();
                            r1 = (int) (r1 + o);
                        }
                        r1 = (int) (r1 / h);

                        for (d = 0; d < max; d++) {
                            float o = xx[d] * c[d].getGreen();
                            g1 = (int) (g1 + o);
                        }
                        g1 = (int) (g1 / h);
                        //System.out.println(g1);
                        for (d = 0; d < max; d++) {
                            float o = xx[d] * c[d].getBlue();
                            //System.out.println(o);
                            b1 = (int) (b1 + o);
                        }
                        b1 = (int) (b1 / h);
                        int sum1 = (r1 << 16) + (g1 << 8) + b1;
                        bi.setRGB(y, x, sum1);
                        r1 = g1 = b1 = 0;
                        //System.out.println(new Color(sum1));
                    }
                }

                String nomeNovoArquivo = JOptionPane.showInputDialog("Digite o nome do novo arquivo que terá ter o filtro aplicado . png (ex: testenovo.png)\nCertifique-se de incluir a extensão do arquivo.");

                if(nomeNovoArquivo != null && nomeNovoArquivo.length() != 0){
                    File f2 = new File(nomeNovoArquivo);
                    ImageIO.write(bi, "png", f2);

                    JOptionPane.showMessageDialog(null, f2 + " " + "criado com sucesso no diretorio!");
                    Painel.exibirImagemNaTela(bi);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Arquivo não criado. Tente novamente!");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "       Voltando ao menu...");
        }
    }

    public static void blurNaImagem() throws IOException, InterruptedException {
        new BlurImage();
    }
}
