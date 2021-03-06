package fr.pfe.visgen.web_components;

import fr.pfe.visgen.MyTask;
import fr.pfe.visgen.VoitureException;
import fr.pfe.visgen.dao.MotoDAO;
import fr.pfe.visgen.dao.VoitureDAO;
import fr.pfe.visgen.mail.MailService;
import fr.pfe.visgen.pojo.Moto;
import fr.pfe.visgen.pojo.Voiture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

@RestController
public class VoitureRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoitureRestController.class);

    private final ReductionService reductionService;
    private final MailService mailService;
    private final VoitureDAO voitureDAO;
    private final MotoDAO motoDAO;

    public VoitureRestController(ReductionService reductionService,
                                 MailService mailService,
                                 VoitureDAO voitureDAO,
                                 MotoDAO motoDAO){
        this.reductionService = reductionService;
        this.mailService = mailService;
        this.voitureDAO = voitureDAO;
        this.motoDAO = motoDAO;

        Timer t = new Timer();
        MyTask task = new MyTask(voitureDAO);
        t.scheduleAtFixedRate(task, 0, 10000);
    }

    @GetMapping(value = "/voitures")
    public List<Voiture> getVoitures(){
        LOGGER.info("Getting all products in database ...");
        return voitureDAO.findAll();
    }

    @GetMapping(value = "/voitures/{id}")
    public Voiture getVoiture(@PathVariable(name = "id") Long id){
        LOGGER.info("Getting product with id {}", id);
        return voitureDAO.findById(id).orElseThrow(() -> new VoitureException("Voiture not found with id : " + id));
    }

    @GetMapping(value = "/voitures/{id}/reduction")
    public Voiture getVoitureWithReduction(@PathVariable(name = "id") Long id){
        LOGGER.info("Getting voiture with promotion with id {}", id);
        Voiture voiture = voitureDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("Voiture not found with id : " + id));
        Integer priceWithPromotion = reductionService.getPriceWithPromotion(voiture.getPrice());
        voiture.setPrice(priceWithPromotion);
        return voiture;
    }

    @GetMapping(value = "/filter/voitures/low/{price}")
    public List<Voiture> getVoituresLessThan(@PathVariable(name = "price") Integer price){
        return voitureDAO.findVoituresByPriceLessThanEqual(price);
    }

    @GetMapping(value = "/filter2/voitures/low/{price}")
    public List<Voiture> getVoituresLessThanUsingStream(@PathVariable(name = "price") Integer price){
        List<Voiture> allVoitures = voitureDAO.findAll();
        List<Voiture> result = allVoitures
                .stream()
                .filter(car -> car.getPrice() <= price)
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping(value = "/addMoto")
    public void addMoto(){
        motoDAO.save(new Moto("Suzuki"));
    }

    @GetMapping(value = "/addCar")
    @Transactional
    public void addCar(){
        voitureDAO.save(new Voiture("volvo", "golf", 1234));
    }

    @GetMapping(value = "/toto")
    public String sendMail(){
        return mailService.sendEmail("ordonez.romain@gmail.com");
    }
}
