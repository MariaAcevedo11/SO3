package Trabajos;
import kareltherobot.*;
import java.awt.Color;
import java.util.ArrayList; 
import java.util.Scanner;
 

// Clase personalizada de robot que corre en su propio hilo
public class Tren extends Robot implements Runnable, Directions {
    
    volatile public int columna; 
    volatile public int fila;
    String ruta; 
    Control tin; 
    volatile boolean son420; 

    public Tren(int street, int avenue, Direction direction, int beeps, Color color, String ruta, Control tin, boolean son420) {
        super(street, avenue, direction, beeps, color);
        this.columna = avenue; 
        this.fila = street;
        this.ruta = ruta; 
        this.tin = tin; 
        this.son420 = son420; 

        World.setupThread(this);
    }


    
    public boolean isSon420() {
        return son420;
    }

    public void setSon420(boolean son420) {
        this.son420 = son420;
    }
    public String getRuta(){
        return ruta; 
    }
    public void run() {
        salirDelT();

    }
    public void turnRight(){
        turnLeft(); 
        turnLeft(); 
        turnLeft();
    }
    public void goToIPosition(){

        if (this.ruta.equals("rutaAN")){
            
            irAN();
                while (!isSon420()){  
                }
                while (isSon420()){

                    rutaAE();
                    if (tin.son11){
                        break;
                    }
                    rutaAN(); 
                }
        }

        if (this.ruta.equals("rutaAE")){
            irAE();
                while (!isSon420()){  
                }

                while(isSon420()){
                    rutaAN();
                    if (tin.son11){
                        break;
                    }
                    rutaAE();
                }
        }
        if (this.ruta.equals("rutaASJ")){
            irASJ(); 
                while (!isSon420()){  
                }
                while(isSon420()){ 
                    rutaASA();
                    
                    rutaASJ();
                    if (tin.son11){
                        break; 
                    }
                }

 
        }

        while(tin.son11){   
            goToFPosition(); 
            }
        
    }
    public void salirDelT(){ //avenida = columna, calle = fila
        
    
        while (columna != 16 || fila != 32){

            if (columna == 15 && fila == 35 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 1 && fila == 35 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 1 && fila == 34 && !facingEast()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 34 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 14 && fila == 32 && !facingEast()){
                turnLeft(); 
            }  
            moverActualizandoCoord();
    }
        
        tin.block.lock();
        tin.enTaller.remove(tin.enTaller.getLast());
        tin.block.unlock(); 

        goToIPosition();
         //LLama esta funcion para que cuando terminen de salir del taller se vayan a sus pos iniciales
            
    }
    public void moverActualizandoCoord() {
        int filaAntes = fila; 
        int columnaAntes = columna;  

        if (facingNorth()) {
            fila++;
        }
        else if (facingSouth()) {
            fila--;
        }
        else if (facingEast()) {
            columna++;
        }
        else if (facingWest()) {
            columna--;
            
        }
        tin.block.lock(); 
        if (tin.mapa[fila][columna] == 1){ //tin es lo que me permite acceder al mapa que está en control, lo pongo de atributo. 
            fila = filaAntes; 
            columna = columnaAntes;
            tin.block.unlock();
        }
        else {
            tin.mapa[filaAntes][columnaAntes] = 0; //Desocupa la posición anterior del hp
            tin.mapa[fila][columna] = 1; //Marca la nueva posición del hp como ocupada
            tin.block.unlock(); //Se desbloquea antes del move para que no intervenga con la velocidad de los hp
            move(); //siempre que movamos el robot con este metodo (moverActualizandoCoord) tendremos las coordenadas del hp 

        }
    }
    public void irAN(){

       while (columna != 19 || fila != 35){

            if (columna == 17 && fila == 32 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 17 && fila == 34 && !facingEast()){
                turnRight(); 
            }
            if (columna == 20 && fila == 34 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 20 && fila == 35 && !facingWest()){
                turnLeft(); 
            }

            moverActualizandoCoord();

        }

    }
    public void irAE(){

        while(columna != 11 || fila != 1 ){

            if (columna == 16 && fila == 32 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 16 && fila == 29 && !facingWest()){
                turnRight(); 
            }
            if (columna == 15 && fila == 29 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 15 && fila == 26 && !facingWest()){
                turnRight(); 
            }
            if (columna == 13 && fila == 26 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 13 && fila == 23 && !facingWest()){
                turnRight(); 
            }
            if (columna == 11 && fila == 23 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 11 && fila == 18 && !facingEast()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 18 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 16 && fila == 11 && !facingWest()){
                turnRight(); 
            }
            if (columna == 13 && fila == 11 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 13 && fila == 5 && !facingWest()){
                turnRight(); 
            }
            if (columna == 12 && fila == 5 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 12 && fila == 2 && !facingWest()){
                turnRight(); 
            }
            if (columna == 10 && fila == 2 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 10 && fila == 1 && !facingEast()){
                turnLeft(); 
            }

            moverActualizandoCoord();
}

    }
    public void irASJ(){
        while (columna != 1 || fila != 16 ){

            if (columna == 16 && fila == 32 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 16 && fila == 29 && !facingWest()){
                turnRight(); 
            }
            if (columna == 15 && fila == 29 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 15 && fila == 26 && !facingWest()){
                turnRight(); 
            }
            if (columna == 13 && fila == 26 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 13 && fila == 23 && !facingWest()){
                turnRight(); 
            }
            if (columna == 11 && fila == 23 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 11 && fila == 14 && !facingWest()){
                turnRight();
            }
            if (columna == 7 && fila == 14 && !facingNorth()){
                turnRight();
            }
            if (columna == 7 && fila == 15 && !facingWest()){
                turnLeft();
            }
            if (columna == 2 && fila == 15 && !facingNorth()){
                turnRight();
            }
            if (columna == 2 && fila == 17 && !facingWest()){
                turnLeft();
            }
            if (columna == 1 && fila == 17 && !facingSouth()){
                turnLeft();
            }

            moverActualizandoCoord();
        }

    }
    public void rutaASA(){

       while(columna != 15 || fila != 14){

            waitBro();
            
            if (columna == 1 && fila == 14 && !facingEast()){
                turnLeft(); 
            }
            if (columna == 6 && fila == 14 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 6 && fila == 13 && !facingEast()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 13 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 14 && !facingEast()){
                turnRight(); 
            }

            moverActualizandoCoord(); 

        }

        turnLeft();
        turnLeft(); //Debe girar 180 grados porque san antonio no tiene devuelta. 
    }
    public void rutaASJ(){

        while (columna != 1 || fila != 16){

            waitBro(); 
            
            if (columna == 7 && fila == 14 && !facingNorth()){
                turnRight(); 
            }
            if (columna == 7 && fila == 15 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 2 && fila == 15 && !facingNorth()){
                turnRight(); 
            }
            if (columna == 2 && fila == 17 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 1 && fila == 17 && !facingSouth()){
                turnLeft(); 
            }

            moverActualizandoCoord(); 
        }
    }
    public void rutaAN(){

        while (columna != 19 || fila != 35){

            waitBro(); 
            
            if (columna == 13 && fila == 1 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 13 && fila == 4 && !facingEast()){
                turnRight();
            }
            if (columna == 14 && fila == 4 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 10 && !facingEast()){
                turnRight(); 
            }
            if (columna == 17 && fila == 10 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 17 && fila == 19 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 12 && fila == 19 && !facingNorth()){
                turnRight(); 
            }
            if (columna == 12 && fila == 22 && !facingEast()){
                turnRight(); 
            }
            if (columna == 14 && fila == 22 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 25 && !facingEast()){
                turnRight(); 
            }
            if (columna == 16 && fila == 25 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 28 && !facingEast()){
                turnRight(); 
            }
            if (columna == 17 && fila == 28 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 17 && fila == 34 && !facingEast()){
                turnRight(); 
            }
            if (columna == 20 && fila == 34 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 20 && fila == 35 && !facingWest()){
                turnLeft(); 
            }

            moverActualizandoCoord(); 

            //Hasta aquí llega de estrella a niquia, falta code de ruta completa

        }

    }
    public void rutaAE(){
        while (columna != 11 || fila != 1){

            waitBro(); 

            if (columna == 16 && fila == 35 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 29 && !facingWest()){
                turnRight(); 
            }
            if (columna == 15 && fila == 29 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 15 && fila == 26 && !facingWest()){
                turnRight(); 
            }
            if (columna == 13 && fila == 26 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 13 && fila == 23 && !facingWest()){
                turnRight(); 
            }
            if (columna == 11 && fila == 23 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 11 && fila == 18 && !facingEast()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 18 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 16 && fila == 11 && !facingWest()){
                turnRight(); 
            }
            if (columna == 13 && fila == 11 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 13 && fila == 5 && !facingWest()){
                turnRight(); 
            }
            if (columna == 12 && fila == 5 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 12 && fila == 2 && !facingWest()){
                turnRight(); 
            }
            if (columna == 10 && fila == 2 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 10 && fila == 1 && !facingEast()){
                turnLeft(); 
            }

            moverActualizandoCoord();

        }


    }
    public void waitBro(){

       

        if(nextToABeeper()){
            try {
                Thread.sleep(3000); // duerme 2 segundos (3000 - 1000)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (columna == 12 && fila == 13) {
            tin.blockCisneros.lock();
            try {
                if (tin.enSanancho) {
                    try {
                        tin.condicion.await();  // Espera mientras alguien está en Sanancho
                        tin.enSanancho = true; // Ocupa el recurso
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Buena práctica
                    }
                } else {
                    tin.enSanancho = true; // Ocupa el recurso
                }
            } finally {
                tin.blockCisneros.unlock(); // Siempre liberar el lock
            }
        }

        if (columna == 15 && fila == 14){
            tin.blockCisneros.lock();
            try {
                tin.enSanancho = false;     // Libera el recurso
                tin.condicion.signal();     // Despierta a un hilo esperando
            } finally {
                tin.blockCisneros.unlock();
            }
        }
    }
    public void vuelveN(){ //Desde al posición niquia 
        while (columna != 15 || fila != 33 ){
 
            //System.out.println("WHILE GO BACK N"); FUNCIONAAAA
            if (columna == 16 && fila == 35 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 33 && !facingWest()){
                turnRight(); 
            }
            moverActualizandoCoord();

        }
    }
    public void vuelveE(){ //Desde al posición Estrella 
        while(columna != 15 || fila != 33){

            if (columna == 13 && fila == 1 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 13 && fila == 4 && !facingEast()){
                turnRight(); 
            }
            if (columna == 14 && fila == 4 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 10 && !facingEast()){
                turnRight(); 
            }
            if (columna == 17 && fila == 10 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 17 && fila == 19 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 12 && fila == 19 && !facingNorth()){
                turnRight(); 
            }
            if (columna == 12 && fila == 22 && !facingEast()){
                turnRight(); 
            }
            if (columna == 14 && fila == 22 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 25 && !facingEast()){
                turnRight(); 
            }
            if (columna == 16 && fila == 25 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 28 && !facingEast()){
                turnRight(); 
            }
            if (columna == 17 && fila == 28 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 17 && fila == 34 && !facingEast()){
                turnRight(); 
            }
            if (columna == 20 && fila == 34 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 20 && fila == 35 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 35 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 33 && !facingWest()){
                turnRight(); 
            }
            moverActualizandoCoord(); 
        }
        
    }
    public void vuelveS(){ //Desde al posición SANJACHO

        while (columna != 15 || fila != 33){
            //System.out.println("WHILE GO BACK "); FUNCIONA :DDDDDD

            if (columna == 1 && fila == 14 && !facingEast()){
                turnLeft();  
            }
            if (columna == 6 && fila == 14 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 6 && fila == 13 && !facingEast()){
                turnLeft(); 
            }
            if (columna == 11 && fila == 13 && !facingNorth()){ //sale de linea B
                turnLeft(); 
            }
            if (columna == 11 && fila == 18 && !facingEast()){
                turnRight(); 
            }
            if (columna == 12 && fila == 18 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 12 && fila == 22 && !facingEast()){
                turnRight(); 
            }
            if (columna == 14 && fila == 22 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 25 && !facingEast()){
                turnRight(); 
            }
            if (columna == 16 && fila == 25 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 28 && !facingEast()){
                turnRight(); 
            }
            if (columna == 17 && fila == 28 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 17 && fila == 34 && !facingEast()){
                turnRight(); 
            }
            if (columna == 20 && fila == 34 && !facingNorth()){
                turnLeft(); 
            }
            if (columna == 20 && fila == 35 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 35 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 16 && fila == 33 && !facingWest()){
                turnRight(); 
            }

            moverActualizandoCoord();

        }

    }
    public void goToFPosition(){
        
        while(columna != 15 || fila != 33 ){
            
            if (columna == 11 && fila == 1){
                System.out.println(getRuta() + "GOBACKE"); 
                vuelveE();  
                System.out.println("si lo coge 1 ");
                
            }
            if (columna == 19 && fila == 35){
                System.out.println(getRuta() + "GOBACKN");
                    vuelveN();    
                    System.out.println("si lo coge 2 "); 
                
            }
            if (columna == 1 && fila == 16){
                System.out.println(getRuta() + "GOBACKS");
                    vuelveS(); 
                    System.out.println("si lo coge 3 ");        
            }


        }
        entrarAlT(); 
    }
    public void entrarAlT(){

        while (columna != 15 || fila != 32){

            if (columna == 15 && fila == 33 && !facingNorth()){
                turnRight();
            }
            if (columna == 15 && fila == 35 && !facingWest()){
                turnLeft(); 
            }
            if (columna == 1 && fila == 35 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 1 && fila == 34 && !facingEast()){
                turnLeft(); 
            }
            if (columna == 14 && fila == 34 && !facingSouth()){
                turnRight(); 
            }
            if (columna == 14 && fila == 32 && !facingEast()){
                turnLeft(); 
            }  
            moverActualizandoCoord();
    }
    }
    public void waitBroFinal(){
        if (columna == 11 && fila == 17){
            tin.blockD.lock();
            try {
                if(tin.enFrente1){
                    try {
                        tin.condicionBlockD.await(); 
                        tin.enFrente1 = true; // Ocupa el recurso
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Buena práctica
                    }
                } else {
                    tin.enFrente1 = true; // Ocupa el recurso
                }
            } finally {
                tin.blockD.unlock(); // Siempre liberar el lock
            }

        }
        if (columna == 11 && fila == 18){
            tin.blockD.lock();
            try {
                tin.enFrente1 = false;     // Libera el recurso
                tin.condicionBlockD.signal();     // Despierta a un hilo esperando
            } finally {
                tin.blockD.unlock();
            }
        }
    }
}