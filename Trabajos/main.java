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
    
    
    Control control = new Control(); 
    World.setDelay(10); 
     // Crear un nuevo hilo para el robot
    new Thread(control.metrosA.getLast()).start();
    new Thread(control.metrosA.get(control.metrosA.size() - 2)).start(); //Este está generando problemas a la hora de salir del taller
    new Thread(control.metrosB.getLast()).start();
    
    }
}
