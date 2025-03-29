package opp2.ejer1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private static int contadorId = 1;
    private String nombre;
    private int id;
    private LocalDate fechaAbiertoInscripcion;
    private LocalDate fechaCerradoInscripcion;
    private List<Inscripcion> inscriptos;
    private RegistroInscripto registroInscripto;
    private MailService servicioMail = new MessageInscripto();

    public Concurso(String nombre, LocalDate fechaAbiertoInscripcion, LocalDate fechaCerradoInscripcion, RegistroInscripto registroInscripto) {
        this.nombre = nombre;
        this.fechaAbiertoInscripcion = fechaAbiertoInscripcion;
        this.fechaCerradoInscripcion = fechaCerradoInscripcion;
        this.id = contadorId++;
        this.inscriptos = new ArrayList<>();
        this.registroInscripto = registroInscripto;
        //this.servicioMail = servicioMail;
    }

    private boolean fechaValida(LocalDate unaFecha) {
        boolean estado = false;
        if (isEqualOrAfter(unaFecha, fechaAbiertoInscripcion) && isEqualOrBefore(unaFecha, fechaCerradoInscripcion))
            estado = true;
        return estado;
    }

    public void inscribirParticipante(Inscripcion unaInscripcion, LocalDate fechaInscripcion) {
        if (fechaValida(fechaInscripcion)) {
            if (!(participanteInscripto(unaInscripcion))) {
                inscriptos.add(unaInscripcion);
                registroInscripto.registro(fechaInscripcion, unaInscripcion.idParticipante(), this.id);
                servicioMail.sendMail(unaInscripcion.nombreParticipante(), nombre);
                if (fechaInscripcion.equals(fechaAbiertoInscripcion))
                    unaInscripcion.primerDia();
            } else
                throw new RuntimeException("Participante ya inscripto");
        } else
            throw new IllegalArgumentException("Fecha fuera del limite");
    }

    public boolean isEqualOrAfter(LocalDate l1, LocalDate l2) {
        return l1.isEqual(l2) || l1.isAfter(l2);
    }

    public boolean isEqualOrBefore(LocalDate l1, LocalDate l2) {
        return l1.isEqual(l2) || l1.isBefore(l2);
    }

    public boolean participanteInscripto(Inscripcion unaInscripcion) {
        boolean existe = false;
        int i = 0;
        List<Inscripcion> l = this.inscriptos;
        while (!existe && i < l.size()) {
            if (l.get(i).equals(unaInscripcion)) {
                existe = true;
            } else {
                i++;
            }
        }
        return existe;
    }

    int cantidadInscriptos() {
        return inscriptos.size();
    }
}
