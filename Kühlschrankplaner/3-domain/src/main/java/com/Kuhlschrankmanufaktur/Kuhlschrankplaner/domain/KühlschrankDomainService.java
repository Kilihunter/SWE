package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.List;

public class KühlschrankDomainService {

    
    public void abgelaufeneEntsorgenUndNachkaufen(Kühlschrank kühlschrank,
                                                        Einkaufsliste einkaufsliste) {
        List<Item> abgelaufene = kühlschrank.getAbgelaufeneItems();

        for (Item item : abgelaufene) {
            einkaufsliste.schreibeAuf(item.getMenge().getAnzahl(), item.getLebensmittel().getName());
            kühlschrank.itemVerbrauchen(item.getId(), item.getMenge().getAnzahl());
        }
    }

    public Kühlschrank einkaufVerarbeiten( Einkaufsliste einkaufsliste, Kühlschrank kühlschrank, Item item) {

        einkaufsliste.eingekauft(
                item.getMenge().getAnzahl(),
                item.getLebensmittel().getName()
        );

        kühlschrank.itemHinzufuegen(item);

        return kühlschrank;
    }
}
