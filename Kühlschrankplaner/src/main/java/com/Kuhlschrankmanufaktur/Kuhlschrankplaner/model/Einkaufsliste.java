package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class Einkaufsliste {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "lebensmittel_id")
    private List<Lebensmittel> lebensmittel;
    // lebensmittel mit stückangabe 

    Einkaufsliste(){
        lebensmittel = new ArrayList<>();
    }

    protected void lebensmittelHinzufügen(Lebensmittel neuesLebensmittel) {
        if (neuesLebensmittel != null && !lebensmittel.contains(neuesLebensmittel)) {
            lebensmittel.add(neuesLebensmittel);
        }
    }
}
