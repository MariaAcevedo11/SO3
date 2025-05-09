package Trabajos;

import kareltherobot.*;
import java.awt.Color;
import java.util.List;

public class Tren extends Robot implements Runnable, Directions {
    private final String rutaNombre;
    private MapaConcurrencia mapa;
    private List<Coordenada> ruta;
    private SanAntonioB sanAntonioBControl;
    private LineaC lineaC;
    private RelojVirtual reloj;

    private int fila, columna;
    private boolean asignado = false;

    public Tren(int street, int avenue, Direction dir, int beeps, Color color,
                String rutaNombre, Control control) {
        super(street, avenue, dir, beeps, color);
        this.fila = street;
        this.columna = avenue;
        this.rutaNombre = rutaNombre;
        World.setupThread(this);
    }

    public String getRutaNombre() {
        return rutaNombre;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void asignar(MapaConcurrencia mapa, SanAntonioB sanAntonioB,
                        LineaC lineaC, RelojVirtual reloj) {
        this.mapa = mapa;
        this.sanAntonioBControl = sanAntonioB;
        this.lineaC = lineaC;
        this.reloj = reloj;
        this.ruta = RutaFactory.getRuta(rutaNombre);
        this.asignado = true;
    }

//    public void avanzarHacia(Coordenada destino) {
//        while (fila != destino.fila || columna != destino.columna) {
//            if (fila < destino.fila && !facingNorth()) faceNorth();
//            else if (fila > destino.fila && !facingSouth()) faceSouth();
//            else if (columna < destino.columna && !facingEast()) faceEast();
//            else if (columna > destino.columna && !facingWest()) faceWest();
//
//            moverSeguro();
//        }
//    }

    public void esperarHastaHora(int hora, int minuto) {
        while (reloj.getHora() < hora || (reloj.getHora() == hora && reloj.getMinuto() < minuto)) {
            dormir(500); // revisa cada 0.5s
        }
    }


    public void iniciarRuta() {
        run(); // simplemente delega al método original
    }

    public void volverAlTaller() {
        System.out.println("🏁 Tren " + rutaNombre + " iniciando regreso al taller.");

        List<Coordenada> vuelta = RutaFactory.getRutaDeVueltaAlTaller(new Coordenada(fila, columna));
        for (Coordenada paso : vuelta) {
            avanzarHacia(paso);
        }

        System.out.println("🏁 Tren " + rutaNombre + " llegó al taller. Fin del día.");
    }


    public void avanzarHacia(Coordenada destino) {

        if (CierreManager.isCierreActivo() && esEstacionFinal(destino)) {
            System.out.println("🚧 Tren " + rutaNombre + " llegó a estación final tras cierre. Terminando ruta.");
            return;
        }


        while (fila != destino.fila || columna != destino.columna) {
            if (fila < destino.fila && !facingNorth()) faceNorth();
            else if (fila > destino.fila && !facingSouth()) faceSouth();
            else if (columna < destino.columna && !facingEast()) faceEast();
            else if (columna > destino.columna && !facingWest()) faceWest();

            moverSeguro();
        }

        if (esSanAntonioB(destino)) {
            sanAntonioBControl.entrar(rutaNombre);
            esperarEnEstacion();
            girar180();
            sanAntonioBControl.salir(rutaNombre);
        } else if (esEstacion(destino)) {
            esperarEnEstacion();
        }

        if (esEstacionFinal(destino) && reloj.getHora() >= 23) {
            System.out.println("🛑 Tren " + rutaNombre + " llegó a estación final tras cierre. Terminando ruta.");
        }
    }

    private void moverSeguro() {
        int nuevaFila = fila;
        int nuevaColumna = columna;

        if (facingNorth()) nuevaFila++;
        else if (facingSouth()) nuevaFila--;
        else if (facingEast()) nuevaColumna++;
        else if (facingWest()) nuevaColumna--;

        Coordenada nueva = new Coordenada(nuevaFila, nuevaColumna);

        CruceManager.entrarCruce(nueva);
        boolean pudoOcupar = mapa.ocupar(nuevaFila, nuevaColumna);
        if (pudoOcupar) {
            mapa.liberar(fila, columna);
            fila = nuevaFila;
            columna = nuevaColumna;
            move();
        } else {
            dormir(50); // no pudo ocupar, espera
        }
        CruceManager.salirCruce(nueva); // liberar aunque no se haya movido
    }



    private void esperarEnEstacion() {
        System.out.println("🕒 Tren " + rutaNombre + " esperando en estación (" + fila + "," + columna + ")");
        dormir(3000);
    }

    private void dormir(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Usa el manager central de estaciones para reglas definidas
    private boolean esEstacion(Coordenada c) {
        return EstacionManager.esEstacion(c);
    }

    private boolean esSanAntonioB(Coordenada c) {
        return EstacionManager.esSanAntonioB(c);
    }

    private boolean esEstacionFinal(Coordenada c) {
        return EstacionManager.esLaEstrella(c) ||
                EstacionManager.esSanJavier(c) ||
                EstacionManager.esNiquia(c);
    }

    private boolean esEntradaLineaC(Coordenada c) {
        return c.columna == 14 && c.fila == 26;
    }

    private boolean esSalidaLineaC(Coordenada c) {
        return c.columna == 13 && c.fila == 23;
    }

    private void girar180() {
        turnLeft();
        turnLeft();
    }

    private void faceNorth() { while (!facingNorth()) turnLeft(); }
    private void faceSouth() { while (!facingSouth()) turnLeft(); }
    private void faceEast()  { while (!facingEast())  turnLeft(); }
    private void faceWest()  { while (!facingWest())  turnLeft(); }
}
