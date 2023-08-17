import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math;

public class practica16 extends JFrame{

    private static BufferedImage buffer;
    private static Graphics graPixel;

    public practica16(){

        setTitle("Circulo - Grozor");
        setResizable(false);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
    }

    public void putPixel2(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void putPixel(int xc, int yc, double R,int t){
        buffer = new BufferedImage(t, t, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        double a=2, x=0, h=xc, k=yc,y=R;

        double xf=R/Math.sqrt(2);
        
        while(x <= xf){

        y= Math.sqrt(R*R-x*x);
        putPixel2((int)Math.round(x+h), (int)Math.round(y+k), Color.black);
        putPixel2((int)Math.round(-x+h), (int)Math.round(y+k), Color.black);
        putPixel2((int)Math.round(x+h), (int)Math.round(-y+k), Color.black);
        putPixel2((int)Math.round(-x+h), (int)Math.round(-y+k), Color.black);
        putPixel2((int)Math.round(y+h), (int)Math.round(x+k), Color.black);
        putPixel2((int)Math.round(-y+h), (int)Math.round(x+k), Color.black);
        putPixel2((int)Math.round(y+h), (int)Math.round(-x+k), Color.black);
        putPixel2((int)Math.round(-y+h), (int)Math.round(-x+k), Color.BLACK);
        x=x+1;
        }
    
    }

    public static void main(String args[]){
        int xc = 250, yc = 250, R = 90, t=10;
        practica16 b = new practica16();
        b.putPixel(xc, yc, R, t);
    }
    
}
