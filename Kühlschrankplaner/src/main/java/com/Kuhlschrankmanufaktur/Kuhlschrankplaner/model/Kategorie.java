package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Kategorie {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique=true, nullable=false)
    private String name;
    
    @OneToMany(mappedBy = "kategorie")
    private List<Lebensmittel> lebensmittel;
    
    public Kategorie(String name) {
        this.name = name;
        lebensmittel = new ArrayList<>();
    }

    protected Kategorie(){}

    protected void lebensmittelHinzufügen(Lebensmittel newlebensmittel) {
        if (!this.lebensmittel.contains(newlebensmittel)) {
            this.lebensmittel.add(newlebensmittel);
            newlebensmittel.kategorieAendern(this);
        }
    }

    
}
