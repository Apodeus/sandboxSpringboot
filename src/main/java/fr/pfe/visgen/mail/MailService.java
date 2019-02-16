package fr.pfe.visgen.mail;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender emailSender;
    private final MailProperties mailProperties;

    public MailService(JavaMailSender emailSender, MailProperties mailProperties) {
        this.emailSender = emailSender;
        this.mailProperties = mailProperties;
    }

    public String sendEmail(String targetMail){
        if(!EmailValidator.getInstance().isValid(targetMail)){
            return "Wrong target mail pattern";
        }
        System.out.println(mailProperties.getMail() + " is sending mail to " + targetMail);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(targetMail);
        msg.setSubject("Test mail service impl");
        msg.setText("Hello, It's a mail sent using Java Spring");

        emailSender.send(msg);

        return "Email sent !";
    }
}
