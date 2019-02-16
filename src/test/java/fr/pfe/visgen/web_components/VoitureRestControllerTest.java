package fr.pfe.visgen.web_components;

import fr.pfe.visgen.VoitureException;
import fr.pfe.visgen.dao.MotoDAO;
import fr.pfe.visgen.dao.VoitureDAO;
import fr.pfe.visgen.mail.MailService;
import fr.pfe.visgen.pojo.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoitureRestControllerTest {

    @Mock
    private VoitureDAO voitureDAO;

    @Mock
    private MotoDAO motoDAO;

    @Mock
    private MailService mailService;

    private VoitureRestController restController;

    @BeforeEach
    void setUp(){
        this.restController = new VoitureRestController(new ReductionService(), mailService,  voitureDAO, motoDAO);
    }

    @Test
    void it_should_apply_20percent_reduction(){
        //given a car with price 5000 $
        long id = 1L;
        Optional<Voiture> opt = Optional.of(new Voiture(1L, "test", "tset", 5000));
        when(voitureDAO.findById(any())).thenReturn(opt);
        //when route with promotion apply is called
        Voiture voitureWithReduction = restController.getVoitureWithReduction(id);
        //then price should be equal to 20% off
        assertThat(voitureWithReduction.getPrice()).isEqualTo(4000);
    }

    @Test
    void it_should_throw_an_exception_id_not_existing(){
        //given no car in DAO
        when(voitureDAO.findById(any())).thenReturn(Optional.empty());
        //when get on a car using id is called
        //then it should throw an exception
        assertThatThrownBy(() -> restController.getVoiture(1L))
                .hasMessage("Voiture not found with id : 1")
                .isInstanceOf(VoitureException.class);
    }
}
