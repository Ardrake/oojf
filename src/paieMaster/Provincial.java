package paieMaster;

import java.math.BigDecimal;

/**
 * @author acooke
 * Classe Provincial extends Impots
 */
public class Provincial extends Impots{

    public Provincial(BigDecimal salaireBrut) {
        super(salaireBrut);

        setPremTranche(new BigDecimal(35000));
        setDeuxTranche(new BigDecimal(35000));
        setPremTaux(new BigDecimal(0.0605));
        setDeuxTaux(new BigDecimal(0.0725));
    }
}
