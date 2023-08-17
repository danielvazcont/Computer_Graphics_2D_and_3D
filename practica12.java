import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math;

public class practica12 extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    public practica12() {

        setTitle("Practica Lineas");
        setResizable(false);
        setSize(900, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public void lineasPixel(int x0, int y0, int x1, int y1, Color c) {

        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x0, y0, this);

        int dx, dy, A, B, p, steps;
        float xinc, yinc, x, y;

        dx = x1 - x0;
        dy = y1 - y0;

        A = 2 * dy;
        B = 2 * dy - 2 * dx;
        p = 2 * dy - dx;

        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }

        xinc = (float) dx / steps;
        yinc = (float) dy / steps;

        x = x0;
        y = y0;

        for (int k = 1; k <= steps; ++k) {
            if (p < 0) {
                this.getGraphics().drawImage(buffer, Math.round(x) + 1, Math.round(y), this);
                p = p + A;
            } else {
                this.getGraphics().drawImage(buffer, Math.round(x) + 1, Math.round(y) + 1, this);
                p = p + B;
            }
            x = x + xinc;
            y = y + yinc;
        }
    }

    public void circulosPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void circuloPuntoMedio(int xc, int yc, int R) {
        int x = R, y = 0;

        circulosPixel((x + xc), (y + yc), Color.red);
        if (R > 0) {
            circulosPixel((x + xc), (-y + yc), Color.red);
            circulosPixel((y + xc), (x + yc), Color.red);
            circulosPixel((-y + xc), (x + yc), Color.red);

        }
        int P = 1 - R;
        while (x > y) {
            y++;
            if (P <= 0)
                P = P + 2 * y + 1;
            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }
            if (x < y)
                break;
            circulosPixel((x + xc), (y + yc), Color.blue);
            circulosPixel((-x + xc), (y + yc), Color.blue);
            circulosPixel((x + xc), (-y + yc), Color.blue);
            circulosPixel((-x + xc), (-y + yc), Color.blue);
            if (x != y) {
                circulosPixel((y + xc), (x + yc), Color.blue);
                circulosPixel((-y + xc), (x + yc), Color.blue);
                circulosPixel((y + xc), (-x + yc), Color.blue);
                circulosPixel((-y + xc), (-x + yc), Color.blue);
            }
        }
    }

    
    public void elipsePixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    
    public void Elipse(int h, int k, float a, float b){
    
        float theta=0, thetaend,x,y, step=(float) 0.010;
        thetaend=(float) ((Math.PI*90)/180);
        while(theta<thetaend){
            x=(float) (a*Math.cos(theta));
            y=(float) (b*Math.sin(theta));
            theta+=step;
            elipsePixel( (int)Math.round(x+h),  (int)Math.round(y+k), Color.BLACK);
            elipsePixel( (int)Math.round(-x+h),  (int)Math.round(-y+k), Color.BLACK);
            elipsePixel( (int)Math.round(x+h),  (int)Math.round(-y+k), Color.BLACK);
            elipsePixel( (int)Math.round(-x+h),  (int)Math.round(y+k), Color.BLACK);

        }
    }


    public void crearRectangulo(int x1, int y1, int alt, int an){

        int x11 = x1 + an; 
        int y11 = y1 + alt;

        
        for(int y = y1; y <= y11; y++){

            int x = x1;
            circulosPixel(x, y, Color.orange); //LINEA IZQUIERDA
            circulosPixel(x11, y, Color.orange); // LINEA DERECHA

        }

        for(int x = x1; x <= x11; x++){

            int y = y1;
            circulosPixel(x, y11, Color.ORANGE); // LINEA INFERIOR
            circulosPixel(x, y + 1, Color.orange); //LINEA SUPERIOR

        }
    }

    public void crearRectanguloInverso(int x1, int y1, int alt, int an){

        int x11 = x1 + an; // 450
        int y11 = y1 + alt; //325

        //y = 325   Mientras 325 <= 275, se repite 50 veces
        for(int y = y11 ; y >= y1; y--){

            int x = x1;
            circulosPixel(x, y, Color.orange); //LINEA IZQUIERDA
            circulosPixel(x11, y, Color.orange); // LINEA DERECHA

        }

        //x = 450 Mientras 450 >= 300, se repite 150 veces
        for(int x = x11; x >= x1; x--){

            int y = y1;
            circulosPixel(x, y11, Color.orange); // LINEA INFERIOR
            circulosPixel(x, y + 1, Color.orange); //LINEA SUPERIOR

        }
    }


    public static void main(String[] args) {

        practica12 b = new practica12();

        b.lineasPixel(200, 130, 270, 180, Color.red); // Pendiente negativa
        b.lineasPixel(330, 155, 410, 155, Color.RED); // Izquierda a derecha
        b.lineasPixel(520, 131, 460, 185, Color.RED); // Pendiente positiva
        b.lineasPixel(680, 157, 600, 157, Color.red); // Derecha a izquierda

        b.circuloPuntoMedio(100, 300, 60);
        b.circuloPuntoMedio(100, 300, 40);
        b.circuloPuntoMedio(100, 300, 20);
        b.circuloPuntoMedio(100, 300, 5);

        b.Elipse(700, 300, 120, 45);
        b.Elipse(700,300,100,35);
        b.Elipse(700,300,80,25);
        b.Elipse(700,300,50,7);
        
        b.crearRectangulo(250, 250, 100, 250);
        b.crearRectanguloInverso(300, 275, 50, 150);

    }

}
