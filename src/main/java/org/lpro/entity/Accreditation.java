package org.lpro.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Accreditation {
    public Commande commande;
    public Sandwich sandwich;
     public Accreditation() {
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

   
    
    
}
