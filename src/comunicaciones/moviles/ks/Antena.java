
package comunicaciones.moviles.ks;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.pow;

public class Antena {
    public int posicionX, posicionY;
    public double potRx=-90;
    public double ganRx=4;
    public double potTx, ganTx, frecuencia;
    public Antena(int posX, int posY, double pTx, double gTx, double frec){
        this.posicionX = posX;
        this.posicionY = posY;
        this.frecuencia = frec;
        this.ganTx = gTx;
        this.potTx = pTx;
    }
    
    public double calcularRadio(){
        double potTxAd = Math.pow(10, this.potTx/10);
        double potRxAd = Math.pow(10, this.potRx/10);
        double ganRxAd = Math.pow(10, this.ganRx/10);
        double ganTxAd = Math.pow(10, this.ganTx/10);
        double landa = 300000000/this.frecuencia;
        double perdidas = 0.0000316227766; //15 decibelios de perdidas
        //double auxiliar = (this.potRx-this.potTx-this.ganRx-this.ganTx)/20;
        double aux = Math.sqrt((potTxAd*ganRxAd*ganTxAd*perdidas)/potRxAd);
        double radio = (landa*aux)/(4*Math.PI);
        return radio;
    }
}
