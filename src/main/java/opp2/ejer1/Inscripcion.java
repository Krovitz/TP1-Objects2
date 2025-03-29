package opp2.ejer1;

import java.util.Objects;

public class Inscripcion {
    private Participante participante;
    private Concurso concurso;

    public Inscripcion(Participante unPartipante, Concurso unConcurso) {
        this.participante = unPartipante;
        this.concurso = unConcurso;
    }

    void primerDia() {
        participante.sumarPuntos(10);
    }

    public int idParticipante() {
        return participante.id();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Inscripcion that)) return false;
        return Objects.equals(participante, that.participante);
    }

    public String nombreParticipante() {
        return participante.nombre();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(participante);
    }
}
