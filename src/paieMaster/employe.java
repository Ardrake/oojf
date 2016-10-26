package paieMaster;

import java.math.BigDecimal;

/**
 * @author acooke
 * classe object employé
 */
public class employe {

    public int monId;
    public String monNom;
    public String monPrenom;
    public departement monDept;
    private BigDecimal monTauxHoraire;
    
    public employe(int id, String prenom, String nom, departement dept){
        this.monId = id;
        this.monNom = nom;
        this.monPrenom = prenom;
        this.monDept = dept;
        this.monTauxHoraire = dept.getTaux();
    }
    
    public void setTaux(BigDecimal taux){
        this.monTauxHoraire = taux;
    }
    
    public BigDecimal getTaux(){
        return this.monTauxHoraire;
    }
    
    public String getDept(){
        return this.monDept.monNom;
    }
    
    public String toString(){
        return "Prénom:" + this.monPrenom + " Nom:" + this.monNom;
    } 
    
    public Object[] toObjectArray() {
    return new Object[] { this.monId, this.monPrenom, this.monNom };
    }
} 


    
