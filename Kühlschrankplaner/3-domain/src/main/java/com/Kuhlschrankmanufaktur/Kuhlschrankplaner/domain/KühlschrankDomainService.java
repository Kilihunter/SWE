package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KühlschrankDomainService {

    
    public void abgelaufeneEntsorgenUndNachkaufen(List<Kühlschrank> kühlschränke,
                                                        Einkaufsliste einkaufsliste) {
        for (Kühlschrank kühlschrank : kühlschränke) {
            List<Item> abgelaufene = kühlschrank.getAbgelaufeneItems();

            for (Item item : abgelaufene) {
                einkaufsliste.schreibeAuf(item.getAnzahl(), item.getLebensmittel());
                kühlschrank.itemVerbrauchen(item, item.getAnzahl());
            }
        }
    }

    public Kühlschrank einkaufVerarbeiten( Einkaufsliste einkaufsliste, Kühlschrank kühlschrank, Item item) {

        einkaufsliste.eingekauft(
                item.getAnzahl(),
                item.getLebensmittel()
        );

        kühlschrank.itemHinzufuegen(item);

        return kühlschrank;
    }
}
