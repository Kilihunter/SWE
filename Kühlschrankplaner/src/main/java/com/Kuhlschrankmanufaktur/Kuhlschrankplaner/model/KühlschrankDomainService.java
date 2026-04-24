package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.util.List;

public class KühlschrankDomainService {

    
    public void abgelaufeneEntsorgenUndNachkaufen(Kühlschrank kuehlschrank,
                                                  Einkaufsliste einkaufsliste) {
        
        List<Item> abgelaufene = kuehlschrank.getAbgelaufeneItems();

        for (Item item : abgelaufene) {
            einkaufsliste.schreibeAuf(item.getMenge().getAnzahl(), item.getLebensmittel());
            
            kuehlschrank.itemVerbrauchen(item.getId(), item.getMenge().getAnzahl());
        }
    }

    
    public void einkaufVerarbeiten(Einkaufsliste einkaufsliste,
                                   Kühlschrank kuehlschrank,
                                   Lebensmittel lebensmittel,
                                   Haltbarkeitsdatum haltbarkeit,
                                   int anzahl) {
        
        einkaufsliste.eingekauft(anzahl, lebensmittel);

        Item item = new Item(lebensmittel, haltbarkeit, anzahl);
        kuehlschrank.itemHinzufuegen(item);
    }
}