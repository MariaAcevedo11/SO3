package Trabajos;

public class Coordenada {
    public final int fila;
    public final int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordenada)) return false;
        Coordenada o = (Coordenada) obj;
        return this.fila == o.fila && this.columna == o.columna;
    }

    @Override
    public int hashCode() {
        return 31 * fila + columna;
    }
}
