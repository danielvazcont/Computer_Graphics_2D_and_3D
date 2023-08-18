import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class project extends JFrame implements Runnable, KeyListener{
    private BufferedImage buffer, animacion;
    private Color color, disponible, atrac;

    File tick = new File("bit.wav");

    int xf=0, yf=0, xa=0, contador=0, sum1=2, sum2=7,segundoC=0;

    private Thread hilo;
    
    //Cohete
    //cuadrado
    private int [] CuerpoP1 = {100,100,1}; //posicion 1
    private int [] CuerpoP2 = {220,130,1}; //posicion 2
    //triangulo
    private int [] CuerpoP3 = {105,80,1};//a
    private int [] CuerpoP4 = {105,99,1};//b
    private int [] CuerpoP5 = {150,99,1};//c
    //triangulo 2
    private int [] CuerpoP6 = {105,131,1};//a
    private int [] CuerpoP7 = {105,150,1};//b
    private int [] CuerpoP8 = {150,131,1};//c
    //circulos
    private int [] CuerpoP9 = {200,115,1};//ventana
    private int [] sol= {770,200,1};
    //triangulo delante
    private int [] CuerpoP10 = {221,100,1};//a
    private int [] CuerpoP11 = {221,130,1};//b
    private int [] CuerpoP12 = {260,115,1};//c
    //triangulo fuego1
    private int [] CuerpoPF1 = {80,105,1};//a
    private int [] CuerpoPF2 = {80,110,1};//b
    private int [] CuerpoPF3 = {98,107,1};//c
    //triangulo fuego2
    private int [] CuerpoPF4 = {80,112,1};//a
    private int [] CuerpoPF5 = {80,117,1};//b
    private int [] CuerpoPF6 = {98,115,1};//c
    //triangulo fuego3
    private int [] CuerpoPF7 = {80,119,1};//a
    private int [] CuerpoPF8 = {80,124,1};//b
    private int [] CuerpoPF9 = {98,121,1};//c

    //Asteroide
    private int [] asteroide1 = {900,540,1};//met
    private int [] asteroide2 = {950,500,1};//met
    //estrellas
    private int [] estresha1 = {300,300,1};//estresha
    private int [] estresha2 = {550,70,1};//estresha
    private int [] estresha3 = {100,50,1};//estresha
    private int [] estresha4 = {600,600,1};//estresha
    private int [] estresha5 = {200,500,1};//estresha
    private int [] estresha6 = {120,600,1};//estresha
    private int [] estresha7 = {30,200,1};//estresha
    private int [] estresha8 = {340,100,1};//estresha
    private int [] estresha9 = {520,300,1};//estresha
    private int [] estresha10 = {100,400,1};//estresha
    private int [] estresha11 = {600,200,1};//estresha
    private int [] estresha12 = {750,50,1};//estresha
    private int [] estresha13 = {650,470,1};//estresha
    private int [] estresha14 = {450,450,1};//estresha
    private int [] estresha15 = {730,350,1};//estresha
    private int [] estresha16 = {770,650,1};//estresha
    private int [] estresha17 = {350,600,1};//estresha
    private int [] estresha18 = {795,480,1};//estresha



    //rotacion
    private double[] rotacion = {80, 40, 1};
    private int[] traslacion = {770, 200, 1};
    private int[] escalar = {60, 60, 1};
    //rotacion
    private double[] rotacion2 = {80, 40, 1};
    private int[] traslacion2 = {770, 200, 1};
    private int[] escalar2 = {40, 60, 1};

    public project(){
        setTitle("Proyecto 2_José Daniel Vázquez Franco.");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addKeyListener(this);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        animacion = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
        disponible = new Color(0,0,0);
        atrac = new Color(255,255,255);
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);
        sonido(tick);
    }
    //principal
    public static void main(String[] args) {
        project moc = new project();
    }
    //sonido
    static void sonido(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e){
            System.out.println("Error Audio");
        }
    }


    public void paint(Graphics g){
        animacion = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        //variables
        contador++;
        //Todo por una nave wow 
        
        //ventana
        //dibujarCirculo(CuerpoP9[0],CuerpoP9[1],10,Color.white);
        //inundacion(CuerpoP9[0]+1,CuerpoP9[1]+1,Color.white);
        //traslacionn(CuerpoP9,xf,yf);
        //Comienza cohete cuadrado
        dibujarRectangulo(CuerpoP1[0],CuerpoP1[1],CuerpoP2[0],CuerpoP2[1],Color.white);
        inundacion(CuerpoP1[0]+1,CuerpoP1[1]+1,Color.gray);
        traslacionn(CuerpoP1,xf,yf);
        traslacionn(CuerpoP2,xf,yf);
        //ala arriba
        dibujarTrangulo(CuerpoP3[0],CuerpoP3[1],CuerpoP4[0],CuerpoP4[1],CuerpoP5[0],CuerpoP5[1],Color.white);
        inundacion(CuerpoP3[0]+1,CuerpoP3[1]+1,Color.red);
        traslacionn(CuerpoP3,xf,yf);
        traslacionn(CuerpoP4,xf,yf);
        traslacionn(CuerpoP5,xf,yf);
        //ala abajo
        dibujarTrangulo(CuerpoP6[0],CuerpoP6[1],CuerpoP7[0],CuerpoP7[1],CuerpoP8[0],CuerpoP8[1],Color.white);
        inundacion(CuerpoP6[0]+1,CuerpoP6[1]+1,Color.red);
        traslacionn(CuerpoP6,xf,yf);
        traslacionn(CuerpoP7,xf,yf);
        traslacionn(CuerpoP8,xf,yf);
        //punta
        dibujarTrangulo(CuerpoP10[0],CuerpoP10[1],CuerpoP11[0],CuerpoP11[1],CuerpoP12[0],CuerpoP12[1],Color.white);
        inundacion(CuerpoP10[0]+1,CuerpoP10[1]+1,Color.red);
        traslacionn(CuerpoP10,xf,yf);
        traslacionn(CuerpoP11,xf,yf);
        traslacionn(CuerpoP12,xf,yf);
        //fuego1
        dibujarTrangulo(CuerpoPF1[0],CuerpoPF1[1],CuerpoPF2[0],CuerpoPF2[1],CuerpoPF3[0],CuerpoPF3[1],Color.red);
        inundacion(CuerpoPF1[0]+1,CuerpoPF1[1]+1,Color.orange);
        traslacionn(CuerpoPF1,xf,yf);
        traslacionn(CuerpoPF2,xf,yf);
        traslacionn(CuerpoPF3,xf,yf);
        //fuego2
        dibujarTrangulo(CuerpoPF4[0],CuerpoPF4[1],CuerpoPF5[0],CuerpoPF5[1],CuerpoPF6[0],CuerpoPF6[1],Color.red);
        inundacion(CuerpoPF4[0]+1,CuerpoPF4[1]+1,Color.orange);
        traslacionn(CuerpoPF4,xf,yf);
        traslacionn(CuerpoPF5,xf,yf);
        traslacionn(CuerpoPF6,xf,yf);
        //fuego3
        dibujarTrangulo(CuerpoPF7[0],CuerpoPF7[1],CuerpoPF8[0],CuerpoPF8[1],CuerpoPF9[0],CuerpoPF9[1],Color.red);
        inundacion(CuerpoPF7[0]+1,CuerpoPF7[1]+1,Color.orange);
        traslacionn(CuerpoPF7,xf,yf);
        traslacionn(CuerpoPF8,xf,yf);
        traslacionn(CuerpoPF9,xf,yf);

        //estrella1
        dibujarEstrella(estresha1[0],estresha1[1],Color.gray);
        inundacion(estresha1[0]+1,estresha1[1]+1,Color.white);
        //estrella2
        dibujarEstrella(estresha2[0],estresha2[1],Color.gray);
        inundacion(estresha2[0]+1,estresha2[1]+1,Color.white);
        //estrella3
        dibujarEstrella(estresha3[0],estresha3[1],Color.gray);
        inundacion(estresha3[0]+1,estresha3[1]+1,Color.white);
        //estrella4
        dibujarEstrella(estresha4[0],estresha4[1],Color.gray);
        inundacion(estresha4[0]+1,estresha4[1]+1,Color.white);
        //estrella5
        dibujarEstrella(estresha5[0],estresha5[1],Color.gray);
        inundacion(estresha5[0]+1,estresha5[1]+1,Color.white);
        //estrella6
        dibujarEstrella(estresha6[0],estresha6[1],Color.gray);
        inundacion(estresha6[0]+1,estresha6[1]+1,Color.white);
        //estrella7
        dibujarEstrella(estresha7[0],estresha7[1],Color.gray);
        inundacion(estresha7[0]+1,estresha7[1]+1,Color.white);
        //estrella8
        dibujarEstrella(estresha8[0],estresha8[1],Color.gray);
        inundacion(estresha8[0]+1,estresha8[1]+1,Color.white);
        //estrella9
        dibujarEstrella(estresha9[0],estresha9[1],Color.gray);
        inundacion(estresha9[0]+1,estresha9[1]+1,Color.white);
        //estrella10
        dibujarEstrella(estresha10[0],estresha10[1],Color.gray);
        inundacion(estresha10[0]+1,estresha10[1]+1,Color.white);
        //estrella11
        dibujarEstrella(estresha11[0],estresha11[1],Color.gray);
        inundacion(estresha11[0]+1,estresha11[1]+1,Color.white);
        //estrella12
        dibujarEstrella(estresha12[0],estresha12[1],Color.gray);
        inundacion(estresha12[0]+1,estresha12[1]+1,Color.white);
        //estrella13
        dibujarEstrella(estresha13[0],estresha13[1],Color.gray);
        inundacion(estresha13[0]+1,estresha13[1]+1,Color.white);
        //estrella14
        dibujarEstrella(estresha14[0],estresha14[1],Color.gray);
        inundacion(estresha14[0]+1,estresha14[1]+1,Color.white);
        //estrella15
        dibujarEstrella(estresha15[0],estresha15[1],Color.gray);
        inundacion(estresha15[0]+1,estresha15[1]+1,Color.white);
        //estrella16
        dibujarEstrella(estresha16[0],estresha16[1],Color.gray);
        inundacion(estresha16[0]+1,estresha16[1]+1,Color.white);
        //estrella17
        dibujarEstrella(estresha17[0],estresha17[1],Color.gray);
        inundacion(estresha17[0]+1,estresha17[1]+1,Color.white);
        //estrella18
        dibujarEstrella(estresha18[0],estresha18[1],Color.gray);
        inundacion(estresha18[0]+1,estresha18[1]+1,Color.white);
        //asteroide2
        dibujarAsteroide2(asteroide2[0],asteroide2[1],Color.gray);
        inundacion(asteroide2[0]+1,asteroide2[1]+1,Color.decode("#797776"));
        traslacionn(asteroide2, xa,0);
        //asteroide
        dibujarAsteroide(asteroide1[0],asteroide1[1],Color.white);
        inundacion(asteroide1[0]+1,asteroide1[1]+1,Color.decode("#AFA299"));
        traslacionn(asteroide1, xa,0);
       
        //sol
        dibujarCirculo(sol[0],sol[1],50,Color.white);
        inundacion(sol[0]-1,sol[1]+1,Color.yellow);
        //alo
        Elipse(traslacion[0], traslacion[1], rotacion[0], 50);
        rotacion(2);
        Elipse(traslacion2[0], traslacion2[1], rotacion2[0], 50);
        rotacion2(6);
        this.getGraphics().drawImage(animacion,0,0,this);
    }
    

    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        animacion.getGraphics().drawImage(buffer, x,y,this);
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        System.out.println("tecla precianada: "+ e.getKeyChar());
            char tecla = e.getKeyChar();
            
            if(tecla=='d'){
                xf=10;
                yf=0;
                //System.out.println("Derecha: "+ xf);;
                repaint();
            }
            if(tecla=='a'){
                xf=-10;
                yf=0;
                //System.out.println("Izquierda: "+xf);;
                repaint();
            }
            if(tecla=='s'){
                yf=10;
                xf=0;
                //System.out.println("Abajo: "+yf);;
                repaint();
            }
            if(tecla=='w'){
                xf=0;
                yf=-10;
                //System.out.println("Arriba: "+yf);;
                repaint();
            }
        
    }
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void run() {
        while(true){
            try{
                yf=0;
                xf=0;
                xa=-5;
                //System.out.println(contador);
                if(contador==30 || contador==60 || contador==90 || contador==120 || contador==150 || contador==180){
                    sum1=4; sum2=14;
                }else{
                    sum1=2; sum2=7;
                }
                if(contador>=200){
                    segundoC++;
                    contador=0;
                    dibujarAsteroide(asteroide1[0],asteroide1[1],Color.white);
                    inundacion(asteroide1[0]+1,asteroide1[1]+1,Color.decode("#AFA299"));
                    traslacionn(asteroide1, 1000,0);
                    if(segundoC==2){
                        segundoC=0;
                        dibujarAsteroide2(asteroide2[0],asteroide2[1],Color.white);
                        inundacion(asteroide2[0]+1,asteroide2[1]+1,Color.decode("#797776"));
                        traslacionn(asteroide2, 2000,0);
                    }
                }
                yf=0;
                xf=0;
                repaint();
                
                hilo.sleep(100);
            }catch(InterruptedException ex){

            }
        }
    }
























    //funciones para algoritmos
    //rectangulos sum1=2 sum2=7
    public void dibujarEstrella(int x0,int y0, Color c){
        int a1 = x0,     a2=x0+sum1, a3=x0+sum2 ,a4=x0+sum1  ,a5=x0   ,a6=x0-sum1, a7=x0-sum2, a8=x0-sum1;
        int b1 = y0-sum2,   b2=y0-sum1, b3=y0   ,b4=y0+sum1  ,b5=y0+sum2 ,b6=y0+sum1, b7=y0,   b8=y0-sum1;
        linea_bresenham(a1,b1,a2,b2,c);//A1-A2
        linea_bresenham(a2,b2,a3,b3,c);//A2-A3
        linea_bresenham(a3,b3,a4,b4,c);//A3-A4
        linea_bresenham(a4,b4,a5,b5,c);//A4-A5
        linea_bresenham(a5,b5,a6,b6,c);//A5-A6
        linea_bresenham(a6,b6,a7,b7,c);//A6-A7
        linea_bresenham(a7,b7,a8,b8,c);//A7-A8
        linea_bresenham(a8,b8,a1,b1,c);//A8-A1
    }
     //rectangulos
     public void dibujarAsteroide(int x0,int y0, Color c){
        int a1 = x0,     a2=x0+20, a3=x0+25 ,a4=x0+25 ,a5=x0+15    ,a6=x0-20,  a7=x0-20,  a8=x0-15;
        int b1 = y0-25,  b2=y0-20, b3=y0-15  ,b4=y0+15  ,b5=y0+20   ,b6=y0+15,   b7=y0-15,   b8=y0-20;
        linea_bresenham(a1,b1,a2,b2,c);//A1-A2
        linea_bresenham(a2,b2,a3,b3,c);//A2-A3
        linea_bresenham(a3,b3,a4,b4,c);//A3-A4
        linea_bresenham(a4,b4,a5,b5,c);//A4-A5
        linea_bresenham(a5,b5,a6,b6,c);//A5-A6
        linea_bresenham(a6,b6,a7,b7,c);//A6-A7
        linea_bresenham(a7,b7,a8,b8,c);//A7-A8
        linea_bresenham(a8,b8,a1,b1,c);//A8-A1
    }
    //rectangulos
    public void dibujarAsteroide2(int x00,int y00, Color c){
        int a11 = x00,     a22=x00+4, a33=x00+5 ,a44=x00+5 ,a55=x00+3    ,a66=x00-4,  a77=x00-4,  a88=x00-3;
        int b11 = y00-5,  b22=y00-4, b33=y00-3  ,b44=y00+3  ,b55=y00+4   ,b66=y00+3,   b77=y00-3,   b88=y00-4;
        linea_bresenham(a11,b11,a22,b22,c);//A1-A2
        linea_bresenham(a22,b22,a33,b33,c);//A2-A3
        linea_bresenham(a33,b33,a44,b44,c);//A3-A4
        linea_bresenham(a44,b44,a55,b55,c);//A4-A5
        linea_bresenham(a55,b55,a66,b66,c);//A5-A6
        linea_bresenham(a66,b66,a77,b77,c);//A6-A7
        linea_bresenham(a77,b77,a88,b88,c);//A7-A8
        linea_bresenham(a88,b88,a11,b11,c);//A8-A1
    }
    //rectangulos
    public void dibujarRectangulo(int x0,int y0, int x1, int y1, Color c){
        linea_bresenham(x0,y0,x1,y0,c);
        linea_bresenham(x0,y1,x1,y1,c);
        linea_bresenham(x0,y0,x0,y1,c);
        linea_bresenham(x1,y0,x1,y1,c);
    }
    //triangulos
    public void dibujarTrangulo(int x0,int y0, int x1, int y1, int x2, int y2, Color c){
        linea_bresenham(x0,y0,x1,y1,c);//punto a
        linea_bresenham(x1,y1,x2,y2,c);//punto b
        linea_bresenham(x2,y2,x0,y0,c);//punto c
    }
    public void dibujarCirculo(int xc, int yc, int r, Color c){
        double x,y,dt;
        dt= Math.PI/(r*4);
        for(double t=0; t<=2*Math.PI; t+=dt) {
            x = Math.cos(t) * r + xc;
            y = Math.sin(t) * r +yc;
            putPixel((int)(Math.round(x)),(int)(Math.round(y)), c);
        }
    }
    public void Elipse(double xcnt, double ycnt, double rx, double ry) {
        double x, y;
        double Bronbreaker = 0;
        do {

            x = xcnt + rx * (float) Math.cos(Bronbreaker);
            y = ycnt + ry * (float) Math.sin(Bronbreaker);
            putPixel((int) x, (int) y, Color.red);
            Bronbreaker = Bronbreaker + 0.005;
        } while (Bronbreaker < 100);
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

    //translacion
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
    }
    //inundacion
    public void inundacion(int x, int y, Color relleno){
        if((x<this.getWidth() && y<this.getHeight()) && (x>0 && y>0)){
            color = leerColorPixel(x,y);
            if(color.equals(disponible)){
                putPixel(x,y,relleno);
                inundacion(x,y+1,relleno);
                inundacion(x+1,y,relleno);
                inundacion(x,y-1,relleno);
                inundacion(x-1,y,relleno);
            }
        }
    }

    //lee el color del pixel
    public Color leerColorPixel(int x, int y){
        color = new Color(animacion.getRGB(x, y));
        return color;
    }
    public void rotacion(double Bronbreaker) {
        double Flacktreck[] = {0, 0, 0};
        double[][] R = {
                {Math.cos(Math.toRadians(Bronbreaker)), (0 - Math.sin(Math.toRadians(Bronbreaker))), 0},
                {Math.sin(Math.toRadians(Bronbreaker)), Math.cos(Math.toRadians(Bronbreaker)), 0},
                {0, 0, 1}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Flacktreck[i] += rotacion[j] * R[i][j];
            }
        }
        rotacion[0] = Flacktreck[0];
        rotacion[1] = Flacktreck[1];
        rotacion[2] = Flacktreck[2];
    }
    public void rotacion2(double Bronbreaker2) {
        double Flacktreck2[] = {0, 0, 0};
        double[][] R2 = {
                {Math.cos(Math.toRadians(Bronbreaker2)), (0 - Math.sin(Math.toRadians(Bronbreaker2))), 0},
                {Math.sin(Math.toRadians(Bronbreaker2)), Math.cos(Math.toRadians(Bronbreaker2)), 0},
                {0, 0, 1}
        };
        for (int i2 = 0; i2 < 3; i2++) {
            for (int j2 = 0; j2 < 3; j2++) {
                Flacktreck2[i2] += rotacion2[j2] * R2[i2][j2];
            }
        }
        rotacion2[0] = Flacktreck2[0];
        rotacion2[1] = Flacktreck2[1];
        rotacion2[2] = Flacktreck2[2];
    }
}
