package opp2.concurso;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void inscribirParticipanteTest() {
        FakeRegistroDeInscripcion fakeRegistro = new FakeRegistroDeInscripcion();
        FakeSendMail fakeServicioMail = new FakeSendMail();
        Participante participante = new Participante("Tomas");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), fakeRegistro, fakeServicioMail);

        LocalDate fechaInscripto = LocalDate.now().plusDays(2);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        concurso1.inscribirParticipante(participante, fechaInscripto);

        assertEquals(1, concurso1.cantidadInscriptos());
        assertTrue(concurso1.participanteInscripto(participante));
        assertEquals(formato.format(fechaInscripto) + ", " + "1" + ", " + "1", fakeRegistro.content());
        assertEquals("Hola Tomas, tu inscripcion al concurso 'Truco' se ha realizado exitosamente!", fakeServicioMail.mensajeMail());
    }

    @Test
    public void inscribirPrimerDiaTest() {
        Participante participante = new Participante("Tomas");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), new FakeRegistroDeInscripcion(), new FakeSendMail());

        concurso1.inscribirParticipante(participante, LocalDate.now());
        assertEquals(10, participante.puntos());
    }

    @Test
    public void inscribirFueraLimiteFechaTest() {
        Participante participante = new Participante("Tomas");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), new FakeRegistroDeInscripcion(), new FakeSendMail());

        //Inscripcion antes de abrir la fecha de inscripcion
        Exception exceptionFecha = assertThrows(IllegalArgumentException.class, () ->
                concurso1.inscribirParticipante(participante, LocalDate.of(2025, 5, 2))
        );
        assertEquals("Fecha fuera del limite", exceptionFecha.getMessage());

        //Inscripcion despues de cerrar la fecha de inscripcion
        exceptionFecha = assertThrows(IllegalArgumentException.class, () ->
                concurso1.inscribirParticipante(participante, LocalDate.of(2025, 5, 22))
        );
        assertEquals("Fecha fuera del limite", exceptionFecha.getMessage());
    }

    @Test
    public void ParticipanteYaInscriptoTest() {
        Participante participante = new Participante("Tomas");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), new FakeRegistroDeInscripcion(), new FakeSendMail());

        concurso1.inscribirParticipante(participante, LocalDate.now().plusDays(5));
        Exception exceptionYaInscripto = assertThrows(RuntimeException.class, () ->
                concurso1.inscribirParticipante(participante, LocalDate.now().plusDays(6))
        );
        assertEquals("Participante ya inscripto", exceptionYaInscripto.getMessage());
    }
}
