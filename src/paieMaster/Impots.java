package paieMaster;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author acooke
 * class impots - extend Deduction
 */
public class Impots extends Deduction{

    private BigDecimal tauxPremierTranche = new BigDecimal(0);
    private BigDecimal tauxDeuxiemeTranche = new BigDecimal(0);
    private BigDecimal limitePremierTranche = new BigDecimal(0);
    private BigDecimal limiteDeuxiemeTranche = new BigDecimal(0);

    public Impots(BigDecimal salaireBrut) {
        super(salaireBrut);
        
    }
    
    public BigDecimal deductionImpot(){
        BigDecimal salaireAnnuel = this.salaireBrute.multiply(new BigDecimal(52));
        BigDecimal montantPremiereTranche = new BigDecimal(0);
        BigDecimal montantDeuxiemeTranche = new BigDecimal(0);
        
        if (salaireAnnuel.compareTo(limitePremierTranche) >= 0){
            montantPremiereTranche = limitePremierTranche.multiply(tauxPremierTranche);
        } else {
            montantPremiereTranche = salaireAnnuel.multiply(tauxPremierTranche);
        }
        
        if (salaireAnnuel.compareTo(limiteDeuxiemeTranche) > 0){
            montantDeuxiemeTranche = (salaireAnnuel.subtract(limitePremierTranche)).multiply(tauxDeuxiemeTranche);
        } 
        
        BigDecimal totalDeduction = (montantPremiereTranche.add(montantDeuxiemeTranche)).divide(new BigDecimal(52), RoundingMode.HALF_UP);
        
        //System.out.printf(TotalDeduction.toString() + "%n");
        
        return totalDeduction;
    }
   
    public void setPremTranche(BigDecimal montant){
        this.limitePremierTranche = montant;
    }
    
    public BigDecimal getPremTranche(){
        return this.limitePremierTranche;
    }  
    
    public void setDeuxTranche(BigDecimal montant){
    this.limiteDeuxiemeTranche = montant;
    }
    
    public BigDecimal getDeuxTranche(){
        return this.limiteDeuxiemeTranche;
    }  
     
    public void setPremTaux(BigDecimal taux){
    this.tauxPremierTranche = taux;
    }
    
    public BigDecimal getPremTaux(){
        return this.tauxPremierTranche;
    }  
    
    public void setDeuxTaux(BigDecimal taux){
        this.tauxDeuxiemeTranche = taux;
    }
    
    public BigDecimal getDeuxTaux(){
        return this.tauxDeuxiemeTranche;
    }  
}
