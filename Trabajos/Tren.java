package Trabajos;
import kareltherobot.*;
import java.awt.Color;
import java.util.ArrayList; 

// Clase personalizada de robot que corre en su propio hilo
class Tren extends Robot implements Runnable, Directions {
    
    
    int columna; 
    int fila;
    String ruta; 
    Control tin; 

    public Tren(int street, int avenue, Direction direction, int beeps, Color color, String ruta, Control tin) {
        super(street, avenue, direction, beeps, color);
        this.columna = avenue; 
        this.fila = street;
        this.ruta = ruta; 
        this.tin = tin; 

        World.setupThread(this);
    }

    // Movimiento que hará el robot
    public void race() {

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
            System.out.println("1");
            irAN(); 
        }
        if (this.ruta.equals("rutaAE")){
            System.out.println("2");
            irAE();
            rutaA();  

        }
        if (this.ruta.equals("rutaASJ")){
            System.out.println("3");
            irASJ(); 
            irASA(); 
             

        }
    }
     

    public void salirDelT(){ //avenida = columna, calle = fila
        
        while (columna != 16 || fila != 32 ){
            
            if (columna == 15 && fila == 35 && !facingEast()){ //El facing dice que se gire el hp si es que ya no está mirando donde debería
                turnLeft(); 
            }
            if (columna == 1 && fila == 35 && !facingSouth()){
                turnLeft(); 
            }
            if (columna == 1 && fila == 34 && !facingWest()){
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
        
        goToIPosition(); //LLama esta funcion para que cuando terminen de salir del taller se vayan a sus pos iniciales
            
    }

    public void moverActualizandoCoord(){
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
        if (tin.mapa[fila][columna] == 1){ //tin es lo que me permite acceder al mapa que está en control, lo pongo de atributo. 
            fila = filaAntes; 
            columna = columnaAntes; 
        }
        else {
            tin.mapa[filaAntes][columnaAntes] = 0; //Desocupa la posición anterior del hp
            tin.mapa[fila][columna] = 1; //Marca la nueva posición del hp como ocupada
            move(); //siempre que movamos el robot con este metodo (moverActualizandoCoord) tendremos las coordenadas del hp 

        }
        
                
    }

    public void irAN(){

        while (columna != 19 || fila != 35 ){
            
            if (columna == 17 && fila == 32){
                turnLeft(); 
            }
            if (columna == 17 && fila == 34){
                turnRight(); 
            }
            if (columna == 20 && fila == 34){
                turnLeft(); 
            }
            if (columna == 20 && fila == 35){
                turnLeft(); 
            }

            moverActualizandoCoord();

        }

    }
    public void irAE(){

        while(columna != 11 || fila != 1 ){

            if (columna == 16 && fila == 32){
                turnRight(); 
            }
            if (columna == 16 && fila == 29){
                turnRight(); 
            }
            if (columna == 15 && fila == 29){
                turnLeft(); 
            }
            if (columna == 15 && fila == 26){
                turnRight(); 
            }
            if (columna == 13 && fila == 26){
                turnLeft(); 
            }
            if (columna == 13 && fila == 23){
                turnRight(); 
            }
            if (columna == 11 && fila == 23){
                turnLeft(); 
            }
            if (columna == 11 && fila == 18){
                turnLeft(); 
            }
            if (columna == 16 && fila == 18){
                turnRight(); 
            }
            if (columna == 16 && fila == 11){
                turnRight(); 
            }
            if (columna == 13 && fila == 11){
                turnLeft(); 
            }
            if (columna == 13 && fila == 5){
                turnRight(); 
            }
            if (columna == 12 && fila == 5){
                turnLeft(); 
            }
            if (columna == 12 && fila == 2){
                turnRight(); 
            }
            if (columna == 10 && fila == 2){
                turnLeft(); 
            }
            if (columna == 10 && fila == 1){
                turnLeft(); 
            }

            moverActualizandoCoord();

        }

    }
    public void irASJ(){
        while (columna != 1 || fila != 16 ){
            
            if (columna == 16 && fila == 32){
                turnRight(); 
            }
            if (columna == 16 && fila == 29){
                turnRight(); 
            }
            if (columna == 15 && fila == 29){
                turnLeft(); 
            }
            if (columna == 15 && fila == 26){
                turnRight(); 
            }
            if (columna == 13 && fila == 26){
                turnLeft(); 
            }
            if (columna == 13 && fila == 23){
                turnRight(); 
            }
            if (columna == 11 && fila == 23){
                turnLeft(); 
            }
            if (columna == 11 && fila == 14){
                turnRight();
            }
            if (columna == 7 && fila == 14){
                turnRight();
            }
            if (columna == 7 && fila == 15){
                turnLeft();
            }
            if (columna == 2 && fila == 15){
                turnRight();
            }
            if (columna == 2 && fila == 17){
                turnLeft();
            }
            if (columna == 1 && fila == 17){
                turnLeft();
            }

            moverActualizandoCoord();

        }

    }
    public void irASA(){

        while(columna != 15 || fila != 14){
            
            if (columna == 1 && fila == 14){
                turnLeft(); 
            }
            if (columna == 6 && fila == 14){
                turnRight(); 
            }
            if (columna == 6 && fila == 13){
                turnLeft(); 
            }
            if (columna == 14 && fila == 13){
                turnLeft(); 
            }
            if (columna == 14 && fila == 14){
                turnRight(); 
            }
            moverActualizandoCoord(); 

        }
        turnLeft();
        turnLeft(); //Debe girar 180 grados porque san antonio no tiene devuelta. 
    }
    public void rutaA(){

        while (columna != 19 || fila != 35 && tin.son420 == true){
            
            if (columna == 13 && fila == 1){
                turnLeft(); 
            }
            if (columna == 13 && fila == 4){
                turnRight();
            }
            if (columna == 14 && fila == 4){
                turnLeft(); 
            }
            if (columna == 14 && fila == 10){
                turnRight(); 
            }
            if (columna == 17 && fila == 10){
                turnLeft(); 
            }
            if (columna == 17 && fila == 19){
                turnLeft(); 
            }
            if (columna == 12 && fila == 19){
                turnRight(); 
            }
            if (columna == 12 && fila == 22){
                turnRight(); 
            }
            if (columna == 14 && fila == 22){
                turnLeft(); 
            }
            if (columna == 14 && fila == 25){
                turnRight(); 
            }
            if (columna == 16 && fila == 25){
                turnLeft(); 
            }
            if (columna == 16 && fila == 28){
                turnRight(); 
            }
            if (columna == 17 && fila == 28){
                turnLeft(); 
            }
            if (columna == 17 && fila == 34){
                turnRight(); 
            }
            if (columna == 20 && fila == 34){
                turnLeft(); 
            }
            if (columna == 20 && fila == 35){
                turnLeft(); 
            }
            moverActualizandoCoord(); 

            //Hasta aquí llega de estrella a niquia, falta code de ruta completa

        }
    }

}









