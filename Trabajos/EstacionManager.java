package Trabajos;

import java.util.HashSet;
import java.util.Set;

public class EstacionManager {

    private static final Set<Coordenada> estaciones = new HashSet<>();

    static {
        estaciones.add(new Coordenada(35, 19)); // Niquía
        estaciones.add(new Coordenada(1, 11));   // La Estrella
        estaciones.add(new Coordenada(16, 1));   // San Javier
        estaciones.add(new Coordenada(14, 16));  // San Antonio A
        estaciones.add(new Coordenada(14, 17));  // San Antonio A
        estaciones.add(new Coordenada(13, 13));  // San Antonio B
        estaciones.add(new Coordenada(14, 13));  // San Antonio B
    }

    public static boolean esEstacion(Coordenada c) {
        return estaciones.contains(c);
    }

    public static boolean esNiquia(Coordenada c) {
        return c.fila == 35 && c.columna == 19;
    }

    public static boolean esLaEstrella(Coordenada c) {
        return c.fila == 1 && c.columna == 11;
    }

    public static boolean esSanJavier(Coordenada c) {
        return c.fila == 16 && c.columna == 1;
    }

    public static boolean esSanAntonioA(Coordenada c) {
        return (c.columna == 16 || c.columna == 17) && c.fila == 14;
    }

    public static boolean esSanAntonioB(Coordenada c) {
        return c.columna == 13 && (c.fila == 13 || c.fila == 14);
    }
}
