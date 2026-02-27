package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.util.HashMap;
import java.util.Map;

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
    private Map<Lebensmittel, Integer> lebensmittel;
    // lebensmittel mit stückangabe 

    Einkaufsliste(){
        lebensmittel = new HashMap<>();
    }

    protected void lebensmittelHinzufügen(Lebensmittel neuesLebensmittel, int anzahl) {
        if (neuesLebensmittel != null && !lebensmittel.containsKey(neuesLebensmittel)) {
            lebensmittel.put(neuesLebensmittel, anzahl);
        }
    }
}
