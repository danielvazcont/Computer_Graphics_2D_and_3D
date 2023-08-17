import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class practica17 extends JFrame {
    private Graphicss MyGraphicsInstance;
    private Boolean checkExistance = Boolean.FALSE;

    private BufferedImage buffer;
    public JPanel myJPanel;

    public practica17(){
        super("Algoritmo Scan-Line");
        setSize(500,500);
        setLocationRelativeTo(null);
        //setResizable(false);
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
        

        MyGraphicsInstance.PuntoMedio(100, 150, 200, 250, Color.green);//abj-izq
        MyGraphicsInstance.PuntoMedio(100, 150, 230, 100, Color.green);//arriba-izquierda
        MyGraphicsInstance.PuntoMedio(200, 250, 205, 170, Color.green);//triangulo-izquierda
        MyGraphicsInstance.PuntoMedio(205, 170, 260, 220, Color.green);//triangulo-Derecha
        MyGraphicsInstance.PuntoMedio(230, 100, 259, 220, Color.green);//asta-izquierda
        

        MyGraphicsInstance.ScanLine(150,150, Color.green);
    }
    public static void main(String[] args) {
        practica17 Frame = new practica17();
    }
}
