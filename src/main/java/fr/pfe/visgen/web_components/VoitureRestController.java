package fr.pfe.visgen.web_components;

import fr.pfe.visgen.VoitureException;
import fr.pfe.visgen.dao.VoitureDAO;
import fr.pfe.visgen.pojo.Voiture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class VoitureRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoitureRestController.class);

    private final VoitureDAO voitureDAO;
    private final ReductionService reductionService;

    public VoitureRestController(ReductionService reductionService, VoitureDAO voitureDAO){
        this.reductionService = reductionService;
        this.voitureDAO = voitureDAO;
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

}
