package opp2.concurso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private static int contadorId = 1;
    private String nombre;
    private int id;
    private LocalDate fechaAbiertoInscripcion;
    private LocalDate fechaCerradoInscripcion;
    private List<Participante> inscriptos;
    private RegistroInscripto registroInscripto;
    private MailService servicioMail;

    public Concurso(String nombre, LocalDate fechaAbiertoInscripcion, LocalDate fechaCerradoInscripcion, RegistroInscripto registroInscripto, MailService servicioMail) {
        this.nombre = nombre;
        this.fechaAbiertoInscripcion = fechaAbiertoInscripcion;
        this.fechaCerradoInscripcion = fechaCerradoInscripcion;
        this.id = contadorId++;
        this.inscriptos = new ArrayList<>();
        this.registroInscripto = registroInscripto;
        this.servicioMail = servicioMail;
    }

    private boolean fechaValida(LocalDate unaFecha) {
        boolean estado = false;
        if (isEqualOrAfter(unaFecha, fechaAbiertoInscripcion) && isEqualOrBefore(unaFecha, fechaCerradoInscripcion))
            estado = true;
        return estado;
    }

    public void inscribirParticipante(Participante participante, LocalDate fechaInscripcion) {
        if (fechaValida(fechaInscripcion)) {
            if (!(participanteInscripto(participante))) {
                inscriptos.add(participante);
                registroInscripto.registro(fechaInscripcion, participante.id(), this.id);
                servicioMail.sendMail(participante.nombre(), nombre);
                if (fechaInscripcion.equals(fechaAbiertoInscripcion))
                    participante.sumarPuntos(10);
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

    public boolean participanteInscripto(Participante unParticipante) {
        boolean existe = false;
        int i = 0;
        List<Participante> l = this.inscriptos;
        while (!existe && i < l.size()) {
            if (l.get(i).equals(unParticipante)) {
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

    void setRegistroInscripto(RegistroInscripto registroInscripto) {
        this.registroInscripto = registroInscripto;
    }
}
