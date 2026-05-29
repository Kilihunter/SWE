package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class EinkaufslistenVerwaltungsService {

    private final EinkaufslisteRepository einkaufslisteRepository;
    private final KühlschrankVerwaltungsService kühlschrankVerwaltungsService;
    private final ItemFactory itemFactory;
    private final LebensmittelVerwaltungsService lebensmittelVerwaltungsService;
    private final KühlschrankDomainService kühlschrankDomainService;

    public EinkaufslistenVerwaltungsService(EinkaufslisteRepository einkaufslisteRepository,
                                             KühlschrankVerwaltungsService kühlschrankVerwaltungsService,
                                             ItemFactory itemFactory,
                                             LebensmittelVerwaltungsService lebensmittelVerwaltungsService, KühlschrankDomainService kühlschrankDomainService) {
        this.einkaufslisteRepository = einkaufslisteRepository;
        this.kühlschrankVerwaltungsService = kühlschrankVerwaltungsService;
        this.itemFactory = itemFactory;
        this.lebensmittelVerwaltungsService = lebensmittelVerwaltungsService;
        this.kühlschrankDomainService = kühlschrankDomainService;

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
        Lebensmittel lebensmittel = lebensmittelVerwaltungsService.lebensmittelAbfrage(lebensmittelName);
        liste.schreibeAuf(anzahl, lebensmittel);

    return einkaufslisteRepository.save(liste);
    }
    public Einkaufsliste einkaufVerarbeiten(
            Integer einkaufslisteId,
            Integer kühlschrankId,
            int anzahl,
            String lebensmittelName,
            LocalDate haltbarkeit
        ) {

        Kühlschrank kühlschrank = kühlschrankVerwaltungsService.getKühlschrank(kühlschrankId);
        Einkaufsliste einkaufsliste = getEinkaufsliste(einkaufslisteId);
        Lebensmittel lebensmittel = lebensmittelVerwaltungsService.lebensmittelAbfrage(lebensmittelName);
        Item item = itemFactory.erstelleItem(
                lebensmittel,
                 haltbarkeit,
                 anzahl
        );


        kühlschrankDomainService.einkaufVerarbeiten(
                einkaufsliste,
                kühlschrank,
                item
        );

        kühlschrankVerwaltungsService.kühlschrankSpeichern(kühlschrank);

        return einkaufslisteRepository.save(einkaufsliste);
    }

    public Einkaufsliste sachenDieNachgekauftWerdenMüssen( Integer einkaufslisteId) {
        List<Kühlschrank> kühlschränke = kühlschrankVerwaltungsService.getAlleKühlschränke();
        if (kühlschränke.isEmpty()) {
            throw new IllegalArgumentException("Es muss mindestens einen Kühlschrank geben, um die Einkaufsliste zu aktualisieren.");
        }
        Einkaufsliste einkaufsliste = einkaufslisteRepository.findById(einkaufslisteId)
                .orElseThrow(() -> new IllegalArgumentException("Einkaufsliste mit ID " + einkaufslisteId + " nicht gefunden."));
        kühlschrankDomainService.abgelaufeneEntsorgenUndNachkaufen(kühlschränke, einkaufsliste);
        
        for (Kühlschrank kühlschrank : kühlschränke) {
            kühlschrankVerwaltungsService.kühlschrankSpeichern(kühlschrank);
        }
        return einkaufslisteRepository.save(einkaufsliste);
    }

    public Einkaufsliste LebensmittelNachkaufenUmMindestBestandZuErreichen(Integer einkaufslisteId) {
        List<Kühlschrank> kühlschränke = kühlschrankVerwaltungsService.getAlleKühlschränke();
        if (kühlschränke.isEmpty()) {
            throw new IllegalArgumentException("Es muss mindestens einen Kühlschrank geben, wo sollen sonst die lebensmittel gelagert werden?");
        }
        Einkaufsliste einkaufsliste = einkaufslisteRepository.findById(einkaufslisteId)
                .orElseThrow(() -> new IllegalArgumentException("Einkaufsliste mit ID " + einkaufslisteId + " nicht gefunden."));
        Map<Lebensmittel, Integer> lebensmittelMitMindestbestand = lebensmittelVerwaltungsService.getAlleLebensmittelMitMindestbestand();
        for (Map.Entry<Lebensmittel, Integer> eintrag : lebensmittelMitMindestbestand.entrySet()) {
            Lebensmittel lebensmittel = eintrag.getKey();
            Integer minMenge = eintrag.getValue();

            int gesamtBestand = kühlschränke.stream()
                    .mapToInt(kühlschrank -> kühlschrank.bestandVon(lebensmittel))
                    .sum();

            if (gesamtBestand < minMenge) {
                int fehlendeMenge = minMenge - gesamtBestand;
                if (fehlendeMenge > 0) {
                    if (!einkaufsliste.getEintraege().containsKey(lebensmittel)) {
                        einkaufsliste.schreibeAuf(fehlendeMenge, lebensmittel);
                    }
                }
            }
        }
        return einkaufslisteRepository.save(einkaufsliste);
}
}
