package opp2.ejer1;

import opp2.persistencia.EnDiscoRegistroInscripto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void inscribirParticipanteTest() {
        FakeRegistroDeInscripcion registro = new FakeRegistroDeInscripcion();
        Participante participante = new Participante("Tomas", "123");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), registro);
        Inscripcion inscripcion = new Inscripcion(participante, concurso1);

        LocalDate fechaInscripto = LocalDate.now().plusDays(2);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        concurso1.inscribirParticipante(inscripcion, fechaInscripto);

        assertEquals(1, concurso1.cantidadInscriptos());
        assertTrue(concurso1.participanteInscripto(inscripcion));
        assertTrue(registro.startWith(formato.format(fechaInscripto)));
    }

    @Test
    public void inscribirPrimerDiaTest() {
        String path = "C:/Users/Tomas/Desktop/Pruebas-Inscripciones.txt";
        Participante participante = new Participante("Tomas", "123");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), new EnDiscoRegistroInscripto(path));

        concurso1.inscribirParticipante(new Inscripcion(participante, concurso1), LocalDate.now());
        assertEquals(10, participante.Puntos());
    }

    @Test
    public void inscribirFueraLimiteFechaTest() {
        Participante participante = new Participante("Tomas", "123");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), new FakeRegistroDeInscripcion());

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

    @Test
    public void ParticipanteYaInscriptoTest() {
        Participante participante = new Participante("Tomas", "123");
        Concurso concurso1 = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10), new FakeRegistroDeInscripcion());
        Inscripcion inscripcion = new Inscripcion(participante, concurso1);

        concurso1.inscribirParticipante(inscripcion, LocalDate.now().plusDays(5));
        Exception exceptionYaInscripto = assertThrows(RuntimeException.class, () ->
                concurso1.inscribirParticipante(new Inscripcion(participante, concurso1), LocalDate.now().plusDays(6))
        );
        assertEquals("Participante ya inscripto", exceptionYaInscripto.getMessage());
    }
}
