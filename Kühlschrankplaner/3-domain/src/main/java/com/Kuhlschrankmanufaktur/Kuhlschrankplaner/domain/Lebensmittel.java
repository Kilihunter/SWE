package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public final class Lebensmittel {

    private final String name;

    @Enumerated(EnumType.STRING)
    private final Kategorie kategorie;

    protected Lebensmittel() {
        this.name = null;
        this.kategorie = null;
    }

    public Lebensmittel(String name, Kategorie kategorie) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Der Name des Lebensmittels darf nicht leer sein.");
        }
        if (kategorie == null) {
            throw new IllegalArgumentException("Kategorie darf nicht null sein.");
        }

        this.name = name;
        this.kategorie = kategorie;
    }

    public String getName() {
        return name;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lebensmittel that = (Lebensmittel) o;
        return Objects.equals(name, that.name) &&
               kategorie == that.kategorie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, kategorie);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, kategorie);
    }
}
