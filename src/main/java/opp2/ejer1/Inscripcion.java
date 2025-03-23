package opp2.ejer1;

public class Inscripcion {
    private Participante participante;
    private Concurso concurso;

    public Inscripcion(Participante unPartipante, Concurso unConcurso) {
        this.participante = unPartipante;
        this.concurso = unConcurso;
    }

    boolean estaInscripto(Participante participante) {
        return this.participante.equals(participante);
    }

    void primerDia() {
        participante.sumarPuntos(10);
    }
}
