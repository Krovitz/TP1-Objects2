package opp2.ejer1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FakeRegistroDeInscripcion implements RegistroInscripto {
    private String content;

    @Override
    public void registro(LocalDate fechaInscripcion, int idParticipante, int idConcurso) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = fechaInscripcion.format(format) + ", " + idParticipante + ", " + idConcurso;
        this.content = text;
    }

    public boolean startWith(String start) {
        return this.content.startsWith(start);
    }
}
