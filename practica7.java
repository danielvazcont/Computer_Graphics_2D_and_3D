import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math;

public class practica7 extends JFrame{

    private BufferedImage buffer;
    private Graphics graPixel;

    public practica7(){

        setTitle("Circulo - Coordenadas Cartesianas");
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

        int x;
        double y1, y2;

        for(x = xc - R; x <= xc + R; x = x + 1){
            
            y1 = yc + Math.sqrt(R * R - ((x - xc) * (x - xc)));
            y2 = yc - Math.sqrt(R * R - ((x - xc) * (x - xc))) ;

            putPixel2(x, (int)Math.round(y1), Color.RED);
            putPixel2(x, (int)Math.round(y2), Color.RED);
        }
     
    }

    public static void main(String args[]){
        int xc = 250, yc = 250, R = 90;
        practica7 b = new practica7();
        b.putPixel(xc, yc, R);
    }
    
}
