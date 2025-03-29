package opp2.ejer1;

import java.util.Objects;

public class Participante {
    private static int contadorId = 1;
    private String dni;
    private int id;
    private String nombre;
    private int puntos = 0;

    public Participante(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.id = contadorId++;
    }

    public void sumarPuntos(int cantidadPuntos) {
        this.puntos = cantidadPuntos;
    }

    public int id() {
        return id;
    }

    public String nombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(nombre, that.nombre);
    }

    public int Puntos() {
        return puntos;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
