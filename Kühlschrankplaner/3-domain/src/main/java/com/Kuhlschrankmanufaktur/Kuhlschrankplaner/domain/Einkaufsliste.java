package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;

@Entity
public class Einkaufsliste {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "einkaufsliste_eintraege", joinColumns = @JoinColumn(name = "einkaufsliste_id"))
    @MapKeyColumn(name = "lebensmittel_name")
    @Column(name = "anzahl")
    private Map<Lebensmittel, Integer> eintraege = new HashMap<>();

    protected Einkaufsliste() {
    }

    public Einkaufsliste(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Die Einkaufsliste braucht einen Namen.");
        }
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }

    public void schreibeAuf(int anzahl, Lebensmittel lebensmittel) {
        if (lebensmittel == null) {
             throw new IllegalArgumentException("Lebensmittel darf nicht null sein.");
        }
        if (anzahl <= 0) {
             throw new IllegalArgumentException("Anzahl muss größer als 0 sein.");
        }
        eintraege.put(lebensmittel, eintraege.getOrDefault(lebensmittel, 0) + anzahl);
    }

    public void eingekauft(int anzahl, Lebensmittel lebensmittel) {
        if (lebensmittel == null || anzahl <= 0) {
            throw new IllegalArgumentException("Ungültige Eingabe für eingekauft: " + anzahl + " " + lebensmittel);

        }

        if (!eintraege.containsKey(lebensmittel)) {
            throw new IllegalArgumentException("Lebensmittel '" + lebensmittel.getName() + "' steht nicht auf der Einkaufsliste.");
        }

        int aktuelleAnzahl = eintraege.get(lebensmittel);
        int neueAnzahl = aktuelleAnzahl - anzahl;

        if (neueAnzahl <= 0) {
            eintraege.remove(lebensmittel);
        } else {
            eintraege.put(lebensmittel, neueAnzahl);
        }
    }

    public Map<Lebensmittel, Integer> getEintraege() {
        return Collections.unmodifiableMap(eintraege);
    }
}
