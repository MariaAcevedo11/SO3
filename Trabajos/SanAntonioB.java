package Trabajos;

import java.util.concurrent.Semaphore;

public class SanAntonioB {
    private final Semaphore plataforma = new Semaphore(1);

    public void entrar(String trenId) {
        try {
            System.out.println("🚦 Tren " + trenId + " esperando plataforma en San Antonio B...");
            plataforma.acquire(); // Espera hasta que esté libre
            System.out.println("✅ Tren " + trenId + " entró a San Antonio B.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void salir(String trenId) {
        System.out.println("⬅️ Tren " + trenId + " salió de San Antonio B.");
        plataforma.release(); // Libera para otro tren
    }
}
