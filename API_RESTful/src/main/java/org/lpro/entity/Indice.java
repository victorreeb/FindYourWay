package org.lpro.entity;

import java.io.Serializable;
import javax.persistence.Id;


import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author remaki
 */
@Entity
public class Indice implements Serializable {

  private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String text;

     @ManyToOne // va se référer à u message  qui correspond à mapBy
    @JsonBackReference //cassser le cycle
    private Destination destination;

     public Indice(){

     }

    public Indice(String text){

        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }



}
