package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Lebensmittel lebensmittel;

    @Embedded
    private Haltbarkeitsdatum haltbarkeit;

    private Integer anzahl;

    @ManyToOne
    @JoinColumn(name = "kuehlschrank_id")
    private Kühlschrank kühlschrank;

    protected Item() { }

    public Item(Lebensmittel lebensmittel, Haltbarkeitsdatum haltbarkeit, int anzahl) {
        validateState(lebensmittel, haltbarkeit, anzahl);

        this.lebensmittel = lebensmittel;
        this.haltbarkeit = haltbarkeit;
        this.anzahl = anzahl;
    }

    private void validateState(Lebensmittel lebensmittel, Haltbarkeitsdatum haltbarkeit, int anzahl) {
        if (lebensmittel == null) throw new IllegalArgumentException("Lebensmittel fehlt.");
        if (haltbarkeit == null) throw new IllegalArgumentException("Haltbarkeit fehlt.");
        if (anzahl <= 0) throw new IllegalArgumentException("Menge muss positiv sein.");

        if (haltbarkeit.istAbgelaufen()) {
            throw new IllegalArgumentException("Abgelaufene Lebensmittel können nicht neu hinzugefügt werden.");
        }
    }

    public void mengeAnpassen(int neueAnzahl) {
        if (neueAnzahl <= 0) {
            throw new IllegalArgumentException("Die neue Anzahl muss größer als 0 sein.");
        }
        this.anzahl = neueAnzahl;
    }

    public void lebensmittelAnpassen(Lebensmittel neuesLebensmittel) {
        if (neuesLebensmittel == null) {
            throw new IllegalArgumentException("Lebensmittel darf nicht null sein.");
        }
        this.lebensmittel = neuesLebensmittel;
    }
    public void korrigieren(Lebensmittel neuesLebensmittel, Haltbarkeitsdatum neueHaltbarkeit, int neueAnzahl, Einheit neueEinheit) {
         validateState(neuesLebensmittel, neueHaltbarkeit, neueAnzahl);
            this.lebensmittel = neuesLebensmittel;
            this.haltbarkeit = neueHaltbarkeit;
            this.anzahl = neueAnzahl;
    }
    public boolean istAbgelaufen() {
        return haltbarkeit.istAbgelaufen();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return id != null && id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() { return id; }
    public Lebensmittel getLebensmittel() { return lebensmittel; }
    public Haltbarkeitsdatum getHaltbarkeit() { return haltbarkeit; }
    public Integer getAnzahl() { return anzahl; }
    public Kühlschrank getKühlschrank() { return kühlschrank; }

    void setKühlschrank(Kühlschrank kühlschrank) {
        this.kühlschrank = kühlschrank;
    }
}
