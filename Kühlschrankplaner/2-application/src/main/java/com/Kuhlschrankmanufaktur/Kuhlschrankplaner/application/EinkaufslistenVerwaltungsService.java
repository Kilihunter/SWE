package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EinkaufslistenVerwaltungsService {

    private final EinkaufslisteRepository einkaufslisteRepository;
    private final KühlschrankRepository kühlschrankRepository;

    public EinkaufslistenVerwaltungsService(EinkaufslisteRepository einkaufslisteRepository,
                                             KühlschrankRepository kühlschrankRepository) {
        this.einkaufslisteRepository = einkaufslisteRepository;
        this.kühlschrankRepository = kühlschrankRepository;
    }

    public Einkaufsliste einkaufslisteAnlegen(String name) {
        Einkaufsliste liste = new Einkaufsliste(name);
        return einkaufslisteRepository.save(liste);
    }

    public Einkaufsliste getEinkaufsliste(Integer id) {
        return einkaufslisteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Einkaufsliste mit ID " + id + " nicht gefunden."));
    }

    public List<Einkaufsliste> getAlleEinkaufslisten() {
        return einkaufslisteRepository.findAll();
    }

    public Einkaufsliste schreibeAuf(Integer einkaufslisteId, int anzahl, String lebensmittelName) {
        Einkaufsliste liste = getEinkaufsliste(einkaufslisteId);

        liste.schreibeAuf(anzahl, lebensmittelName);

    return einkaufslisteRepository.save(liste);
    }
    public Einkaufsliste einkaufVerarbeiten(Integer einkaufslisteId, Integer kühlschrankId, int anzahl, String lebensmittelName, LocalDate haltbarkeit, String kategorie, String einheit) {

        Kühlschrank kühlschrank = kühlschrankRepository.findById(kühlschrankId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Kühlschrank mit ID " + kühlschrankId + " nicht gefunden."
                ));

        Einkaufsliste liste = getEinkaufsliste(einkaufslisteId);

        KühlschrankDomainService kühlschrankDomainService = new KühlschrankDomainService();

        Kategorie kat = Kategorie.valueOf(kategorie.toUpperCase());
        Einheit ein = Einheit.valueOf(einheit.toUpperCase());
        Haltbarkeitsdatum haltbarkeitsdatum = new Haltbarkeitsdatum(haltbarkeit);
        Lebensmittel lebensmittel = new Lebensmittel(lebensmittelName, kat, ein);

        kühlschrank = kühlschrankDomainService.einkaufVerarbeiten(
                liste,
                kühlschrank,
                lebensmittel,
                haltbarkeitsdatum,
                anzahl,
                ein
        );

        kühlschrankRepository.save(kühlschrank);
        return einkaufslisteRepository.save(liste);
    }
    public void sachenDieNachgekauftWerdenMüssen(Integer kühlschrankId, Integer einkaufslisteId) {
        Kühlschrank kühlschrank = kühlschrankRepository.findById(kühlschrankId)
                .orElseThrow(() -> new IllegalArgumentException("Kühlschrank mit ID " + kühlschrankId + " nicht gefunden."));
        Einkaufsliste einkaufsliste = einkaufslisteRepository.findById(einkaufslisteId)
                .orElseThrow(() -> new IllegalArgumentException("Einkaufsliste mit ID " + einkaufslisteId + " nicht gefunden."));
        KühlschrankDomainService kühlschrankDomainService = new KühlschrankDomainService();
        kühlschrankDomainService.abgelaufeneEntsorgenUndNachkaufen(kühlschrank, einkaufsliste);
        kühlschrankRepository.save(kühlschrank);
        einkaufslisteRepository.save(einkaufsliste);
    }
}
