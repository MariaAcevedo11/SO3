import Trabajos.Control;
import kareltherobot.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner; 

public class main { 
public static void main(String[] args) {

    
    World.readWorld("MetroMed.kwld");
    World.setVisible(true); 
    World.setDelay(0);                
    
    
    Control tin = new Control(); 
    World.setDelay(10); 
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
            tin.son420 = true; 
        } else {
            System.out.println("La hora no es válida");
        }
    }



    for (int i = 29; i >= 0; i--) {

    new Thread(tin.enTaller.get(i)).start(); //el remove del taller se hace solo, al final de la iteración taller = 0
    try {
        Thread.sleep(100); // Espera 15 segundos (15,000 milisegundos)
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Buena práctica
    }
}


    /*try {
        Thread.sleep(5000); // Espera 20 segundos (15,000 milisegundos)
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Buena práctica
    }
    tin.son11 = true;
    tin.son420 = false; */
    
        /*System.out.println("¿Qué hora es bro?"); 
        String hora2 = input.next(); 

            if (hora2.equals("11") || hora2.equals("11:00")){
                tin.son11 = true; 
                tin.son420 = false; 
            } else {
                System.out.println("La hora no es valida bro"); 
            } */
    
    }
}
