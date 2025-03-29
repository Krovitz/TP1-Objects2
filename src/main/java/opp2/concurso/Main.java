package opp2.concurso;

import opp2.persistencia.EnDataBaseRegistroInscripto;
import opp2.persistencia.EnDiscoRegistroInscripto;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Escritura en disco. Crea archivo si no existe en el Escritorio
        //Si no existe escribe en la ultima linea
        String path = "C:/Users/Tomas/Desktop/Pruebas-Inscripciones.txt";
        Participante participante = new Participante("Tomas");
        Participante participante2 = new Participante("Tobias");
        Concurso concurso = new Concurso("Truco", LocalDate.now(), LocalDate.now().plusDays(10),
                new EnDiscoRegistroInscripto(path), new MailMessageInscripto());

        concurso.inscribirParticipante(participante, LocalDate.now());

        //Escritura en la BD
        concurso.setRegistroInscripto(new EnDataBaseRegistroInscripto());
        concurso.inscribirParticipante(participante2, LocalDate.now());

    }
}
