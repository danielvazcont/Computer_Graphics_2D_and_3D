import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class practica14 extends JFrame implements Runnable{
    private BufferedImage buffer, animacion;
    private Color color, disponible, atrac;

    //private int[] circulo = {0,400,1};
    //nubes
    private int[] elipse = {80,70,1};
    private int [] nublar2= {80,70,1};
    private int[] Sol = {80,60,1};
    private Thread hilo;
    //Edificio 1
    private int[] CuadradoP1 = {100,100,1};//cuadrado{x,y,z}
    private int[] CuadradoP2 = {200,400,1};
    //Edificio 2
    private int[] CuadradoP3 = {250,100,1};//cuadrado{x,y,z}
    private int[] CuadradoP4 = {350,400,1};
    //CIBER TRUCK
    //Cuerpo osea es obvio animal
    private int [] CuerpoP1 = {100,420,1};
    private int [] CuerpoP2 = {250,500,1};
    //Ass
    private int [] ASSP1 = {251,450,1};
    private int [] ASSP2 = {280,500,1};
    //llanta 1
    private int [] circulo = {250,500,1};
    //llanta 2
    private int [] circulo2= {100,500,1};
// termina carro
 //carro 2
 private int [] CuerpoA1 = {70,420,1};
 private int [] CuerpoA2 = {150,500,1};


    public practica14(){
        setTitle("Relleno");
        setSize(500,520);
        setLayout(null);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        animacion = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
        disponible = new Color(0,0,0);
        atrac = new Color(255,255,255);
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        practica14 moc = new practica14();
    }
    public void paint(Graphics g){
        animacion = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        int atras=-1, adelante=1;
        int p_atras=atras-1, p_adelante=adelante+1;
        //traslacionn(CuadradoP1, 2,2);
        //traslacionn(CuadradoP2, 2,2);
//nubes
            //traslacionn(elipse,10,0);
            //dibujarElipse(elipse[0],elipse[1],40,20 );
           // inundacion(elipse[0]+1,elipse[1]+1,Color.CYAN);

//termian nubes
//Comienza edificio
dibujarRectangulo(CuerpoP1[0],CuerpoP1[1],CuerpoP2[0],CuerpoP2[1],Color.CYAN);
        inundacion(CuerpoP1[0]+1,CuerpoP1[1]+1,Color.CYAN);
        traslacionn(CuerpoP1,0,-5);
        traslacionn(CuerpoP2,0,-5);
        dibujarRectangulo(CuadradoP1[0], CuadradoP1[1],CuadradoP2[0],CuadradoP2[1],Color.WHITE);
        inundacion(CuadradoP1[0]+1,CuadradoP1[1]+1, Color.LIGHT_GRAY);
        //edificio2
        dibujarRectangulo(CuadradoP3[0], CuadradoP3[1],CuadradoP4[0],CuadradoP4[1],Color.WHITE);
        inundacion(CuadradoP3[0]+1,CuadradoP3[1]+1, Color.LIGHT_GRAY);
        //termina edificio
        //Comienza carro
        
        dibujarRectangulo(ASSP1[0], ASSP1[1],ASSP2[0],ASSP2[1],Color.CYAN);
        inundacion(ASSP1[0]+1,ASSP1[1]+1,Color.CYAN );
        traslacionn(ASSP1,10,0);
        traslacionn(ASSP2,10,0);
        dibujarCirculo(circulo[0],circulo[1],10 );
        traslacionn(circulo, 10,0);
        //inundacion(circulo[0]-9,circulo[1],Color.BLUE);
        //inundacion(circulo[0]+9,circulo[1],Color.BLUE);
        dibujarCirculo(circulo2[0],circulo2[1],10 );
        traslacionn(circulo2, 10,0);
//termina carro
        //Carro 2
        dibujarRectangulo(CuerpoA1[0],CuerpoA1[1],CuerpoA2[0],CuerpoA2[1],Color.pink );

        //termina carro
        dibujarCirculo(Sol[0],Sol[1],20 );
        inundacion(Sol[0]-19,Sol[1],Color.LIGHT_GRAY );
        inundacion(Sol[0]+19,Sol[1],Color.LIGHT_GRAY );

        this.getGraphics().drawImage(animacion,0,0,this);
    }
    public void traslacionn(int [] vector, int dx,int dy){
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
    }//traslacion

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

    public void dibujarElipse(int xc, int yc, int rx, int ry){
        double x,y,dt;
        int r = rx+ry;
        dt=Math.PI/(r*4);
        for(double t=0; t<=2*Math.PI; t+=dt){
            x = Math.cos(t) * rx + xc;
            y = Math.sin(t) * ry + yc;
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
        }//else

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
            //Para |m|>1
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
            }//while
        }//else
    }//lineabresenham

    //inundacion
    public void inundacion(int x, int y, Color relleno){
        if((x<this.getWidth() && y<this.getHeight()) && (x>0 && y>0)){
            color = leerColorPixel(x,y);
            if(color.equals(disponible)){
                putPixel(x,y,relleno);
                inundacion(x,y+1,relleno);
                inundacion(x+1,y,relleno);
                inundacion(x,y-1,relleno);
//              inundacion(x-1,y,relleno);
            }//if
        }
    }//inundacion

    //lee el color del pixel
    public Color leerColorPixel(int x, int y){
        color = new Color(animacion.getRGB(x, y));
        return color;
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
