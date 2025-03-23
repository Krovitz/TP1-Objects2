package opp2.ejer1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void inscribirParticipanteTest() {
        Participante participante = new Participante("Tomas", "123");
        Concurso concurso1 = new Concurso("Truco", LocalDate.of(2025, 05, 03), LocalDate.of(2025, 05, 21));
        Concurso concurso2 = new Concurso("Tenis", LocalDate.of(2025, 03, 10), LocalDate.of(2025, 03, 26));

        concurso1.inscribirParticipante(new Inscripcion(participante, concurso1), LocalDate.of(2025, 05, 15));
        assertEquals(1, concurso1.cantidadInscriptos());
        assertTrue(concurso1.participanteInscripto(participante));
    }

    @Test
    public void inscribirPrimerDiaTest() {
        Participante participante = new Participante("Tomas", "123");
        Concurso concurso1 = new Concurso("Truco", LocalDate.of(2025, 05, 03), LocalDate.of(2025, 05, 21));

        concurso1.inscribirParticipante(new Inscripcion(participante, concurso1), LocalDate.of(2025, 05, 03));
        assertEquals(10, participante.getPuntos());
    }

    @Test
    public void inscribirFueraLimiteFechaTest() {
        Participante participante = new Participante("Tomas", "123");
        Concurso concurso1 = new Concurso("Truco", LocalDate.of(2025, 05, 03), LocalDate.of(2025, 05, 21));

        //Inscripcion antes de abrir la fecha de inscripcion
        Exception exceptionFecha = assertThrows(IllegalArgumentException.class, () ->
                concurso1.inscribirParticipante(new Inscripcion(participante, concurso1), LocalDate.of(2025, 5, 02))
        );
        assertEquals("Fecha fuera del limite", exceptionFecha.getMessage());

        //Inscripcion despues de cerrar la fecha de inscripcion
        exceptionFecha = assertThrows(IllegalArgumentException.class, () ->
                concurso1.inscribirParticipante(new Inscripcion(participante, concurso1), LocalDate.of(2025, 5, 22))
        );
        assertEquals("Fecha fuera del limite", exceptionFecha.getMessage());
    }
}
