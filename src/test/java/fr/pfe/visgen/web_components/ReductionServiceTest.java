package fr.pfe.visgen.web_components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReductionServiceTest {

    private ReductionService reductionService;

    @BeforeEach
    void setup(){
        reductionService = new ReductionService();
    }

    @Test
    void it_should_apply_20percent_off(){
        //given 100$
        int price = 100;
        //when reduction is applied using the service
        Integer priceWithPromotion = reductionService.getPriceWithPromotion(price);
        //then it should remove 20$ off
        assertThat(priceWithPromotion).isEqualTo(80);
    }

}