package Trabajos;

public class AdministradorDeTaller {
    private final Control control;
    private final RelojVirtual reloj;
    private final MapaConcurrencia mapa;
    private final SanAntonioB sanAntonioB;
    private final LineaC lineaC;

    public AdministradorDeTaller(Control control, RelojVirtual reloj, MapaConcurrencia mapa,
                                 SanAntonioB sanAntonioB, LineaC lineaC) {
        this.control = control;
        this.reloj = reloj;
        this.mapa = mapa;
        this.sanAntonioB = sanAntonioB;
        this.lineaC = lineaC;
    }

    private boolean estacionOcupada(Coordenada coordenada, MapaConcurrencia mapa) {
        return mapa.estaOcupado(coordenada.fila, coordenada.columna);
    }

    public void lanzarPrimerosTrenes() {
        Tren aNiquia = control.obtenerTrenRuta("rutaAN", mapa, sanAntonioB, lineaC, reloj);
        Tren aEstrella = control.obtenerTrenRuta("rutaAE", mapa, sanAntonioB, lineaC, reloj);
        Tren aSanJavier = control.obtenerTrenRuta("rutaASJ", mapa, sanAntonioB, lineaC, reloj);

        new Thread(new TrenProgramado(aNiquia, new Coordenada(35, 19), reloj)).start();
        new Thread(new TrenProgramado(aEstrella, new Coordenada(1, 11), reloj)).start();
        new Thread(new TrenProgramado(aSanJavier, new Coordenada(16, 1), reloj)).start();
    }

    public void monitorearCierreYEnviarUltimosTrenes() {
        new Thread(() -> {
            while (!CierreManager.isCierreActivo()) {
                dormir(500);
            }

            System.out.println("🚨 Enviando trenes finales post-cierre...");

            // Tren Niquía → La Estrella
            Tren trenAN = control.obtenerTrenRuta("rutaAN", mapa, sanAntonioB, lineaC, reloj);
            new Thread(() -> {
                trenAN.run(); // ejecuta la ruta
                trenAN.volverAlTaller();
            }).start();

            // Tren La Estrella → Niquía
            Tren trenAE = control.obtenerTrenRuta("rutaAE", mapa, sanAntonioB, lineaC, reloj);
            new Thread(() -> {
                trenAE.run();
                trenAE.volverAlTaller();
            }).start();

            // Esperar que San Antonio B esté libre antes de mandar tren a San Javier
            sanAntonioB.entrar("FinalSJ");
            Tren trenSJ = control.obtenerTrenRuta("rutaASJ", mapa, sanAntonioB, lineaC, reloj);
            new Thread(() -> {
                trenSJ.run();
                trenSJ.volverAlTaller();
            }).start();
            dormir(1000); // buffer
            sanAntonioB.salir("FinalSJ");

        }, "FinalCierreDispatcher").start();
    }


    private void dormir(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



//    public void lanzarPrimerosTrenes() {
//        Tren aNiquia = control.obtenerTrenRuta("rutaAN", mapa, sanAntonioB, lineaC, reloj);
//        Tren aEstrella = control.obtenerTrenRuta("rutaAE", mapa, sanAntonioB, lineaC, reloj);
//        Tren aSanJavier = control.obtenerTrenRuta("rutaASJ", mapa, sanAntonioB, lineaC, reloj);
//
//        new Thread(aNiquia).start();
//        new Thread(aEstrella).start();
//        new Thread(aSanJavier).start();
//    }
}
