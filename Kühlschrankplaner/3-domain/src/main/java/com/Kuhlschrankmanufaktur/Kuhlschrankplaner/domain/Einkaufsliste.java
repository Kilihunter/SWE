package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Einkaufsliste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "einkaufsliste_eintraege", joinColumns = @JoinColumn(name = "einkaufsliste_id"))
    @MapKeyJoinColumn(name = "lebensmittel_name")
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
        eintraege.put(lebensmittel, anzahl);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Einkaufsliste e)) return false;
        return id != null && id.equals(e.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

