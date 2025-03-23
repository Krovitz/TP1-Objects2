package opp2.ejer1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private String nombre;
    private LocalDate fechaAbiertoInscripcion;
    private LocalDate fechaCerradoInscripcion;
    private List<Inscripcion> inscriptos;

    public Concurso(String nombre, LocalDate fechaAbiertoInscripcion, LocalDate fechaCerradoInscripcion) {
        this.nombre = nombre;
        this.fechaAbiertoInscripcion = fechaAbiertoInscripcion;
        this.fechaCerradoInscripcion = fechaCerradoInscripcion;
        this.inscriptos = new ArrayList<>();
    }

    private boolean fechaValida(LocalDate unaFecha) {
        boolean estado = false;
        if (unaFecha.isAfter(fechaAbiertoInscripcion.minusDays(1)) && unaFecha.isBefore(fechaCerradoInscripcion))
            estado = true;
        return estado;
    }

    public void inscribirParticipante(Inscripcion unaInscripcion, LocalDate fechaInscripcion) {
        if (fechaValida(fechaInscripcion)) {
            inscriptos.add(unaInscripcion);
            if (fechaInscripcion.equals(fechaAbiertoInscripcion))
                unaInscripcion.primerDia();
        } else
            throw new IllegalArgumentException("Fecha fuera del limite");
    }

    public boolean participanteInscripto(Participante participante) {
        boolean existe = false;
        int i = 0;
        List<Inscripcion> l = this.inscriptos;
        while (!existe && i < l.size()) {
            if (l.get(i).estaInscripto(participante)) {
                existe = true;
            } else {
                i++;
            }
        }
        return existe;
    }

    int cantidadInscriptos() {
        return inscriptos.size();
    }
}
