package fr.pfe.visgen;

import fr.pfe.visgen.web_components.VoitureRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = VoitureRestController.class)
public class VoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoitureApplication.class, args);
    }

}

