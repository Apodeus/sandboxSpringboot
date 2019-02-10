package fr.pfe.visgen.web_components;

import org.springframework.stereotype.Service;

@Service
public class ReductionService {

    private final float reduction;

    public ReductionService(){
        this.reduction = 0.2f;
    }

    public Integer getPriceWithPromotion(Integer price){
        return price - (int)(price * reduction);
    }

}
