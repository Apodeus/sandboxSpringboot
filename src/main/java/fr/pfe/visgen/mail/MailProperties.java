package fr.pfe.visgen.mail;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailProperties {

    private final String mail;

    private final String pwd;

    public MailProperties(@Value("${pfe.visgen.mail}") String mail,
                          @Value("${pfe.visgen.pswd}") String pwd) {
        checkMail(mail);
        this.mail = mail;
        this.pwd = pwd;
    }

    private void checkMail(String mail){
        System.out.println("checking mail ...");
        if(!EmailValidator.getInstance().isValid(mail)){
            System.out.println("not valid mail ...");
        } else {

            System.out.println("It's a valid mail !");
        }
    }

    public String getMail() {
        return mail;
    }

    public String getPwd() {
        return pwd;
    }
}
