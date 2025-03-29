package opp2.concurso;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailMessageInscripto implements MailService {
    
    @Override
    public void sendMail(String nombreParticipante, String nombreConcurso) {
        // provide recipient's email ID
        String to = "tomikpp_roldan_186@hotmail.com";
        // provide sender's email ID
        String from = "john.doe@your.domain";

        // provide account credentials
        final String username = "ec5a861d3241ae";
        final String password = "fae61a839d4fc9";

        // provide host address
        String host = "sandbox.smtp.mailtrap.io";

        // configure SMTP details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // create the mail Session object
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // create a MimeMessage object
            Message message = new MimeMessage(session);
            // set From email field
            message.setFrom(new InternetAddress(from));
            // set To email field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // set email subject field
            message.setSubject("Inscripcion a concurso");
            // set the content of the email message
            message.setText("Hola " + nombreParticipante + ", tu inscripcion al concurso '" + nombreConcurso + "' se ha realizado exitosamente!");

            // send the email message
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
