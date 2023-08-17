//José Daniel Vázquez Franco.
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;

public class practica1 extends JFrame{
    private BufferedImage buffer;
    private Graphics graPixel;

    //Constructor
    public practica1(){
        setTitle("Recta1_JDVF");
        setResizable(false);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        
    }

    public void algoritmo(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        practica1 ventana = new practica1();
        int x0=100, x1=120;  //x1=140
        int y0=100, y1=200;
        int b,m,y;
        m=(y1-y0)/(x1-x0);
        b=y0-(m*x0);
        
        for(int x = x0; x < x1; x++){
            y=(m*x)+b;
            ventana.algoritmo(x, y, Color.BLACK);
        }
        
    }
}
