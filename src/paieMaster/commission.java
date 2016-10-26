package paieMaster;

import java.math.BigDecimal;

/**
 * @author andre
 * calcul des commission
 */
public class commission {
    
    static BigDecimal calculCommission(double ventes){
        double totalCommission = 0;
        
        if (ventes > 7000){
                totalCommission = ((ventes - 7000) * 0.05) + 68.75 + 26.25 ;
        } else if (ventes > 4250){
                totalCommission = ((ventes - 4250) * 0.025) + 26.25 ;
        } else if (ventes > 2500) {
                totalCommission = ((ventes - 2500) * 0.015)  ;
        }
        
        return BigDecimal.valueOf(totalCommission);
    }
    
}
