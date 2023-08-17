import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;

public class practica6 extends JFrame {


    private BufferedImage buffer;
    private Graphics graPixel;

    public practica6(){
        setTitle("Rectangulo_JDVF");
        setResizable(false);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }
    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, Math.round(x), Math.round(y), this);
        
    }

    //Algoritmo para graficar rectangulo
    public void rectangulo(int x0, int y0,int x1, int y1){
        Color color = new Color(0, 0, 0);

        int xM = x0 + y1; 
        int yM = y0 + x1;

        //Punto 1 I-D
        for(int y = y0; y <= yM; y++){
            int x = x0;
            putPixel(x,y,color); 
            putPixel(xM,y,color);
        }

        //Punto 2 I-D
        for(int x = x0; x <= xM; x++){
            int y = y0;
            putPixel(x,yM+2,color);
            putPixel(x,y+4,color);
        }
    }

    public static void main(String[] args) {
        practica6 ventana = new practica6();

        int x0 = 50, y0 = 50;
        int x1 = 200, y1 = 200;

        ventana.rectangulo(x0, y0, x1, y1);
    }
}
