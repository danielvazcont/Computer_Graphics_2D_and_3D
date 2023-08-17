import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math;

public class practica10 extends JFrame{
    
    private BufferedImage buffer;
    private Graphics graPixel;

    public practica10(){

        setTitle("Circulo - Punto Medio");
        setResizable(false);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public void putPixel2(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void putPixel(int xc, int yc, int R){
        int x = 0;
        int y = R;

        int pk = 1 - R;
        while(x <= y){
            
            putPixel2(x + xc, y + yc, Color.RED);
            putPixel2(y + yc, x + xc, Color.RED);
            putPixel2(xc + x, yc - y, Color.RED);
            putPixel2(xc - x, yc + y, Color.RED);
            putPixel2(yc - y, xc + x, Color.RED);
            putPixel2(yc + y, xc - x, Color.RED);
            putPixel2(yc - x, xc - y, Color.RED);
            putPixel2(xc - y, yc - x, Color.RED);

            if(pk < 0){
                pk+=2*(x + 1) + 1;
                x++;
            }else{
                pk+=2*(x + 1) + 1 - 2*(y - 1);
                x++;
                y--;
            }
        }
    }

    public static void main(String args[]){
        int xc = 250, yc = 250, R = 90;
        practica10 b = new practica10();
        b.putPixel(xc, yc, R);
    }
    
}
