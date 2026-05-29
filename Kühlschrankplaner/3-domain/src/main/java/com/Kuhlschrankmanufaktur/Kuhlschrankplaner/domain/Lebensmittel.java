package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public final class Lebensmittel {
    @Id
    private final String name;

    @Enumerated(EnumType.STRING)
    private Kategorie kategorie;

    @Enumerated(EnumType.STRING)
    private Einheit einheit;

    private Integer minMenge;

    protected Lebensmittel() {
        this.name = null;
        this.kategorie = null;
        this.einheit = null;
        this.minMenge = null;
    }

    public Lebensmittel(String name, Kategorie kategorie, Einheit einheit, Integer minMenge) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Der Name des Lebensmittels darf nicht leer sein.");
        }
        if (kategorie == null) {
            throw new IllegalArgumentException("Kategorie darf nicht null sein.");
        }
        if (einheit == null) {
            throw new IllegalArgumentException("Einheit darf nicht null sein.");
        }
        if (minMenge == null || minMenge < 0) {
            throw new IllegalArgumentException("Minimale Menge darf kleiner 0 sein.");
        }

        this.name = name;
        this.kategorie = kategorie;
        this.einheit = einheit;
        this.minMenge = minMenge; 
    }

    public Lebensmittel lebensmittelAnpassen(Kategorie neueKategorie, Einheit neueEinheit, Integer neueMinMenge) {
        if (neueKategorie == null) {
            throw new IllegalArgumentException("Kategorie darf nicht null sein.");
        }
        if (neueEinheit != einheit) {
            throw new IllegalArgumentException("Einheit darf sich nicht ändern würde sich auf die vorhandenen Items auswirken.");
        }
        if (neueMinMenge == null || neueMinMenge < 0) {
            throw new IllegalArgumentException("Minimale Menge darf nicht kleiner 0 sein.");
        }

        this.kategorie = neueKategorie;
        this.einheit = neueEinheit;
        this.minMenge = neueMinMenge; 
        return this;
    }

    public String getName() {
        return name;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public Integer getMinMenge() {
        return minMenge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lebensmittel l)) return false;
        return name != null && name.equals(l.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
