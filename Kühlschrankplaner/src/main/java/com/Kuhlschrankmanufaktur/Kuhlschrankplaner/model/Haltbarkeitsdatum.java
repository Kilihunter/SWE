package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public final class Haltbarkeitsdatum {

    private final LocalDate datum;

    protected Haltbarkeitsdatum() {
        this.datum = null;
    }

    public Haltbarkeitsdatum(LocalDate datum) {
        if (datum == null) {
            throw new IllegalArgumentException("Haltbarkeitsdatum darf nicht null sein.");
        }
        // Hier könnte man fachliche Validierung ergänzen, z.B. kein Datum vor 1900
        this.datum = datum;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public boolean istAbgelaufen() {
        return datum.isBefore(LocalDate.now());
    }

  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Haltbarkeitsdatum that = (Haltbarkeitsdatum) o;
        return Objects.equals(datum, that.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datum);
    }

    @Override
    public String toString() {
        return datum.toString();
    }
}