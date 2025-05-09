package Trabajos;

import java.util.ArrayList;
import kareltherobot.*;
import java.awt.Color;

public class Control implements Directions {
    public ArrayList<Tren> metrosA = new ArrayList<>();
    public ArrayList<Tren> metrosB = new ArrayList<>();
    public int[][] mapa = new int[36][21];

    public Tren obtenerTrenRuta(String ruta, MapaConcurrencia mapa, SanAntonioB sanAntonioB,
                                LineaC lineaC, RelojVirtual reloj) {
        for (Tren tren : ruta.equals("rutaASJ") ? metrosB : metrosA) {
            if (tren.getRutaNombre().equals(ruta) && !tren.isAsignado()) {
                tren.asignar(mapa, sanAntonioB, lineaC, reloj);
                return tren;
            }
        }
        return null;
    }

    public Control() {
        int posColumna = 15;
        int posFila = 34;
        Direction tipoDireccion = Directions.North;
        int contador = 0;

        while (metrosA.size() < 22 || metrosB.size() < 10) {
            if (contador % 3 == 0) {
                Tren trenB = new Tren(posFila, posColumna, tipoDireccion, 0, Color.GREEN, "rutaASJ", this);
                metrosB.add(trenB);
            } else if (contador % 3 == 1) {
                Tren trenA = new Tren(posFila, posColumna, tipoDireccion, 0, Color.BLUE, "rutaAN", this);
                metrosA.add(trenA);
            } else if (contador % 3 == 2) {
                Tren trenA = new Tren(posFila, posColumna, tipoDireccion, 0, Color.BLUE, "rutaAE", this);
                metrosA.add(trenA);
            }

            mapa[posFila][posColumna] = 1;

            if (tipoDireccion == Directions.North) posFila++;
            else if (tipoDireccion == Directions.South) posFila--;
            else if (tipoDireccion == Directions.East) posColumna++;
            else if (tipoDireccion == Directions.West) posColumna--;

            if (posColumna == 15 && posFila == 35) tipoDireccion = Directions.West;
            if (posColumna == 1 && posFila == 35) tipoDireccion = Directions.South;
            if (posColumna == 1 && posFila == 34) tipoDireccion = Directions.East;
            if (posColumna == 14 && posFila == 34) tipoDireccion = Directions.South;
            if (posColumna == 14 && posFila == 32) tipoDireccion = Directions.East;

            contador++;
        }
    }
}
