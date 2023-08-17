import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math;

public class practica4 extends JFrame{
    private BufferedImage buffer;
    private Graphics graPixel;

    public practica4(){
        setTitle("Bresenham_JDVF");
        setResizable(false);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    //Funcion del algoritmo de bresenham
    public void algoritmo(int x0, int y0, int x1, int y1, Color color){
        buffer.setRGB(0, 0, color.getRGB());

        this.getGraphics().drawImage(buffer, x0, y0, this);

        int dx = x1 - x0;
        int dy = y1 - y0;

        int A = 2 * dy;
        int B = 2 * dy - 2 * dx;
        int p = 2 * dy - dx;

        int steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);
        float xinc = (float) dx / steps;
        float yinc = (float) dy / steps;

        float x = x0;
        float y = y0;

        for(int j = 1; j <= steps; ++j){
            if(p < 0){
                this.getGraphics().drawImage(buffer, Math.round(x) + 1, Math.round(y), this);
                p = p + A;
            } else {
                this.getGraphics().drawImage(buffer, Math.round(x) + 1, Math.round(y) + 1, this);
                p = p + B;
            }
            x = x + xinc;
            y = y + yinc;
        }
    }

    public static void main(String[] args) {
        practica4 ventana = new practica4();
        int x0 = 100, y0 = 100;
        int x1 = 105, y1 = 200;
        ventana.algoritmo(x0, y0, x1, y1, Color.BLACK);
    }
}