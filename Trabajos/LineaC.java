package Trabajos;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class LineaC {
    private final ReentrantLock lock = new ReentrantLock(true); // justo para evitar starvation
    private final Condition cambioDireccion = lock.newCondition();

    private String direccionActual = null;
    private int trenesEnLinea = 0;

    public boolean intentarEntrar(String direccion, String trenId) {
        lock.lock();
        try {
            while (direccionActual != null && !direccionActual.equals(direccion)) {
                System.out.println("⛔ Tren " + trenId + " esperando: Línea C en uso por dirección opuesta (" + direccionActual + ")");
                cambioDireccion.await();
            }

            direccionActual = direccion;
            trenesEnLinea++;
            System.out.println("🚦 Tren " + trenId + " entró a Línea C en dirección: " + direccion);
            return true;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void salir(String trenId) {
        lock.lock();
        try {
            trenesEnLinea--;
            System.out.println("✅ Tren " + trenId + " salió de Línea C. Quedan: " + trenesEnLinea);

            if (trenesEnLinea == 0) {
                direccionActual = null;
                cambioDireccion.signalAll(); // liberar trenes en la dirección opuesta
            }

        } finally {
            lock.unlock();
        }
    }
}
