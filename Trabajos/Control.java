package Trabajos;
import java.util.ArrayList;
import kareltherobot.*;
import java.awt.Color;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;



public class Control implements Directions {

    public ArrayList<Tren> metrosA = new ArrayList<>();
    public ArrayList<Tren> metrosB = new ArrayList<>();
    public ArrayList<Tren> enTaller = new ArrayList<>();
    public final ReentrantLock block = new ReentrantLock(); //pa moverse

    public final ReentrantLock blockCisneros = new ReentrantLock(); //pa cisneros 
    public final Condition condicion = blockCisneros.newCondition();
    public final ReentrantLock blockD = new ReentrantLock();
    public final Condition condicionBlockD = blockD.newCondition();
    volatile public int[][] mapa = new int[36][21];  
    volatile public boolean son11 = false; 
    volatile public boolean son420 = false; 
    volatile public boolean enSanancho = false;  
    volatile public boolean enFrente1 = false; 
    volatile public boolean enFrente2 = false; 

    public Control(){
    
        int posColumna = 15; //inical 
        int posFila = 34; 
        Direction tipoDireccion = Directions.North;
        int contador = 0; 
         
        

        //Creación de robots con tipo de dirección respectivo en el taller

        //Aquí los creo teniendo en cuenta que ciertos van a tener ruta tin y otros ruta tan y los guardo respectivamente cambiando el string ruta
        while (metrosA.size() < 22 || metrosB.size() < 10){

            if (contador % 3 == 0){
                Tren trenB = new Tren(posFila, posColumna, tipoDireccion, 0, Color.GREEN, "rutaASJ", this);
                metrosB.add(trenB);
                enTaller.add(trenB); 
                
                 

            }
            else if (contador % 3 == 1){
                Tren trenA = new Tren(posFila, posColumna, tipoDireccion, 0, Color.BLUE, "rutaAN", this);
                metrosA.add(trenA);
                enTaller.add(trenA);
                 
            }
            else if (contador % 3 == 2){
                Tren trenA = new Tren(posFila, posColumna, tipoDireccion, 0, Color.BLUE, "rutaAE", this);
                metrosA.add(trenA);
                enTaller.add(trenA);
                 
            }
            mapa[posFila][posColumna] = 1; //Cada vez que se cree un hp le marca la posición en el mapa coom ocupado 


            //Aquí si los robots están mirando pa arriba que cree uno arriba del anterior, abajo, der, izq 
            if (tipoDireccion == Directions.North){ 
                posFila++;
            }
            else if (tipoDireccion == Directions.South) {
                posFila--;
            }
            else if (tipoDireccion == Directions.East) {
                posColumna++;
            }
            else if (tipoDireccion == Directions.West) {
                posColumna--;
            } 

            //Aquí comprueba si está en una esquina, sisi, que voltee respectivamente
            if (posColumna == 15 && posFila == 35){ 
                tipoDireccion = Directions.West;    
            }
            if (posColumna == 1 && posFila == 35){
                tipoDireccion = Directions.South;  
            }
            if (posColumna == 1 && posFila == 34){
                tipoDireccion = Directions.East;  
            }
            if (posColumna == 14 && posFila == 34){
                tipoDireccion = Directions.South;  
            }
            if (posColumna == 14 && posFila == 32){
                tipoDireccion = Directions.East;  
            }

            contador++; 
            
        }

        
    }
    
}
