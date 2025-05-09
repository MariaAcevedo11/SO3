package Trabajos;

import java.util.ArrayList;
import java.util.List;

public class RutaFactory {
    public static List<Coordenada> getRuta(String nombre) {
        List<Coordenada> ruta = new ArrayList<>();

        switch (nombre) {
            case "rutaAN": // Ejemplo Niquía → La Estrella
                ruta.add(new Coordenada(32, 16)); // punto de inicio
                ruta.add(new Coordenada(34, 17)); // curva
                ruta.add(new Coordenada(35, 19)); // destino
                break;

            case "rutaASJ": // San Javier
                ruta.add(new Coordenada(32, 16));
                ruta.add(new Coordenada(29, 16));
                ruta.add(new Coordenada(26, 15));
                ruta.add(new Coordenada(23, 13));
                ruta.add(new Coordenada(16, 1)); // destino San Javier
                break;

            case "rutaAE": // La Estrella
                ruta.add(new Coordenada(32, 16));
                ruta.add(new Coordenada(29, 16));
                ruta.add(new Coordenada(26, 15));
                ruta.add(new Coordenada(1, 11)); // destino La Estrella
                break;

            case "rutaATaller":
                ruta.add(new Coordenada(32, 16)); // punto central San Antonio
                ruta.add(new Coordenada(34, 15)); // entrada al taller
                ruta.add(new Coordenada(35, 1));  // destino dentro del taller
                break;


            default:
                throw new IllegalArgumentException("Ruta no reconocida: " + nombre);
        }

        return ruta;
    }

    public static List<Coordenada> getRutaDeVueltaAlTaller(Coordenada origen) {
        List<Coordenada> ruta = new ArrayList<>();
        ruta.add(origen);
        ruta.add(new Coordenada(32, 16)); // nodo central
        ruta.add(new Coordenada(34, 15)); // entrada
        ruta.add(new Coordenada(35, 1));  // taller
        return ruta;
    }


}
