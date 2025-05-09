package Trabajos;

import java.util.concurrent.locks.ReentrantLock;

public class MapaConcurrencia {
    private final int filas;
    private final int columnas;
    private final boolean[][] ocupado;
    private final ReentrantLock[][] locks;

    public MapaConcurrencia(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.ocupado = new boolean[filas][columnas];
        this.locks = new ReentrantLock[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
    }

    public boolean ocupar(int fila, int columna) {
        if (fueraDeRango(fila, columna)) return false;

        ReentrantLock lock = locks[fila][columna];
        lock.lock();
        try {
            if (!ocupado[fila][columna]) {
                ocupado[fila][columna] = true;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void liberar(int fila, int columna) {
        if (fueraDeRango(fila, columna)) return;

        ReentrantLock lock = locks[fila][columna];
        lock.lock();
        try {
            ocupado[fila][columna] = false;
        } finally {
            lock.unlock();
        }
    }

    public boolean estaOcupado(int fila, int columna) {
        if (fueraDeRango(fila, columna)) return true;

        ReentrantLock lock = locks[fila][columna];
        lock.lock();
        try {
            return ocupado[fila][columna];
        } finally {
            lock.unlock();
        }
    }

    public boolean estaLibre(Coordenada c) {
        return !estaOcupado(c.fila, c.columna);
    }

    private boolean fueraDeRango(int fila, int columna) {
        return fila < 0 || fila >= filas || columna < 0 || columna >= columnas;
    }
}
