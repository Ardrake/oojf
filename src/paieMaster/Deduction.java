/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paieMaster;

import java.math.BigDecimal;

/**
 * @author acooke
 * Class des déductions 
 */
public class Deduction {
    
    BigDecimal salaireBrute = new BigDecimal(0);
    BigDecimal TauxHoraire = new BigDecimal(0);
    BigDecimal hrsTravailler = new BigDecimal(0);
    
    public Deduction (BigDecimal salaireBrut){
        this.salaireBrute = salaireBrut;
    }
    
    public BigDecimal getDeductionRRC(){
        BigDecimal TotalDeduction = new BigDecimal(0);
        TotalDeduction = salaireBrute.multiply(new BigDecimal(0.0495));
        return TotalDeduction;
    }
    
    public BigDecimal getDeductionAssEmploie(){
        BigDecimal TotalDeduction = new BigDecimal(0);
        TotalDeduction = salaireBrute.multiply(new BigDecimal(0.0198));
        return TotalDeduction;
    }
    
    public BigDecimal getDeductionAssEmploieEmp(){
        BigDecimal TotalDeduction = new BigDecimal(0);
        TotalDeduction = salaireBrute.multiply(new BigDecimal(0.0277));
        return TotalDeduction;
    }

    public BigDecimal getSalaireBrut(){
        return this.salaireBrute;
    }
}
