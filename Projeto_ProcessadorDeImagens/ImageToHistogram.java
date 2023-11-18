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

public class ImageToHistogram
{
    public static double getIntensity(Color color) // this method produces monochrome luminance
    {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return 0.299*r + 0.587*g + 0.114*b;
    }

    public static double[][] convertPictureToArray(Picture picture)
    {
        int width = picture.width();
        int height = picture.height();
        double[][] pixels = new double[width][height];      
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col,row);
                pixels[col][row] = getIntensity(color);
            }
        }
        return pixels;
    }

    public static void drawHistogram(double[][] a)
    {
        int m = a.length;
        int n = a[0].length;
        int pixels = m*n;
        int[] histogram = new int[256];
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        for (double[] currentRow : a) {
            for (int j = 0; j < n; j++) {
                int intensity = (int) currentRow[j];
                histogram[intensity]++;
            }
        }
        int max = 0;
        for (int i = 0; i < 256; i++){
            max = Math.max(max,histogram[i]);
        }
        StdDraw.setXscale(0,256);
        StdDraw.setYscale(0,max);
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < 256; i++) {
            int half = histogram[i] / 2;
            StdDraw.filledRectangle(i + 0.5, half, 0.5, half);
        }
        StdDraw.show();
    }

    public static void desenharHistograma()
    {
        String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo para fazer o histograma . terminação (ex: teste.png)\nCertifique-se de incluir a extensão do arquivo.");

        BufferedImage img = null;
        File f = null;

        if(nomeArquivo != null){
            try{
                f = new File(nomeArquivo);
                img = ImageIO.read(f);
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado. Digite novamente!");
            }

            if(img != null){
                Picture picture = new Picture(nomeArquivo);
                double[][] a = convertPictureToArray(picture);
                drawHistogram(a);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "       Voltando ao menu...");
        }
    }
}