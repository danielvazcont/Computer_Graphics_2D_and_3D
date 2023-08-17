


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class practica13 extends JFrame {


    private BufferedImage buffer;
    private Graphics graPixel;

    //Constructor
    public practica13(){
        setTitle("DDA_JDVF");
        setResizable(false);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public void algoritmo(int x0, int y0, int x1, int y1, Color color,int tam){
        buffer.setRGB(0, 0, color.getRGB());

        //DDA algoritmo
        int dx = x1 - x0;
        int dy = y1 - y0;

        int steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy); //

        float xinc = (float) dx / steps;
        float yinc =(float) dy / steps;

        float x = x0;
        float y = y0;
        int cont=0,cont2=0;
        int tam2=tam*2;
        this.getGraphics().drawImage(buffer, Math.round(x), Math.round(y), this);
        for(int k = 1; k <= steps; ++k){
            x =  x + xinc;
            y = y + yinc;
            cont++;
            if(cont==tam){
                    if(cont2==tam){
                    cont=0;
                    }else{
                        cont2++;
                    }
            }else{
                this.getGraphics().drawImage(buffer, Math.round(x), Math.round(y), this);
            }

        }
    }

    public static void main(String[] args) {
        practica13 ven = new practica13();

        int x0 = 100, y0 = 100;
        int x1 = 200, y1 = 200;

        ven.algoritmo(x0, y0, x1, y1, Color.BLACK,5);
    }
}
