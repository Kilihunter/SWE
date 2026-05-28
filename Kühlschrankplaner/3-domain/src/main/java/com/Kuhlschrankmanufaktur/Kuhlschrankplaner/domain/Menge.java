package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Embeddable
public final class Menge {

    private final int anzahl;
    @Enumerated(EnumType.STRING)
    private final Einheit einheit;

    protected Menge() {
        this.anzahl = 0;
        this.einheit = null;
    }

    public Menge(int anzahl, Einheit einheit) {
        if (anzahl <= 0) {
            throw new IllegalArgumentException("Die Anzahl darf nicht null oder negativ sein.");
        }
        if (einheit == null) {
            throw new IllegalArgumentException("Einheit darf nicht null sein.");
        }

        this.anzahl = anzahl;
        this.einheit = einheit;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public Einheit getEinheit() {
        return einheit;
    }

    public boolean istLeer() {
        return anzahl == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menge menge = (Menge) o;
        return anzahl == menge.anzahl && einheit == menge.einheit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anzahl, einheit);
    }

    @Override
    public String toString() {
        return anzahl + " " + (einheit != null ? einheit : "");
    }
}
