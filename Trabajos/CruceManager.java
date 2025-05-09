package Trabajos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class CruceManager {
    private static final Map<Coordenada, Semaphore> cruces = new ConcurrentHashMap<>();

    static {
        // Ejemplo de cruces críticos (debes adaptar según tu mapa)
        agregarCruce(32, 16); // Nodo central
        agregarCruce(23, 13); // Interconexión
        agregarCruce(14, 13); // San Antonio B
        agregarCruce(14, 16); // San Antonio A
        agregarCruce(29, 16); // Línea A - Línea B cruce
    }

    private static void agregarCruce(int fila, int columna) {
        cruces.put(new Coordenada(fila, columna), new Semaphore(1));
    }

    public static void entrarCruce(Coordenada c) {
        Semaphore sem = cruces.get(c);
        if (sem != null) {
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void salirCruce(Coordenada c) {
        Semaphore sem = cruces.get(c);
        if (sem != null) {
            sem.release();
        }
    }
}
