package opp2.persistencia;

import opp2.restaurante.RegistroCosto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class EnDiscoRegistroPagos implements RegistroCosto {
    private String path;

    public EnDiscoRegistroPagos(String path) {
        this.path = path;
    }

    @Override
    public void registro(LocalDate fecha, float costoTotal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String text = fecha.format(formatter) + " || " + costoTotal;

        try {
            Files.write(Path.of(this.path),
                    Collections.singletonList(text),
                    StandardOpenOption.APPEND, // Agrega al final del archivo
                    StandardOpenOption.CREATE); // Crea el archivo si no existe
            System.out.println("Inscripción guardada: " + text);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
