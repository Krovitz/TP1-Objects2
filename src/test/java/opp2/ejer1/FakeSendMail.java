package opp2.ejer1;

public class FakeSendMail implements MailService {
    private String mensajeMail;

    @Override
    public void sendMail(String nombreParticipante, String nombreConcurso) {
        this.mensajeMail = "Hola " + nombreParticipante + ", tu inscripcion al concurso '" + nombreConcurso + "' se ha realizado exitosamente!";
    }

//    public String mensajeMail{
//        return mensajeMail
//    }
}
