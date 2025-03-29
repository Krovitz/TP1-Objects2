package opp2.concurso;

import java.util.Objects;

public class Participante {
    private static int contadorId = 1;
    private final int id;
    private final String nombre;
    private int puntos = 0;

    public Participante(String nombre) {
        this.nombre = nombre;
        this.id = contadorId++;
    }

    public void sumarPuntos(int cantidadPuntos) {
        this.puntos = cantidadPuntos;
    }

    int id() {
        return id;
    }

    String nombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(nombre, that.nombre);
    }

    int puntos() {
        return puntos;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
