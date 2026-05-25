package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
public class KühlschrankVerwaltungsService {

    private final KühlschrankRepository kühlschrankRepository;
    private final ItemFactory itemFactory;

    public KühlschrankVerwaltungsService(KühlschrankRepository kühlschrankRepository, ItemFactory itemFactory) {
        this.kühlschrankRepository = kühlschrankRepository;
        this.itemFactory = itemFactory;
    }

    public Kühlschrank kühlschrankAnlegen(String name) {
        Kühlschrank neuerKühlschrank = new Kühlschrank(name);
        return kühlschrankRepository.save(neuerKühlschrank);
    }

    public Kühlschrank getKühlschrank(Integer id) {
        return kühlschrankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kühlschrank mit ID " + id + " nicht gefunden."));
    }

    public List<Kühlschrank> getAlleKühlschränke() {
        return kühlschrankRepository.findAll();
    }

    public Kühlschrank itemHinzufügen(String lebensmittelName, String einheit, String kategorie, LocalDate haltbarBis, int anzahl, Integer kühlschrankId) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        Item item = itemFactory.erstelleItem(
                    lebensmittelName,
                    einheit,
                    kategorie,
                    haltbarBis,
                    anzahl
            );

        kühlschrank.itemHinzufuegen(item);
        return kühlschrankRepository.save(kühlschrank);
    }
    public Kühlschrank itemEntfernen(Integer kühlschrankId, Integer itemId) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        kühlschrank.itemEntfernen(itemId);
        return kühlschrankRepository.save(kühlschrank);
    }
    public Kühlschrank itemTeilweiseVerbraucht(Integer kühlschrankId, Integer itemId, int neueAnzahl) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        kühlschrank.itemVerbrauchen(itemId, neueAnzahl);
        return kühlschrankRepository.save(kühlschrank);
    }
    public Kühlschrank itemKorrigieren( Integer kühlschrankId, Integer itemId, String lebensmittelName, String einheit, String kategorie, LocalDate haltbarkeit, int anzahl) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        Item korrigiertesItem = itemFactory.erstelleItem( lebensmittelName, einheit, kategorie, haltbarkeit, anzahl);
        kühlschrank.itemKorrigieren(itemId, korrigiertesItem);
        return kühlschrankRepository.save(kühlschrank);
    }

}
