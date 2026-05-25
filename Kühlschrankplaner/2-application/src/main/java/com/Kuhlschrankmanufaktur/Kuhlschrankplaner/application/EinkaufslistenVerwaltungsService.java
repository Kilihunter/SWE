package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EinkaufslistenVerwaltungsService {

    private final EinkaufslisteRepository einkaufslisteRepository;
    private final KühlschrankRepository kühlschrankRepository;
    private final ItemFactory itemFactory;

    public EinkaufslistenVerwaltungsService(EinkaufslisteRepository einkaufslisteRepository,
                                             KühlschrankRepository kühlschrankRepository,
                                             ItemFactory itemFactory) {
        this.einkaufslisteRepository = einkaufslisteRepository;
        this.kühlschrankRepository = kühlschrankRepository;
        this.itemFactory = itemFactory;
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
    public Einkaufsliste einkaufVerarbeiten(
            Integer einkaufslisteId,
            Integer kühlschrankId,
            int anzahl,
            String lebensmittelName,
            LocalDate haltbarkeit,
            String kategorie,
            String einheit) {

        Kühlschrank kühlschrank = ladeKühlschrank(kühlschrankId);
        Einkaufsliste einkaufsliste = getEinkaufsliste(einkaufslisteId);

        Item item = itemFactory.erstelleItem(
                lebensmittelName,
                einheit,
                kategorie,
                haltbarkeit,
                anzahl
        );

        KühlschrankDomainService kühlschrankDomainService = new KühlschrankDomainService();

        kühlschrankDomainService.einkaufVerarbeiten(
                einkaufsliste,
                kühlschrank,
                item
        );

        kühlschrankRepository.save(kühlschrank);

        return einkaufslisteRepository.save(einkaufsliste);
    }

    public Einkaufsliste sachenDieNachgekauftWerdenMüssen(Integer kühlschrankId, Integer einkaufslisteId) {
        Kühlschrank kühlschrank = kühlschrankRepository.findById(kühlschrankId)
                .orElseThrow(() -> new IllegalArgumentException("Kühlschrank mit ID " + kühlschrankId + " nicht gefunden."));
        Einkaufsliste einkaufsliste = einkaufslisteRepository.findById(einkaufslisteId)
                .orElseThrow(() -> new IllegalArgumentException("Einkaufsliste mit ID " + einkaufslisteId + " nicht gefunden."));
        KühlschrankDomainService kühlschrankDomainService = new KühlschrankDomainService();
        kühlschrankDomainService.abgelaufeneEntsorgenUndNachkaufen(kühlschrank, einkaufsliste);
        kühlschrankRepository.save(kühlschrank);
        return einkaufslisteRepository.save(einkaufsliste);
    }
    private Kühlschrank ladeKühlschrank(Integer kühlschrankId) {
        return kühlschrankRepository.findById(kühlschrankId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Kühlschrank mit ID " + kühlschrankId + " nicht gefunden."
                ));
    }
}
