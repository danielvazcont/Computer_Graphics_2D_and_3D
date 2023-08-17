import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class practica11 extends JFrame implements KeyListener{

    private BufferedImage buffer;
    private Graphics graPixel;

    public practica11() {

        setTitle("Circulo - Algoritmo Bresenham");
        setResizable(false);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void dibujar(int xc, int yc, int x, int y){

        putPixel(xc + x, yc + y, Color.RED);  
        putPixel(xc - x, yc + y, Color.RED);  
        putPixel(xc + x, yc - y, Color.RED);  
        putPixel(xc - x, yc - y, Color.RED);  
        putPixel(xc + y, yc + x, Color.RED); 
        putPixel(xc - y, yc + x, Color.RED); 
        putPixel(xc + y, yc - x, Color.RED); 
        putPixel(xc - y, yc - x, Color.RED); 

    }

    public void calcularPixeles(int xc, int yc, int R) {

        int x = 0, y = R;
        int d = 3 - 2 * R;
        dibujar(xc, yc, x, y);

        while (y >= x) {

            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else
                d = d + 4 * x + 6;
                dibujar(xc, yc, x, y);

        }

    }
    
    @Override
	public void keyTyped(KeyEvent e)
	{
		System.out.println("The key Typed was: " + e.getKeyChar());
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.isActionKey())
			System.exit(0);
		System.out.println("The key Pressed was: " + e.getKeyChar());
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		System.out.println("The key Released was: " + e.getKeyChar());
	}
    public static void main(String args[]) {
        practica11 b = new practica11();
        
        // Adding the current instance of 'practica11' as the key listener
        b.addKeyListener(b);
    }

}
