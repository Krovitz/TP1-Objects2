package opp2.concurso;

import java.time.LocalDate;

public interface RegistroInscripto {

    void registro(LocalDate fechaInscripcion, int idParticipante, int idConcurso);
}
