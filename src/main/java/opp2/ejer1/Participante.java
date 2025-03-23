package opp2.ejer1;

import java.util.Objects;

public class Participante {
    private String dni;
    private String nombre;
    private int puntos = 0;

    public Participante(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public void sumarPuntos(int cantidadPuntos) {
        this.puntos = cantidadPuntos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(nombre, that.nombre);
    }

    public int getPuntos() {
        return puntos;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
