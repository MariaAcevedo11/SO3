package Trabajos;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CierreManager {
    private static final AtomicBoolean cierreActivo = new AtomicBoolean(false);

    public static boolean isCierreActivo() {
        return cierreActivo.get();
    }

    public static void iniciarCierre() {
        cierreActivo.set(true);
        System.out.println("🛑 Cierre iniciado. Se activará operación final del metro.");
    }

    public static void escucharPorTeclado() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("11pm")) {
                    iniciarCierre();
                    break;
                }
            }
        }, "CierreListener").start();
    }
}
