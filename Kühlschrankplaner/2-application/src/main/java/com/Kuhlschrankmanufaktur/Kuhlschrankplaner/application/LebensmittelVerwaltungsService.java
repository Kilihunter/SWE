package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class LebensmittelVerwaltungsService {


    private final LebensmittelRepository lebensmittelRepository;

    public LebensmittelVerwaltungsService(LebensmittelRepository lebensmittelRepository) {
        this.lebensmittelRepository = lebensmittelRepository;
    }

    public Lebensmittel lebensmittelAnlegen(String name, String kategorie, String einheit, int minMenge) {
        if (lebensmittelRepository.findByLebensmittelName(name).isPresent()) {
            throw new IllegalArgumentException("Lebensmittel '" + name + "' existiert bereits.");
        }
        Kategorie kategorieDomain = Kategorie.valueOf(kategorie.toUpperCase());
        Einheit einheitDomain = Einheit.valueOf(einheit.toUpperCase());

        Lebensmittel neuesLebensmittel = new Lebensmittel(name, kategorieDomain, einheitDomain, minMenge);
        return lebensmittelRepository.save(neuesLebensmittel);
    }   

    public Lebensmittel anpassen(String name, String kategorie, String einheit, int minMenge) {
        Kategorie neuKategorie = Kategorie.valueOf(kategorie.toUpperCase());
        Einheit neuEinheit = Einheit.valueOf(einheit.toUpperCase());
        Optional<Lebensmittel> optionalLebensmittel = lebensmittelRepository.findByLebensmittelName(name);

        if (optionalLebensmittel.isPresent()) {
            Lebensmittel bestehendesLebensmittel = optionalLebensmittel.get();
            bestehendesLebensmittel.lebensmittelAnpassen(neuKategorie, neuEinheit, minMenge);
            lebensmittelRepository.save(bestehendesLebensmittel);
            return bestehendesLebensmittel;
        }else {
            throw new IllegalArgumentException("Lebensmittel mit Name " + name + " nicht gefunden.");
        }
    }
    public Lebensmittel lebensmittelAbfrage(String name) {
        return lebensmittelRepository.findByLebensmittelName(name)
                .orElseThrow(() -> new IllegalArgumentException("Lebensmittel mit Name " + name + " nicht gefunden."));
    }
    public Map<Lebensmittel, Integer> getAlleLebensmittelMitMindestbestand() {
        List<Lebensmittel> lebensmittelListe = lebensmittelRepository.findAll();
        Map<Lebensmittel, Integer> lebensmittelMitMindestbestand = new HashMap<>();

        for (Lebensmittel lebensmittel : lebensmittelListe) {
            if (lebensmittel.getMinMenge() != 0) {
                lebensmittelMitMindestbestand.put(lebensmittel, lebensmittel.getMinMenge());
            }
        }

        return lebensmittelMitMindestbestand;
    }
}
