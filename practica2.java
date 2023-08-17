import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math;


public class practica2 extends JFrame{
    private BufferedImage buffer;
    private Graphics graPixel;

    //Constructor
    public practica2(){
        setTitle("Recta2Mejorada_JDVF");
        setResizable(false);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    //Graficar punto
    public void algoritmo(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    //Establecer los puntos
    public void dibujarlinea(float x0, float y0, float x1, float y1){
        Color color = new Color(0, 0, 0);
        float ax,ay;
        if(x0>x1 && y0>y1){
            ax=x0; x0=x1; x1=ax;
            ay=y0; y0=y1; y1=ay; }

        //Escuacion de la recta
        float dx = (float)x1 - x0;
        float dy = (float)y1 - y0;

        float m = (dy)/(dx);
        float b = y0-(m*x0);

        //Si la division es entre 0
        if(dx == 0){

            if(y0 > y1){
                for(int y = (int)y0; y > (int)y1 ; y--)
                    algoritmo((int)x0, y, color);
            }
            else{
                for(int y = (int)y0; y < (int)y1; y++)
                    algoritmo((int)x0, y, color);
            }
        }
        
        //Negativo
        else if((x0 > x1) && (y0 <= y1)){
            dy = y0 - y1;
            dx = x0 - x1;

            m = (dy)/(dx);
            b = y1-(m*x1);
            float y= 0;

            for(int x = (int)x1; x< x0; x++){
                y = Math.round((m*x) + b);
                algoritmo(x, (int)y, color);
            }
        }
        //Si m es mayor a 1
        else if(Math.abs(m) < 1){
            m = (dy)/(dx);
            b = y0-(m*x0);
            float x = 0;

        for(int y = (int)y1 ; y < (int)y0; y++){
            //Despeje
            x = (y-b)/m;
            algoritmo((int)x, y, color);
        }

    }
        else{
            for(int x = (int)x0; x <= x1; x++){
                int y = Math.round(m * x + b);
                algoritmo(x,y,color);
            }
        }
    }

    public static void main(String[] args) {
        practica2 vent = new practica2();

        int x0= 100, y0= 100; //ejemplo anterior x1=120
        int x1= 120, y1= 200;

        vent.dibujarlinea(x0, y0, x1, y1);
    }

}