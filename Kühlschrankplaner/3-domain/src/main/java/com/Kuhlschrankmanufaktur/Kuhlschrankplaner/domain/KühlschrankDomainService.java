package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class KühlschrankDomainService {

    
    public void abgelaufeneEntsorgenUndNachkaufen(List<Kühlschrank> kühlschränke,
                                                        Einkaufsliste einkaufsliste) {
        for (Kühlschrank kühlschrank : kühlschränke) {
            List<Item> abgelaufene = kühlschrank.getAbgelaufeneItems();

            for (Item item : abgelaufene) {
                if (!einkaufsliste.getEintraege().containsKey(item.getLebensmittel())) {
                    einkaufsliste.schreibeAuf(item.getAnzahl(), item.getLebensmittel());
                }
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
