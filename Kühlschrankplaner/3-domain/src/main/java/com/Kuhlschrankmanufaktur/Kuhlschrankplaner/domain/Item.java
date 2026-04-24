package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Lebensmittel lebensmittel;

    @Embedded
    private Haltbarkeitsdatum haltbarkeit;

    @Embedded
    private Menge menge;

    @ManyToOne
    @JoinColumn(name = "kuehlschrank_id")
    private Kühlschrank kühlschrank;

    protected Item() { }

    public Item(Lebensmittel lebensmittel, Haltbarkeitsdatum haltbarkeit, int anzahl) {
        validateState(lebensmittel, haltbarkeit, anzahl);

        this.lebensmittel = lebensmittel;
        this.haltbarkeit = haltbarkeit;
        this.menge = new Menge(anzahl, lebensmittel.getEinheit());
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
            throw new IllegalArgumentException("Die neue Anzahl muss größer als 0 sein. Zum Entfernen bitte Löschfunktion nutzen.");
        }
        this.menge = new Menge(neueAnzahl, this.lebensmittel.getEinheit());
    }

    public void verlaengereHaltbarkeit(Haltbarkeitsdatum neuesDatum) {
        if (neuesDatum == null || neuesDatum.istAbgelaufen()) {
            throw new IllegalArgumentException("Ungültiges neues Haltbarkeitsdatum.");
        }
        this.haltbarkeit = neuesDatum;
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

    public Long getId() { return id; }
    public Lebensmittel getLebensmittel() { return lebensmittel; }
    public Haltbarkeitsdatum getHaltbarkeit() { return haltbarkeit; }
    public Menge getMenge() { return menge; }
    public Kühlschrank getKühlschrank() { return kühlschrank; }

    void setKühlschrank(Kühlschrank kühlschrank) {
        this.kühlschrank = kühlschrank;
    }
}
