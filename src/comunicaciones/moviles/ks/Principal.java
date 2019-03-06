package comunicaciones.moviles.ks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class Principal implements ActionListener{
    public static VentanaMapa ventanaMapa;
    public MS hilo;
    static JFrame ventanaPrincipal;
    private static JTextArea areaTextoCarro, areaTextoAntena, areaTextoPeaton;
    private JComboBox numeroEstacionCarro, numeroEstacionFija, numeroEstacionPeaton;
    private static int numeroEstacionDatosCarro, numeroEstacionDatosPeaton, numeroEstacionDatosAntena;
    private static String []vectColores = new String[10];
    private String auxStr;
    public static Antena []vectorAntenasFijas;
    //public EstacionMovil []vectorMovilesPeatones;
    //public EstacionMovil []vectorMovilesCarros;
    public static MS []vectorHilosPeatones;
    public static MS []vectorHilosCarros;
    public int numAntenas, numMovilesPeatones ;
    public static Principal programaPrincipal;
    
    
    private static String titulo="";
    private static String numEstMov="";
    private static String vel="";
    private static String posiX="";
    private static String posiY="";
    private static String pot="";
    private static String ant="";
    private static String est="";
    

    public static void main(String[] args) {
        programaPrincipal = new Principal();
        ventanaMapa = new VentanaMapa(programaPrincipal);
        
        ventanaMapa.setVisible(true);
        
        while(true){    
            try {
                    Thread.sleep(100);
                    //this.ventanaMapa.repaint();
                    ventanaMapa.repaint();
                    
                    //DATOS DE LA ANTENA
                    titulo= "                 DATOS DE ESTACION FIJA ANTENA"+ "\n";
                    numEstMov = "  Numero de Antena:   "+ String.valueOf(numeroEstacionDatosAntena);
                    posiX = "  Posicion en X:   " + String.valueOf(vectorAntenasFijas[numeroEstacionDatosAntena].posicionX);
                    posiY = "  Posicion en Y:   " + String.valueOf(vectorAntenasFijas[numeroEstacionDatosAntena].posicionY);
                    pot = "  Potencia:   " + String.valueOf(vectorAntenasFijas[numeroEstacionDatosAntena].potTx)+ " Dbm";
                    ant = "  Color de Antena:   " + vectColores[numeroEstacionDatosAntena];
                    
                    areaTextoAntena.setText(titulo + "\n" + numEstMov+ "\n" + posiX + "\n" + posiY+ "\n" +pot+ "\n" +ant);

                    
                    //DATOS DE LOS 
                    titulo= "                 DATOS DE ESTACION MOVIL PEATON"+ "\n";
                    numEstMov = "  Numero de Estacion Movil:   "+ String.valueOf(numeroEstacionDatosPeaton);
                    vel = "  Velocidad:   " + String.valueOf(vectorHilosPeatones[numeroEstacionDatosPeaton].velocidad)+" Km/h";
                    posiX = "  Posicion en X:   " + String.valueOf(vectorHilosPeatones[numeroEstacionDatosPeaton].posicionX);
                    posiY = "  Posicion en Y:   " + String.valueOf(vectorHilosPeatones[numeroEstacionDatosPeaton].posicionY);
                    pot = "  Potencia:   " + String.valueOf(10*Math.log(vectorHilosPeatones[numeroEstacionDatosPeaton].potencia*1000))+" Dbm";
                    ant = "  Antena Proveedora:   " + String.valueOf(vectorHilosPeatones[numeroEstacionDatosPeaton].antenaActual) +"  (" + vectColores[vectorHilosPeatones[numeroEstacionDatosPeaton].antenaActual]+")";
                    double ayuda =10*Math.log(vectorHilosPeatones[numeroEstacionDatosPeaton].potencia*1000);
                    if(ayuda<-90){
                        est = "  Estado: DESCONECTADO";
                    }
                    else if(ayuda>-90 && ayuda<-85){
                        est = "  Estado: BAJO";
                    }
                    else if(ayuda>-85 && ayuda<-70){
                        est = "  Estado: MEDIO";
                    }
                    else{
                        est = "  Estado: ALTO";
                    }
                                 
                    areaTextoPeaton.setText(titulo + "\n" + numEstMov+ "\n" +vel+ "\n" + posiX + "\n" + posiY+ "\n" +pot+ "\n" +ant+ "\n"+est);

                    
                    //DATOS DE LOS CARROS
                    titulo= "                 DATOS DE ESTACION MOVIL CARRO"+ "\n";
                    numEstMov = "  Numero de Estacion Movil:   "+ String.valueOf(numeroEstacionDatosCarro);
                    vel = "  Velocidad:   " + String.valueOf(vectorHilosCarros[numeroEstacionDatosCarro].velocidad)+" Km/h";
                    posiX = "  Posicion en X:   " + String.valueOf(vectorHilosCarros[numeroEstacionDatosCarro].posicionX);
                    posiY = "  Posicion en Y:   " + String.valueOf(vectorHilosCarros[numeroEstacionDatosCarro].posicionY);
                    pot = "  Potencia:   " + String.valueOf(10*Math.log(vectorHilosCarros[numeroEstacionDatosCarro].potencia*1000))+" Dbm";
                    ant = "  Antena Proveedora:   " + String.valueOf(vectorHilosCarros[numeroEstacionDatosCarro].antenaActual) +"  (" + vectColores[vectorHilosCarros[numeroEstacionDatosCarro].antenaActual]+")";
                    ayuda =10*Math.log(vectorHilosCarros[numeroEstacionDatosCarro].potencia*1000);
                    if(ayuda<-90){
                        est = "  Estado: DESCONECTADO";
                    }
                    else if(ayuda>-90 && ayuda<-85){
                        est = "  Estado: BAJO";
                    }
                    else if(ayuda>-85 && ayuda<-70){
                        est = "  Estado: MEDIO";
                    }
                    else{
                        est = "  Estado: ALTO";
                    }
                    areaTextoCarro.setText(titulo + "\n" + numEstMov+ "\n" +vel+ "\n" + posiX + "\n" + posiY+ "\n" +pot+ "\n" +ant+"\n" +est);


                }
            catch (InterruptedException ex) {}
        
            }
        
        
    }
    
    public Principal (){
        //INGRESAR NUMERO DE PEATONES
        auxStr = JOptionPane.showInputDialog("Ingrese la Cantidad de Peatones");
        this.numMovilesPeatones = Integer.parseInt(auxStr);
         

        //LLENAR VECTOR DE LISTA DE COLORES
        vectColores[0]= "NEGRO";
        vectColores[1]= "AZUL";
        vectColores[2]= "CELESTE";
        vectColores[3]= "VERDE";
        vectColores[4]= "MORADO";
        vectColores[5]= "TOMATE";
        vectColores[6]= "ROJO";
        vectColores[7]= "AMARILLO";
        vectColores[8]= "BLANCO";
        vectColores[9]= "GRIS";
        
        //VENTANA DE CONTROL
        ventanaPrincipal = new JFrame();
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setTitle("COMUNICACIONES MOVILES");
        ventanaPrincipal.setBounds(1000, 0, 350, 700);
        
        //PANEL DE LA VENTANA PRINCIPAL
        JPanel panelVentanaPrincipal = new JPanel();
        panelVentanaPrincipal.setBackground(Color.LIGHT_GRAY);
        panelVentanaPrincipal.setLayout(null);
        ventanaPrincipal.setContentPane(panelVentanaPrincipal);
        
        //TITULO DEL PROYECTO
        JLabel titulo1 = new JLabel("SIMULACIÓN DEL PROCESO");
        titulo1.setFont(new Font("Verdana", Font.BOLD, 16));
        titulo1.setSize(400, 20);
        titulo1.setLocation(40, 10);
        panelVentanaPrincipal.add(titulo1);
        JLabel titulo2 = new JLabel("HANDOVER/ HAND-OFF");
        titulo2.setFont(new Font("Verdana", Font.BOLD, 16));
        titulo2.setSize(400, 20);
        titulo2.setLocation(50, 30);
        panelVentanaPrincipal.add(titulo2);
        
        //ANTENAS
        JLabel textoNumeroAntenas = new JLabel("------------------ANTENAS------------------");
        textoNumeroAntenas.setFont(new Font("Verdana", Font.BOLD, 14));
        textoNumeroAntenas.setLocation( 10, 55);
        textoNumeroAntenas.setSize( 500, 20);
        panelVentanaPrincipal.add(textoNumeroAntenas);
        
        //AREA DE TEXTO PARA MOSTRAR DATOS DE ANTENAS
        areaTextoAntena = new JTextArea();
        areaTextoAntena.setSize(300, 150);
        areaTextoAntena.setLocation(20, 100);
        //areaTexto.setLineWrap(true);
        panelVentanaPrincipal.add(areaTextoAntena);
                
        //ESCOJER NUMERO DE ANTENA
        numeroEstacionFija = new JComboBox();
        numeroEstacionFija.setSize(40,25);
        numeroEstacionFija.setLocation(200,75);
        for(int k=0;k<8;k++){
            this.numeroEstacionFija.addItem(Integer.toString(k));
        }
        numeroEstacionFija.setActionCommand("comboBoxAntena");
        numeroEstacionFija.addActionListener(this);
        panelVentanaPrincipal.add(numeroEstacionFija);
       
        //Texto Escoger Atena
        JLabel textoEscogerAntenas = new JLabel("Escoger Antena:");
        textoEscogerAntenas.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        textoEscogerAntenas.setLocation( 50, 75);
        textoEscogerAntenas.setSize( 500, 20);
        panelVentanaPrincipal.add(textoEscogerAntenas);
        
        
        
        
        
         //PEATONES
        JLabel textoPeatones = new JLabel("-----------------PEATONES-----------------");
        textoPeatones.setFont(new Font("Verdana", Font.BOLD, 14));
        textoPeatones.setLocation( 10, 260);
        textoPeatones.setSize( 500, 20);
        panelVentanaPrincipal.add(textoPeatones);
        
        //ESCOJER NUMERO DE PEATON
        numeroEstacionPeaton = new JComboBox();
        numeroEstacionPeaton.setSize(40,25);
        numeroEstacionPeaton.setLocation(200,280);
        for(int k=0;k<this.numMovilesPeatones;k++){
            this.numeroEstacionPeaton.addItem(Integer.toString(k));
        }
        numeroEstacionPeaton.setActionCommand("comboBoxPeaton");
        numeroEstacionPeaton.addActionListener(this);
        panelVentanaPrincipal.add(numeroEstacionPeaton);
       
        //AREA DE TEXTO PARA MOSTRAR DATOS PETAONES
        areaTextoPeaton = new JTextArea();
        areaTextoPeaton.setSize(300, 150);
        areaTextoPeaton.setLocation(20, 305);
        //areaTexto.setLineWrap(true);
        panelVentanaPrincipal.add(areaTextoPeaton);
        
        //Texto Escoger Peaton
        JLabel textoEscogerPeaton = new JLabel("Escoger Peaton:");
        textoEscogerPeaton.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        textoEscogerPeaton.setLocation( 50, 280);
        textoEscogerPeaton.setSize(500, 20);
        panelVentanaPrincipal.add(textoEscogerPeaton);
        
        
        
       
        //CARROS
        JLabel textoPDatos = new JLabel("------------------CARROS------------------");
        textoPDatos.setFont(new Font("Verdana", Font.BOLD, 14));
        textoPDatos.setLocation( 10, 460);
        textoPDatos.setSize( 500, 20);
        panelVentanaPrincipal.add(textoPDatos);
        
        //ESCOJER NUMERO DE CARRO
        numeroEstacionCarro = new JComboBox();
        numeroEstacionCarro.setSize(40,25);
        numeroEstacionCarro.setLocation(200,480);
        for(int k=0;k<7;k++){
            this.numeroEstacionCarro.addItem(Integer.toString(k));
        }
        numeroEstacionCarro.setActionCommand("comboBoxCarro");
        numeroEstacionCarro.addActionListener(this);
        panelVentanaPrincipal.add(numeroEstacionCarro);
       
        //Texto Escoger Carro
        JLabel textoEscogerCarro = new JLabel("Escoger Carro:");
        textoEscogerCarro.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        textoEscogerCarro.setLocation( 50, 480);
        textoEscogerCarro.setSize(500, 20);
        panelVentanaPrincipal.add(textoEscogerCarro);
        
        
        //AREA DE TEXTO PARA MOSTRAR DCARROS
        areaTextoCarro = new JTextArea();
        areaTextoCarro.setSize(300, 150);
        areaTextoCarro.setLocation(20, 505);
        //areaTexto.setLineWrap(true);
        panelVentanaPrincipal.add(areaTextoCarro);
                
   
        ventanaPrincipal.setVisible(true);

        this.cargarAntenasFijas();
        this.cargarCarros();
        
        //CREAR VECTOR DE PEATONES        
        this.vectorHilosPeatones= new MS[numMovilesPeatones];
        for(int k=0;k<numMovilesPeatones;k++){
            this.vectorHilosPeatones[k] = new MS(Integer.toString(k),"peaton",2);
            this.vectorHilosPeatones[k].setVectAntenas(this.vectorAntenasFijas);
        } 
        
    }
    
    public void cargarAntenasFijas(){
        //CARGAR ANTENAS
        this.vectorAntenasFijas= new Antena[8];
        this.vectorAntenasFijas[0] = new Antena(720,20,17,12,850000000);
        this.vectorAntenasFijas[1] = new Antena(610,100,17,12,850000000);
        this.vectorAntenasFijas[2] = new Antena(825,220,20.5,12,850000000);
        this.vectorAntenasFijas[3] = new Antena(570,280,19,12,850000000);
        this.vectorAntenasFijas[4] = new Antena(500,470,19,12,850000000);
        this.vectorAntenasFijas[5] = new Antena(920,685,28.5,12,850000000);
        this.vectorAntenasFijas[6] = new Antena(-10,-10,32,12,850000000);
        this.vectorAntenasFijas[7] = new Antena(195,890,29.5,12,850000000);
        
        
    }
    
    public void cargarCarros(){
        //CARGAR 7 CARROS

        //Hilo para cada carro
        this.vectorHilosCarros= new MS[7];
        this.vectorHilosCarros[0] = new MS(Integer.toString(0), "carro", 10);
        this.vectorHilosCarros[1] = new MS(Integer.toString(1), "carro", 20);
        this.vectorHilosCarros[2] = new MS(Integer.toString(2), "carro", 30);
        this.vectorHilosCarros[3] = new MS(Integer.toString(3), "carro", 40);
        this.vectorHilosCarros[4] = new MS(Integer.toString(4), "carro", 50);
        this.vectorHilosCarros[5] = new MS(Integer.toString(5), "carro", 60);
        this.vectorHilosCarros[6] = new MS(Integer.toString(6), "carro", 70);
        //Posiciones de las Calles
        this.vectorHilosCarros[0].setPosicionY(50);
        this.vectorHilosCarros[0].setPosicionY(77);
        this.vectorHilosCarros[1].setPosicionY(144);
        this.vectorHilosCarros[2].setPosicionY(203);
        this.vectorHilosCarros[3].setPosicionY(267);
        this.vectorHilosCarros[4].setPosicionY(347);
        this.vectorHilosCarros[5].setPosicionY(439);
        this.vectorHilosCarros[6].setPosicionY(507);
        //Posiciones de inicio
        this.vectorHilosCarros[0].setPosicionX(0);
        this.vectorHilosCarros[0].setPosicionX(0);
        this.vectorHilosCarros[1].setPosicionX(0);
        this.vectorHilosCarros[2].setPosicionX(0);
        this.vectorHilosCarros[3].setPosicionX(0);
        this.vectorHilosCarros[4].setPosicionX(0);
        this.vectorHilosCarros[5].setPosicionX(0);
        this.vectorHilosCarros[6].setPosicionX(0);
        //Vector de Antenas
        this.vectorHilosCarros[0].setVectAntenas(this.vectorAntenasFijas);
        this.vectorHilosCarros[1].setVectAntenas(this.vectorAntenasFijas);
        this.vectorHilosCarros[2].setVectAntenas(this.vectorAntenasFijas);
        this.vectorHilosCarros[3].setVectAntenas(this.vectorAntenasFijas);
        this.vectorHilosCarros[4].setVectAntenas(this.vectorAntenasFijas);
        this.vectorHilosCarros[5].setVectAntenas(this.vectorAntenasFijas);
        this.vectorHilosCarros[6].setVectAntenas(this.vectorAntenasFijas);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        //COMBOBOX ANTENAS
        if ("comboBoxAntena".equals(e.getActionCommand())) {
           this.numeroEstacionDatosAntena = this.numeroEstacionFija.getSelectedIndex();
        } 
        //COMBOBOX PEATON
        if ("comboBoxPeaton".equals(e.getActionCommand())) {
           this.numeroEstacionDatosPeaton = this.numeroEstacionPeaton.getSelectedIndex();
        }
        //COMBOBOX CARRO
        if ("comboBoxCarro".equals(e.getActionCommand())) {
           this.numeroEstacionDatosCarro = this.numeroEstacionCarro.getSelectedIndex();
        }
    }
   
}
