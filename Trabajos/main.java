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

    new Thread(tin.metrosA.getLast()).start();
    new Thread(tin.metrosA.get(tin.metrosA.size() - 2)).start(); 
    new Thread(tin.metrosB.getLast()).start();

    while (!(tin.mapa[35][19] == 1 && tin.mapa[16][1] == 1 && tin.mapa[1][11] == 1)); 
    
    if (tin.mapa[35][19] == 1 && tin.mapa[16][1] == 1 && tin.mapa[1][11] == 1){
        Scanner input = new Scanner(System.in);

        System.out.println("Qué hora es bro?");
        String hora = input.next(); 

        if (hora.equals("420") || hora.equals("4:20")){
            tin.son420 = true; 
            tin.son11 = false;
        } else if (hora.equals("11") || hora.equals("11:00")){
            tin.son11 = true; 
            tin.son420 = false; 
        } else {
            System.out.println("La hora no es válida");
        }
    }

    for (int i = 29; i > 26; i--) {

    new Thread(tin.enTaller.get(i)).start(); //el remove del taller se hace solo, al final de la iteración taller = 0

    try {
        Thread.sleep(15000); // Espera 15 segundos (15,000 milisegundos)
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Buena práctica
    }
}


    
    }
}
