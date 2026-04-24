package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.List;

/**
 * Domain Service – kapselt fachliche Logik, die MEHRERE Aggregate betrifft.
 *
 * Warum Domain Service und nicht Methode auf einem Aggregate?
 * → Der Kuehlschrank kennt keine Einkaufsliste und umgekehrt.
 *   Aber der fachliche Prozess "abgelaufene Items entsorgen und nachkaufen"
 *   oder "Einkauf verarbeiten" übergreift beide Aggregate.
 *   Diese Logik gehört weder in Kuehlschrank noch in Einkaufsliste,
 *   sondern in einen Domain Service.
 *
 * Kein Spring-Annotation – gehört in die Domain-Schicht.
 * Bean-Erzeugung erfolgt in der Plugin-Schicht (BeanConfig).
 */
public class KühlschrankDomainService {

    /**
     * Fachlicher Prozess: Abgelaufene Items aus dem Kühlschrank entsorgen
     * und automatisch auf die Einkaufsliste setzen.
     *
     * Koordiniert zwei Aggregate (Kuehlschrank + Einkaufsliste).
     * Gibt die Liste der entsorgten Items zurück.
     */
    public List<Item> abgelaufeneEntsorgenUndNachkaufen(Kühlschrank kuehlschrank,
                                                        Einkaufsliste einkaufsliste) {
        List<Item> abgelaufene = kuehlschrank.getAbgelaufeneItems();

        for (Item item : abgelaufene) {
            einkaufsliste.schreibeAuf(item.getMenge().getAnzahl(), item.getLebensmittel().getName());
            kuehlschrank.itemVerbrauchen(item.getId(), item.getMenge().getAnzahl());
        }

        return abgelaufene;
    }

    /**
     * Fachlicher Prozess: Einen Einkauf verarbeiten.
     * Nimmt ein Lebensmittel von der Einkaufsliste und legt es in den Kühlschrank.
     *
     * Koordiniert zwei Aggregate (Einkaufsliste + Kuehlschrank).
     */
    public Kühlschrank einkaufVerarbeiten(Einkaufsliste einkaufsliste,
                                   Kühlschrank kuehlschrank,
                                   Lebensmittel lebensmittel,
                                   Haltbarkeitsdatum haltbarkeit,
                                   int anzahl) {
        einkaufsliste.eingekauft(anzahl, lebensmittel.getName());

        Item item = new Item(lebensmittel, haltbarkeit, anzahl);
        kuehlschrank.itemHinzufuegen(item);
        return kuehlschrank;
    }
}
