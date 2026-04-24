package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.List;

public class KühlschrankDomainService {

    
    public List<Item> abgelaufeneEntsorgenUndNachkaufen(Kühlschrank kühlschrank,
                                                        Einkaufsliste einkaufsliste) {
        List<Item> abgelaufene = kühlschrank.getAbgelaufeneItems();

        for (Item item : abgelaufene) {
            einkaufsliste.schreibeAuf(item.getMenge().getAnzahl(), item.getLebensmittel().getName());
            kühlschrank.itemVerbrauchen(item.getId(), item.getMenge().getAnzahl());
        }

        return abgelaufene;
    }

    public Kühlschrank einkaufVerarbeiten(Einkaufsliste einkaufsliste,
                                   Kühlschrank kühlschrank,
                                   Lebensmittel lebensmittel,
                                   Haltbarkeitsdatum haltbarkeit,
                                   int anzahl,
                                   Einheit einheit) {
        einkaufsliste.eingekauft(anzahl, lebensmittel.getName());

        Item item = new Item(lebensmittel, haltbarkeit, anzahl, einheit);
        kühlschrank.itemHinzufuegen(item);
        return kühlschrank;
    }
}
