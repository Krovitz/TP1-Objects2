package opp2.persistencia;

import opp2.ejer1.RegistroInscripto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class EnDiscoRegistroInscripto implements RegistroInscripto {
    private String path;

    public EnDiscoRegistroInscripto(String path) {
        this.path = path;
    }

    @Override
    public void registro(LocalDate fechaInscripcion, int idParticipante, int idConcurso) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = fechaInscripcion.format(format) + ", " + idParticipante + ", " + idConcurso;

        try {
            Files.write(Path.of(this.path),
                    Collections.singletonList(text),
                    StandardOpenOption.APPEND, // Agrega al final del archivo
                    StandardOpenOption.CREATE); // Crea el archivo si no existe
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la informacion");
        }
    }
}
