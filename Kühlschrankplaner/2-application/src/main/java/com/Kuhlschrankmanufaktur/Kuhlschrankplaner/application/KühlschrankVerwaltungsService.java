package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
public class KühlschrankVerwaltungsService {

    private final KühlschrankRepository kühlschrankRepository;

    public KühlschrankVerwaltungsService(KühlschrankRepository kühlschrankRepository) {
        this.kühlschrankRepository = kühlschrankRepository;
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
        Kategorie kat = Kategorie.valueOf(kategorie.toUpperCase());
        Einheit ein = Einheit.valueOf(einheit.toUpperCase());
        Lebensmittel lebensmittel = new Lebensmittel(lebensmittelName, kat, ein);
        Haltbarkeitsdatum haltbarkeit = new Haltbarkeitsdatum(haltbarBis);

        Kühlschrank kühlschrank = getKühlschrank(kühlschrankId);
        Item item = new Item(lebensmittel, haltbarkeit, anzahl, ein);
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

}
