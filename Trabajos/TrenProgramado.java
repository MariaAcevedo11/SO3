package Trabajos;

import java.util.concurrent.atomic.AtomicBoolean;

public class TrenProgramado implements Runnable, RelojListener {
    private final Tren tren;
    private final Coordenada destinoInicial;
    private final RelojVirtual reloj;

    private final AtomicBoolean listoParaSalir = new AtomicBoolean(false);

    public TrenProgramado(Tren tren, Coordenada destinoInicial, RelojVirtual reloj) {
        this.tren = tren;
        this.destinoInicial = destinoInicial;
        this.reloj = reloj;
    }

    @Override
    public void run() {
        reloj.agregarListener(this);



        System.out.println("🚈 " + tren.getRutaNombre() + " va hacia estación inicial: " + destinoInicial.fila + "," + destinoInicial.columna);
        tren.avanzarHacia(destinoInicial); // Se mueve hasta la estación
        System.out.println("🚉 " + tren.getRutaNombre() + " llegó a estación y espera 4:20...");

        while (!listoParaSalir.get()) {
            dormir(500); // espera pasiva hasta que reloj dispare evento
        }

        tren.iniciarRuta();
    }

    @Override
    public void actualizarHora(int hora, int minuto) {
        if (hora == 4 && minuto == 20) {
            listoParaSalir.set(true);
            System.out.println("🕓 Reloj marca 4:20 - Tren " + tren.getRutaNombre() + " iniciará su recorrido.");
        }
    }

    private void dormir(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
