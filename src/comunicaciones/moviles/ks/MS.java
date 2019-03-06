package comunicaciones.moviles.ks;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Random;

public class MS extends Thread{
    
    public int posicionX, posicionY;
    public String tipo;
    public String numero;
    public int velocidad;
    private int tiempoEspera;
    public Antena []vectorAntenas;
    public int antenaActual=0;
    public double potencia;
    private int camino, caso, ex;
    private boolean prim = false;
    
    public MS(String bas, String tip, int vel){
        this.tipo=tip;
        this.numero=bas;
        this.velocidad=vel;
        this.calculartiempo();
        Random rnd = new Random();
        this.posicionX=(int)(rnd.nextDouble()*870+30);
        this.posicionY=(int)(rnd.nextDouble()*670+30);
        this.camino =(int)(rnd.nextDouble()*40);
        this.caso =(int)(rnd.nextDouble()*4);
    }

    public void calculartiempo(){
        this.tiempoEspera=(int)(1000/this.velocidad);
    }
    
    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
    
    public void setVectAntenas(Antena []Antenas){
        this.vectorAntenas = Antenas;
    }
    
    public void validarBordes(){
        if (this.tipo.equals("peaton")){
            if (this.posicionX>980 || this.posicionY>700) {
                        Random rnd = new Random();
                        this.posicionX=(int)(rnd.nextDouble()*870+30);
                        this.posicionY=(int)(rnd.nextDouble()*670+30);
                        ex=0;
            }
        }
        if (this.tipo.equals("carro")){
            if (this.posicionX>980) {
                        this.posicionX=0;
                        ex=0;
            }
        }
    }

    public double potenciaRecibida(double alfa, int posicion){
        double distancia, poten;
        //int ene = 3;
        //potencia = alfa*this.vectorAntenas[posicion].potTx*pow(distancia,(-1*ene));
        distancia = sqrt(pow((this.vectorAntenas[posicion].posicionX-this.posicionX),2)+pow((this.vectorAntenas[posicion].posicionY-this.posicionY),2))*0.19;

        double potTxAd = Math.pow(10, this.vectorAntenas[posicion].potTx/10);
        double ganRxAd = Math.pow(10, this.vectorAntenas[posicion].ganRx/10);
        double ganTxAd = Math.pow(10, this.vectorAntenas[posicion].ganTx/10);
        double landa = 300000000/this.vectorAntenas[posicion].frecuencia;
        double perdidas = 0.0000316227766; //15 decibelios de perdidas
        double aux = Math.pow((landa/(4*Math.PI*distancia)),2);
        poten = potTxAd*ganRxAd*ganTxAd*perdidas*aux;
        return poten;   
    }
    
    
    public void run(){
        while (!interrupted()) {
            //System.out.println(potenciathis.lala+":" +this.posicionX);
            switch(this.tipo){
                case "carro":
                    this.posicionX++;
                    //ystem.out.println(this.tipo+this.numero+": " +this.posicionX);
                    break;
                case "peaton":
                    if(camino>0){
                        camino--;
                        switch(caso){
                            case 1:
                                this.posicionX++;
                                this.posicionX++;
                                break;
                            case 2:
                                this.posicionY++;
                                this.posicionY++;
                                break;
                            case 3:
                                this.posicionX++;
                                this.posicionY++;
                                break;
                            case 4:
                                this.posicionY--;
                                this.posicionX++;
                                break;
                    
                        }
                    }
                    else{
                    Random rnd = new Random();
                    this.camino=(int)(rnd.nextDouble()*40);
                    this.caso =(int)(rnd.nextDouble()*4);
                    }
                    break;
            }
          
            this.validarBordes();
            if(antenaActual<8){
            this.potencia = this.potenciaRecibida(1,this.antenaActual);
            }
            
            for(int k=0;k<8;k++){
                double pot = this.potenciaRecibida(0.7,k);
                if (potencia < pot){
                    this.antenaActual = k;
                }
                if(10*Math.log(potencia*1000)<-90 && prim){
                    this.antenaActual = 8;
                }
            }
            if(ex<10){
                ex++;
                prim = false;
            }
            else{
                prim = true;
            }
            
            try {
                Thread.sleep(this.tiempoEspera);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}
