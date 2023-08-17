import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class practica19 extends JFrame {
    private BufferedImage imagePixel;
    private JLabel status;

    //Regiones de corte
    int DENTRO = 0;     // 0000
    int IZQ = 1;        // 0001
    int DER = 2;        // 0010
    int ABAJO = 4;      // 0100
    int ARRIBA = 8;     // 1000

    //Tamaño de ventana
    int x_min = 100;
    int x_max = 800;
    int y_min = 100;
    int y_max = 520;

    public practica19(){
        setTitle("Recorte de Linea");
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

    //CALCULAR SECTOR PARA UN PUNTO
    public int calcularSector(double x, double y){
        int sector = DENTRO;
 
        if (x < x_min)          // A la izquierda
            sector |= IZQ;
        else if (x > x_max)     // A la derecha
            sector |= DER;
        if (y < y_min)          // Abajo
            sector |= ABAJO;
        else if (y > y_max)     // Arriba
            sector |= ARRIBA;
    
        return sector;
    }

    //recorte
    public void recorteLinea(double x1, double y1, double x2, double y2, Color c){
        
        int sector1 = calcularSector(x1, y1);
        int sector2 = calcularSector(x2, y2);

        boolean aceptar = false;
        
        while (true) {
            if ((sector1 == 0) && (sector2 == 0)) {
                aceptar = true;
                break;
            }
            else if ((sector1 & sector2) != 0) {
                break;
            }
            else {
                int sector_out;
                double x = 0;
                double y = 0;
     
                if (sector1 != 0)
                    sector_out = sector1;
                else
                    sector_out = sector2;
     
                // Encontrar intersección
                if ((sector_out & ARRIBA) != 0) {
                    // Punto arriba
                    x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
                    y = y_max;
                }
                else if ((sector_out & ABAJO) != 0) {
                    // Punto abajo
                    x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
                    y = y_min;
                }
                else if ((sector_out & DER) != 0) {
                    // Punto derecha
                    y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
                    x = x_max;
                }
                else if ((sector_out & IZQ) != 0) {
                    // Punto izquierda
                    y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
                    x = x_min;
                }
     
                // Reemplazamos el punto que está afuera por la intersección
                if (sector_out == sector1) {
                    x1 = x;
                    y1 = y;
                    sector1 = calcularSector(x1, y1);
                }
                else {
                    x2 = x;
                    y2 = y;
                    sector2 = calcularSector(x2, y2);
                }
            }
        }
        if (aceptar) {
            // Dibujamos la linea
            System.out.println("Linea aceptada de (" + (int)x1 + ", " + (int)y1 + ") a (" + (int)x2 + ", " + (int)y2 + ")");
            dibujarLinea((int)x1, (int)y1, (int)x2, (int)y2, c);
        }
        else{
            // Rechazamos la linea
            System.out.println("Linea rechazada");
        }
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


    public static void main(String[] args) {
        double x1 = 550, y1 = 250; //10 10
        double x2 = 10, y2= 250;   //350 350
        practica19 b = new practica19();
        b.recorteLinea(x1, y1, x2, y2, Color.blue);
    }
}
