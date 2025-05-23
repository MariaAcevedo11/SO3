package Trabajos;
import Trabajos.Control;
import kareltherobot.*;
import java.awt.Color;
import java.io.PrintStream;
import Trabajos.FilteredPrintStream;
import java.util.ArrayList;
import java.util.Scanner; 
import Trabajos.Tren; 

public class main{ 
public static void main(String[] args) {
    World.readWorld("MetroMed.kwld");
    World.setVisible(true); 
    World.setDelay(0);                
    
    PrintStream originalOut = System.out;
    System.setOut(new FilteredPrintStream(originalOut));
    Control tin = new Control();
    
    World.setDelay(20); 
     // Crear un nuevo hilo para el robot
    Scanner input = new Scanner(System.in);
    new Thread(tin.metrosA.getLast()).start();
    new Thread(tin.metrosA.get(tin.metrosA.size() - 2)).start(); 
    new Thread(tin.metrosB.getLast()).start();

    while (!(tin.mapa[35][19] == 1 && tin.mapa[16][1] == 1 && tin.mapa[1][11] == 1)); 
    
    if (tin.mapa[35][19] == 1 && tin.mapa[16][1] == 1 && tin.mapa[1][11] == 1){
        

        System.out.println("Qué hora es bro?");
        String hora = input.next(); 

        if (hora.equals("420") || hora.equals("4:20")){
            ArrayList<Tren> listaTrenes = tin.getTrenes();

            for (int i = 0; i < listaTrenes.size(); i++) {
                Tren trenActual = listaTrenes.get(i);
                // 4. Verificar que el tren no es null (evita NullPointerException)
                    trenActual.setSon420(true);
                    
                
            }
            System.out.println("LOS PUSO TRUE"); 
            tin.son11 = false; 
        } else {
            System.out.println("La hora no es válida");
        }
    }



    for (int i = 28; i >= 0; i--) {

        new Thread(tin.enTaller.get(i)).start(); //el remove del taller se hace solo, al final de la iteración taller = 0
        try {
            Thread.sleep(100); // Espera 15 segundos (15,000 milisegundos)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Buena práctica
        }
    }

    //if ultimotren ya pasó por NIQUIA entonces solo ahí pregunta 
    System.out.println("¿Bro, son las 11 PM? (sí/no)");
    String respuesta = input.next().toLowerCase();

    if (respuesta.equals("sí") || respuesta.equals("si") || respuesta.equals("11")) {
        tin.son11 = true;  
        
        if (tin.son11 && (tin.mapa[16][1] == 1 || tin.mapa[1][11] == 1 || tin.mapa[35][19] == 1) ){
        while(true){

            ArrayList<Tren> trenes = tin.getTrenes(); 
            for (Tren tren : trenes){
                if ((tren.columna == 1 && tren.fila == 16) || (tren.columna == 11 && tren.fila == 1) || (tren.columna == 20 && tren.fila == 35)){
                    tren.setSon420(false);
                } else {

                }
                
            }
        }
    }
    

        //System.out.println(tin.son11 + "main"); SI SE PONE TRUE PERO NO ACTUALIZA LA HPTA
        //System.out.println("Son las 11 PM. Los trenes se devuelven al taller.");
    } else {
         // Reanuda los trenes
        //System.out.println("Los trenes continúan su ruta.");
    }
    
    }
}
