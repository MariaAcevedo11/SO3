package Trabajos;

import kareltherobot.*;

public class main {
    public static void main(String[] args) {
        // Cargar mundo Karel
        World.readWorld("MetroMed.kwld");
        World.setVisible(true);
        World.setDelay(0);

        // Inicializar componentes principales
        MapaConcurrencia mapa = new MapaConcurrencia(36, 21);
        SanAntonioB sanAntonioB = new SanAntonioB();
        LineaC lineaC = new LineaC();
        RelojVirtual reloj = new RelojVirtual();
        Control control = new Control();
        AdministradorDeTaller admin = new AdministradorDeTaller(control, reloj, mapa, sanAntonioB, lineaC);

        // Iniciar trenes que salen del taller a las estaciones
        admin.lanzarPrimerosTrenes();

        // Monitorear la activación del cierre y enviar últimos trenes
        admin.monitorearCierreYEnviarUltimosTrenes();

        // Iniciar el reloj virtual
        reloj.start();

        // Escuchar señal de cierre desde teclado
        CierreManager.escucharPorTeclado();
    }
}
