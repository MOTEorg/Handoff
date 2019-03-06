
package comunicaciones.moviles.ks;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import javax.swing.border.*;




public class VentanaMapa extends JFrame {    
public JPanel panel;
private MS []vectHilosPeatones;
private int numMov;
private Principal auxiliarPrincipal;
private Color []listaColores = new Color[10];

    public VentanaMapa(Principal princ){
        this.auxiliarPrincipal = princ;
        this.numMov = this.auxiliarPrincipal.numMovilesPeatones;
        this.vectHilosPeatones = this.auxiliarPrincipal.vectorHilosPeatones;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 00, 1000, 700);
        setTitle("COMUNICACIONES MOVILES");
        panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setLayout(null);
        setContentPane(panel);
        
        //IMAGEN DEL MAPA  
        String direccion ="C:\\Users\\Kevin\\Documents\\ESTATAL\\QUINTO AÑO\\Noveno Ciclo\\Comunicaciones Moviles\\Proyecto Final\\Comunicaciones Moviles KS\\mapa cuenca.jpg"; 
        JLabel mapa = new JLabel(new ImageIcon(direccion));
        mapa.setSize(985, 666);
        mapa.setLocation(0,0);
        panel.add(mapa);
        
        //LLENAR VECTOR DE LISTA DE COLORES
        listaColores[0]= Color.BLACK;
        listaColores[1]= Color.BLUE;
        listaColores[2]= Color.CYAN;
        listaColores[3]= Color.GREEN;
        listaColores[4]= Color.MAGENTA;
        listaColores[5]= Color.ORANGE;
        listaColores[6]= Color.RED;
        listaColores[7]= Color.YELLOW;
        listaColores[8]= Color.WHITE;
        listaColores[9]= Color.DARK_GRAY;
       
        //CORRER HILOS DE PEATONES
        for(int k=0;k<this.numMov;k++){
            this.vectHilosPeatones[k].start();
        } 
        //CORRER HILOS DE CARROS
        for(int k=0;k<7;k++){
            this.auxiliarPrincipal.vectorHilosCarros[k].start();
        } 
    }

    
public void paint( Graphics g ){
    super.paint( g ); // call superclass's paint method
    float dashes[] = { 10 }; 
    Graphics2D g2d = ( Graphics2D ) g;
    g2d.setStroke( new BasicStroke( 5.0f ) );
    g2d.setStroke( new BasicStroke( 4, BasicStroke.CAP_ROUND,
    BasicStroke.JOIN_ROUND, 10, dashes, 0 ) ); 

   
    
    //DIBUJAR ANTENAS FIJAS
    for(int k=0;k<8;k++){
       double radio = (int)this.auxiliarPrincipal.vectorAntenasFijas[k].calcularRadio();
       g2d.setColor(this.listaColores[k]);
       g2d.fill(new Rectangle2D.Double(this.auxiliarPrincipal.vectorAntenasFijas[k].posicionX, this.auxiliarPrincipal.vectorAntenasFijas[k].posicionY, 20, 20)); 
       g2d.draw( new Ellipse2D.Double(this.auxiliarPrincipal.vectorAntenasFijas[k].posicionX-(radio/2)+5, this.auxiliarPrincipal.vectorAntenasFijas[k].posicionY-(radio/2), radio, radio ));
       g2d.drawString(String.valueOf(k), this.auxiliarPrincipal.vectorAntenasFijas[k].posicionX, this.auxiliarPrincipal.vectorAntenasFijas[k].posicionY);
    }

    //DIBUJAR MOVILES CARROS
    for(int k=0;k<7;k++){
       g2d.setColor(this.listaColores[this.auxiliarPrincipal.vectorHilosCarros[k].antenaActual]);
       double xx = this.auxiliarPrincipal.vectorHilosCarros[k].getPosicionX();
       double yy = this.auxiliarPrincipal.vectorHilosCarros[k].getPosicionY();
       g2d.fill(new Rectangle2D.Double(xx, yy, 30, 20));
       g2d.drawString(String.valueOf(k),(int)xx,(int)yy);
   } 
    
    
    //DIBUJAR MOVILES PEATONES
    for(int k=0;k<this.numMov;k++){
       g2d.setColor(this.listaColores[this.auxiliarPrincipal.vectorHilosPeatones[k].antenaActual]);
       double xx = this.vectHilosPeatones[k].getPosicionX();
       double yy = this.vectHilosPeatones[k].getPosicionY();
       g2d.fill( new Ellipse2D.Double(xx, yy, 15, 15 ));
       g2d.drawString(String.valueOf(k),(int)xx,(int)yy);
    } 
 
 }


}
  
