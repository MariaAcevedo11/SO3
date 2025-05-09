package Trabajos;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class RelojVirtual extends Thread {
    private int hora = 4;
    private int minuto = 0;
    private boolean terminado = false;

    private final List<RelojListener> listeners = new CopyOnWriteArrayList<>();

    public void agregarListener(RelojListener listener) {
        listeners.add(listener);
    }

    public void terminarOperacion() {
        terminado = true;
    }

    public void run() {
        while (!terminado && (hora < 23 || (hora == 23 && minuto == 0))) {
            notificarListeners();

            try {
                Thread.sleep(1000); // 1 segundo = 1 minuto simulado
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            minuto++;
            if (minuto >= 60) {
                minuto = 0;
                hora++;
            }
        }

        System.out.println("🛑 Reloj detenido. Hora límite alcanzada o terminación manual.");
    }

    private void notificarListeners() {
        for (RelojListener listener : listeners) {
            listener.actualizarHora(hora, minuto);
        }
    }

    public int getHora() { return hora; }
    public int getMinuto() { return minuto; }
}
