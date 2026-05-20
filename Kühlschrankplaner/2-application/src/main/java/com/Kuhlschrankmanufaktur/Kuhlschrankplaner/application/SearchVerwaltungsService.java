package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SearchVerwaltungsService {
    
    private final KühlschrankRepository repository;

    public SearchVerwaltungsService(KühlschrankRepository repository) {
        this.repository = repository;
    }

    private List<Kühlschrank> getZielKuehlschraenke(Integer kuehlschrankId) {
        if (kuehlschrankId != null) {
            Kühlschrank k = repository.findById(kuehlschrankId)
                    .orElseThrow(() -> new IllegalArgumentException("Kühlschrank nicht gefunden."));
            return List.of(k); 
        } else {
            return repository.findAll();
        }
    }

    public List<Item> findeAblaufendeItems(Integer kuehlschrankId, int tageBisAblauf) {
        LocalDate grenzDatum = LocalDate.now().plusDays(tageBisAblauf);

        return getZielKuehlschraenke(kuehlschrankId).stream()
                .flatMap(kuehlschrank -> kuehlschrank.getItems().stream())
                .filter(item -> !item.getHaltbarkeit().getDatum().isAfter(grenzDatum))
                .sorted(Comparator.comparing(item -> item.getHaltbarkeit().getDatum()))
                .collect(Collectors.toList());
    }

    
    public List<Item> filterUndSortiereBestand(Integer kuehlschrankId, String kategorieStr, String status, String sortBy) {
        Stream<Item> itemStream = getZielKuehlschraenke(kuehlschrankId).stream()
                .flatMap(kuehlschrank -> kuehlschrank.getItems().stream());

        if (kategorieStr != null && !kategorieStr.isBlank()) {
            Kategorie suchKategorie = Kategorie.valueOf(kategorieStr.toUpperCase());
            itemStream = itemStream.filter(item -> item.getLebensmittel().getKategorie() == suchKategorie);
        }

        if ("ABGELAUFEN".equalsIgnoreCase(status)) {
            LocalDate heute = LocalDate.now();
            itemStream = itemStream.filter(item -> item.getHaltbarkeit().getDatum().isBefore(heute));
        } else if ("OK".equalsIgnoreCase(status)) {
            LocalDate heute = LocalDate.now();
            itemStream = itemStream.filter(item -> !item.getHaltbarkeit().getDatum().isBefore(heute));
        }

        if ("haltbarkeit".equalsIgnoreCase(sortBy)) {
            itemStream = itemStream.sorted(Comparator.comparing(item -> item.getHaltbarkeit().getDatum()));
        } else {
            itemStream = itemStream.sorted(Comparator.comparing(item -> item.getLebensmittel().getName().toLowerCase()));
        }

        return itemStream.collect(Collectors.toList());
    }
}
