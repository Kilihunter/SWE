package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
public class KühlschrankVerwaltungsService {

    private final KühlschrankRepository kühlschrankRepository;
    private final ItemFactory itemFactory;
    private final LebensmittelVerwaltungsService lebensmittelVerwaltungsService;

    public KühlschrankVerwaltungsService(KühlschrankRepository kühlschrankRepository, ItemFactory itemFactory, LebensmittelVerwaltungsService lebensmittelVerwaltungsService) {
        this.kühlschrankRepository = kühlschrankRepository;
        this.itemFactory = itemFactory;
        this.lebensmittelVerwaltungsService = lebensmittelVerwaltungsService;

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
    public Kühlschrank kühlschrankSpeichern(Kühlschrank kühlschrank) {
        return kühlschrankRepository.save(kühlschrank);
    }

    public Kühlschrank itemHinzufügen(String lebensmittelName, LocalDate haltbarBis, int anzahl, Integer kühlschrankId) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        Lebensmittel lebensmittel = lebensmittelVerwaltungsService.lebensmittelAbfrage(lebensmittelName);
        Item item = itemFactory.erstelleItem(
                    lebensmittel,
                    haltbarBis,
                    anzahl
            );

        kühlschrank.itemHinzufuegen(item);
        return kühlschrankRepository.save(kühlschrank);
    }
    public Kühlschrank itemEntfernen(Integer kühlschrankId, Integer itemId) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        Item item = kühlschrank.findeItemNachId(itemId);
        kühlschrank.itemEntfernen(item);
        return kühlschrankRepository.save(kühlschrank);
    }
    public Kühlschrank itemTeilweiseVerbraucht(Integer kühlschrankId, Integer itemId, int neueAnzahl) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        Item item = kühlschrank.findeItemNachId(itemId);
        kühlschrank.itemVerbrauchen(item, neueAnzahl);
        return kühlschrankRepository.save(kühlschrank);
    }
    public Kühlschrank itemKorrigieren( Integer kühlschrankId, Integer itemId, String lebensmittelName, LocalDate haltbarkeit, int anzahl) {
        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        Item item = kühlschrank.findeItemNachId(itemId);
        Lebensmittel lebensmittel = lebensmittelVerwaltungsService.lebensmittelAbfrage(lebensmittelName);
        Haltbarkeitsdatum haltbarkeitsdatum = new Haltbarkeitsdatum(haltbarkeit);
        kühlschrank.itemKorrigieren(item, lebensmittel, haltbarkeitsdatum, anzahl);
        return kühlschrankRepository.save(kühlschrank);
    }


}
