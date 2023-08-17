import java.awt.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.nio.channels.UnsupportedAddressTypeException;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class practica18 extends JFrame{
    private Graphicss MyGraphicsInstance;
    private Boolean checkExistance = Boolean.FALSE;

    private BufferedImage buffer;
    public JPanel myJPanel;

    public practica18(){
        super("Inundacion");
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJPanel = new JPanel();
        add(myJPanel);
        setVisible(true);
        MyGraphicsInstance = new Graphicss(this);
    }

    public void paint(Graphics g){
        if( checkExistance == Boolean.FALSE) {
            buffer = new BufferedImage (1,1,BufferedImage.TYPE_INT_RGB);

            buffer.setRGB(0, 0, Color.blue.getRGB());
            this.getGraphics().drawImage(buffer, getWidth()/2, getHeight()/2, this);

            checkExistance = Boolean.TRUE;
            super.paint(g);
        }
        this.update(g);
    }

    
    public void update(Graphics g){
    
        MyGraphicsInstance.PuntoMedio(100, 150, 200, 250, Color.green);
        MyGraphicsInstance.PuntoMedio(100, 150, 230, 100, Color.green);

        MyGraphicsInstance.PuntoMedio(200, 250, 205, 170, Color.green);
        MyGraphicsInstance.PuntoMedio(205, 170, 260, 220, Color.green);

        MyGraphicsInstance.PuntoMedio(230, 100, 259, 220, Color.green);

        MyGraphicsInstance.FloodFill(150,150, Color.green);
    }
    public static void main(String[] args) {
        
        practica18 Frame = new practica18();
    }
    
}
