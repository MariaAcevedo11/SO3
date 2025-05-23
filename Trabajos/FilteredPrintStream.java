package Trabajos;

import java.io.OutputStream;
import java.io.PrintStream;

public class FilteredPrintStream extends PrintStream {

    public FilteredPrintStream(OutputStream out) {
        super(out);
    }

    private boolean isRobotMessage(String s) {
        return s != null && s.contains("RobotID");
    }

    @Override
    public void print(String s) {
        if (!isRobotMessage(s)) {
            super.print(s);
        }
    }

    @Override
    public void println(String s) {
        if (!isRobotMessage(s)) {
            super.println(s);
        }
    }

    @Override
    public void println(Object obj) {
        if (!isRobotMessage(obj == null ? null : obj.toString())) {
            super.println(obj);
        }
    }

    @Override
    public void print(Object obj) {
        if (!isRobotMessage(obj == null ? null : obj.toString())) {
            super.print(obj);
        }
    }

    // También podés sobrecargar write si la clase usa eso
    @Override
    public void write(byte[] buf, int off, int len) {
        String s = new String(buf, off, len);
        if (!isRobotMessage(s)) {
            super.write(buf, off, len);
        }
    }

    @Override
    public void write(int b) {
        // Puede dejar vacío para evitar caracteres sueltos, o implementarlo según convenga
    }
}