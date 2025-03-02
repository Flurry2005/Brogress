package se.aljr.application.programplanner;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {
    private String username;
    private String password;
    private Session session;
    private String host;
    private String port;

    public EmailSender() {
        host = "smtp.gmail.com"; // SMTP server
        port = "587";            // Port number
        username = "brogressworkouttracker@gmail.com";
        password = "ojmm sejk aknw hznc";
        init();
    }

    public void init() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendEmail(String address, String workoutTitle, Image workout, File imagePath) {
        try {
            Message message = new MimeMessage(session);
            // SENDER
            message.setFrom(new InternetAddress(username));
            // RECEIVER
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
            message.setSubject(workoutTitle);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Here is your exported workout.\n Have a great day! \uD83D\uDCAA");
            multipart.addBodyPart(textPart);

            MimeBodyPart imageAttachment = new MimeBodyPart();
            imageAttachment.attachFile(imagePath);
            multipart.addBodyPart(imageAttachment);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
