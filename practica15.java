import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class practica15 extends JFrame implements  Runnable{

    private BufferedImage buffer, animacion;
    private Color color, disponible;
    private Thread hilo;
    private int[] muvtruck1 = {100,100,1};
    private int[] movbacc2 = {200,200,1};
    public practica15(){
        setTitle("Escalamiento");
        setSize(800,800);
        setLayout(null);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        animacion = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
        disponible = new Color(0,0,0);
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);
    }
    public static void main(String[] args){
        new practica15();
    }
    public void paint(Graphics g){
        animacion = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

        Escalar(muvtruck1, 5,5);
        dibujarRectangulo(muvtruck1[0], muvtruck1[1],movbacc2[0],movbacc2[1],Color.MAGENTA);

        this.getGraphics().drawImage(animacion,0,0,this);


    }
    public void Escalar(int [] vector, int dx,int dy){
        int r[]={0,0,0};
        int [] P = {vector[0], vector[1],vector[2]};
        int [][] T = {{1,0,dx},
                {0,1,dy},
                {0,0,1}};
        int i,j;
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                r[i] += P[j]*T[i][j];
            }
        }
        vector[0]=r[0];
        vector[1]=r[1];
        vector[2]=r[2];
    }
    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        animacion.getGraphics().drawImage(buffer, x,y,this);
    }

    public void dibujarRectangulo(int x0,int y0, int x1, int y1, Color c){
        linea_bresenham(x0,y0,x1,y0,c);
        linea_bresenham(x0,y1,x1,y1,c);
        linea_bresenham(x0,y0,x0,y1,c);
        linea_bresenham(x1,y0,x1,y1,c);
    }
    public void dibujarCirculo(int xc, int yc, int r){
        double x,y,dt;
        dt= Math.PI/(r*4);
        for(double t=0; t<=2*Math.PI; t+=dt) {
            x = Math.cos(t) * r + xc;
            y = Math.sin(t) * r +yc;
            putPixel((int)(Math.round(x)),(int)(Math.round(y)),Color.RED);
        }
    }
    public void linea_bresenham(int x0,int y0, int x1, int y1, Color c){
        int x, y, dx, dy, P, A,B,stepx,stepy;
        dx= x1-x0;
        dy= y1-y0;
        if(dy<0){
            dy = -dy;
            stepy = -1;
        }else{
            stepy = 1;
        }

        if(dx<0){
            dx = -dx;
            stepx = -1;
        }else{
            stepx = 1;
        }//else

        x=x0;
        y=y0;
        putPixel(x,y,c);

        //se cicla hasta llegar al extremo de la linea

        if(dx>dy){
            P = 2*dy -dx;
            A = 2*dy;
            B = 2*(dy-dx);
            while (x!= x1){
                x = x + stepx;
                if(P<0){
                    P = P + A;
                }else{
                    y = y + stepy;
                    P = P + B;
                }//else
                putPixel(x,y,c);
            }//while
        }else{

            P = 2*dx - dy;
            A = 2*dx;
            B = 2*(dx-dy);
            while(y!=y1){
                y = y + stepy;
                if(P<0){
                    P = P + A;
                }else{
                    x = x +stepx;
                    P = P + B;
                }
                putPixel(x,y,c);
            }
        }
    }


    @Override
    public void run() {
        while(true){
            try{
                repaint();
                hilo.sleep(100);
            }catch(InterruptedException ex){

            }
        }
    }
}
