import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class practica20 extends JFrame {
    private BufferedImage imagePixel;
    private JLabel status;


    //Tamaño de recorte
    int x_min = 100;
    int x_max = 800;
    int y_min = 100;
    int y_max = 520;

    public practica20(){
        setTitle("Recorte de Circunferencias");
        setResizable(true);
		setSize(1000,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        status = new JLabel("Status", JLabel.CENTER);
        getContentPane().add(status, BorderLayout.SOUTH);

        imagePixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        
        dibujarLinea(x_min, y_min, x_max, y_min, Color.BLACK);
        dibujarLinea(x_min, y_min, x_min, y_max, Color.BLACK);
        dibujarLinea(x_max, y_max, x_max, x_min, Color.BLACK);
        dibujarLinea(x_max, y_max, x_min, y_max, Color.BLACK);
        dibujarLinea(x_min, y_min, x_max, y_min, Color.BLACK);
    }

    //CREAR UN PIXEL
    public void dibujarPixel(int x, int y, Color c){
        imagePixel.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(imagePixel, x, y, this);
    }

    //DIBUJAR UNA LINEA
    public void dibujarLinea(int x0, int y0, int x1, int y1, Color c){
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

        for(int k = 1; k <= steps; ++k){
            if(p < 0){
                dibujarPixel(Math.round(x) + 1, Math.round(y), c);
                p = p + A;
            } else {
                dibujarPixel(Math.round(x) + 1, Math.round(y) + 1, c);
                p = p + B;
            }
            x = x + xinc;
            y = y + yinc;
        }
    }
    
    //DIBUJAR UN CIRCULO
    public void dibujarCirculo(int xc, int yc, int R, Color c) {
        double a=2, x=0, h=xc, k=yc,y=R;

        double xf=R/Math.sqrt(2);
        
        while(x <= xf){

        y= Math.sqrt(R*R-x*x);

        if (((int)Math.round(x+h)) > x_min && ((int)Math.round(x+h)) < x_max){
            if ((int)Math.round(y+k) > y_min && (int)Math.round(y+k) < y_max) {
                dibujarPixel((int)Math.round(x+h), (int)Math.round(y+k), Color.blue);
            }
            if((int)Math.round(-y+k) > y_min && (int)Math.round(-y+k) < y_max) {
                dibujarPixel((int)Math.round(x+h), (int)Math.round(-y+k), Color.blue);
            }            
        }

        if ((int)Math.round(-x+h) > x_min && (int)Math.round(-x+h) < x_max) {
            if((int)Math.round(y+k) > y_min && (int)Math.round(y+k) < y_max) {
                dibujarPixel((int)Math.round(-x+h), (int)Math.round(y+k), Color.BLUE);
            }
            if ((int)Math.round(-y+k) > y_min && (int)Math.round(-y+k) < y_max) {
                dibujarPixel((int)Math.round(-x+h), (int)Math.round(-y+k), Color.blue);
            }
        }

        if ((int)Math.round(y+h) > x_min && (int)Math.round(y+h) < x_max) {
            if ((int)Math.round(x+k) > y_min && (int)Math.round(x+k) < y_max) {
                dibujarPixel((int)Math.round(y+h), (int)Math.round(x+k), Color.blue);
            }
            if ((int)Math.round(-x+k) > y_min && (int)Math.round(-x+k) < y_max) {
                dibujarPixel((int)Math.round(y+h), (int)Math.round(-x+k), Color.blue);
            }
        }
        if ((int)Math.round(-y+h) > x_min && (int)Math.round(-y+h) < x_max) {
            if ((int)Math.round(x+k) > y_min && (int)Math.round(x+k) < y_max) {
                dibujarPixel((int)Math.round(-y+h), (int)Math.round(x+k), Color.blue);
            }
            if ((int)Math.round(-x+k) > y_min && (int)Math.round(-x+k) < y_max) {
                dibujarPixel((int)Math.round(-y+h), (int)Math.round(-x+k), Color.blue);
            }
        }
        
        x=x+1;
        }
    }


    public static void main(String[] args) {
        int x1 = 550, y1 = 550, r=120; //150 150 100
        practica20 b = new practica20();
        b.dibujarCirculo(x1, y1, r, Color.blue);
    }
}
