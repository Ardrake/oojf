package paieMaster;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author acooke
 * Class Federal extends Impot
 */
public class Federal extends Impots{

    public Federal(BigDecimal salaireBrut) {
        super(salaireBrut);
        
        setPremTranche(new BigDecimal(35000));
        setDeuxTranche(new BigDecimal(35000));
        setPremTaux(new BigDecimal(0.16));
        setDeuxTaux(new BigDecimal(0.22));
    }
}

