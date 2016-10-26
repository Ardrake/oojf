package paieMaster;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author acooke
 * Class object département
 */
public class departement {
    
    public String monId;//data member (also instance variable)  
    public String monNom;//data member(also instance variable)  
    private BigDecimal monTauxHoraire;
     
    public departement() {
        this.monId = "9999";
        this.monNom = "aucun Dept";
        this.monTauxHoraire = new BigDecimal(0);
    }
    
    public departement(String id, String nom, BigDecimal taux){
        this.monId = id;
        this.monNom = nom;
        this.monTauxHoraire = taux;
    }
    
    public BigDecimal getTaux(){
        return this.monTauxHoraire;
    }
    
       
}
