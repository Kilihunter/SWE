package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;

@Entity
public class Einkaufsliste {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "einkaufsliste_eintraege", joinColumns = @JoinColumn(name = "einkaufsliste_id"))
    @MapKeyColumn(name = "lebensmittel_name")
    @Column(name = "anzahl")
    private Map<String, Integer> eintraege = new HashMap<>();

    protected Einkaufsliste() {
    }

    public Einkaufsliste(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Die Einkaufsliste braucht einen Namen.");
        }
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }

    public void schreibeAuf(int anzahl, String lebensmittelName) {
        if (lebensmittelName == null || lebensmittelName.isBlank()) {
             throw new IllegalArgumentException("Lebensmittelname darf nicht leer sein.");
        }
        if (anzahl <= 0) {
             throw new IllegalArgumentException("Anzahl muss größer als 0 sein.");
        }
        eintraege.put(lebensmittelName, eintraege.getOrDefault(lebensmittelName, 0) + anzahl);
    }

    public void eingekauft(int anzahl, String lebensmittelName) {
        if (lebensmittelName == null || lebensmittelName.isBlank() || anzahl <= 0) return;

        if (eintraege.containsKey(lebensmittelName)) {
            int aktuelleAnzahl = eintraege.get(lebensmittelName);
            int neueAnzahl = aktuelleAnzahl - anzahl;

            if (neueAnzahl <= 0) {
                eintraege.remove(lebensmittelName);
            } else {
                eintraege.put(lebensmittelName, neueAnzahl);
            }
        }
    }

    public Map<String, Integer> getEintraege() {
        return Collections.unmodifiableMap(eintraege);
    }
}
