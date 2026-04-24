package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.util.*;
import jakarta.persistence.*;

@Entity
public class Einkaufsliste {

    @Id
    @GeneratedValue
    private Long id;


    @ElementCollection
    @CollectionTable(name = "einkaufsliste_einträge", joinColumns = @JoinColumn(name = "einkaufsliste_id"))
    @MapKeyColumn(name = "lebensmittel_name")
    @Column(name = "anzahl")
    private Map<String, Integer> einträge = new HashMap<>();

    public Einkaufsliste() {
    }

    public Long getId() { return id; }


    public void schreibeAuf(int anzahl, Lebensmittel lebensmittel) {
        if (lebensmittel == null || lebensmittel.getName().isBlank()) {
             throw new IllegalArgumentException("Lebensmittelname darf nicht leer sein.");
        }
        if (anzahl <= 0) {
             throw new IllegalArgumentException("Anzahl muss größer als 0 sein.");
        }
        String lebensmittelName = lebensmittel.getName();
        einträge.put(lebensmittelName, einträge.getOrDefault(lebensmittelName, 0) + anzahl);
    }

    public void eingekauft(int anzahl, Lebensmittel lebensmittel) {
        if (lebensmittel == null || lebensmittel.getName().isBlank() || anzahl <= 0) return;
        String lebensmittelName = lebensmittel.getName();
        
        if (einträge.containsKey(lebensmittelName)) {
            int aktuelleAnzahl = einträge.get(lebensmittelName);
            int neueAnzahl = aktuelleAnzahl - anzahl;
            if (neueAnzahl <= 0) {
                einträge.remove(lebensmittelName);
            } else {
                einträge.put(lebensmittelName, neueAnzahl);
            }
        }
    }

    
    public Map<String, Integer> getEinträge() {
        return Collections.unmodifiableMap(einträge);
    }
}