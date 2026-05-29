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

    public List<Item> findeAbgelaufeneItems(Integer kuehlschrankId, int tageBisAblauf) {
        LocalDate grenzDatum = LocalDate.now().plusDays(tageBisAblauf);

        return ladeZielKuehlschraenke(kuehlschrankId).stream()
                .flatMap(kuehlschrank -> kuehlschrank.getItems().stream())
                .filter(item -> !item.getHaltbarkeit().getDatum().isAfter(grenzDatum))
                .filter(item -> !istAbgelaufen(item))
                .sorted(Comparator.comparing(item -> item.getHaltbarkeit().getDatum()))
                .collect(Collectors.toList());
    }

    
    public List<Item> filterUndSortiereBestand(
            Integer kuehlschrankId,
            String kategorieStr,
            String status,
            String sortBy) {

        Stream<Item> itemStream = ladeItemsAusZielKuehlschraenken(kuehlschrankId);

        itemStream = filterNachKategorie(itemStream, kategorieStr);
        itemStream = filterNachStatus(itemStream, status);
        itemStream = sortiere(itemStream, sortBy);

        return itemStream.collect(Collectors.toList());
    }

    private List<Kühlschrank> ladeZielKuehlschraenke(Integer kuehlschrankId) {
        if (kuehlschrankId != null) {
            Kühlschrank kuehlschrank = repository.findById(kuehlschrankId)
                    .orElseThrow(() -> new IllegalArgumentException("Kühlschrank nicht gefunden."));

            return List.of(kuehlschrank);
        }

        return repository.findAll();
    }

    private Stream<Item> ladeItemsAusZielKuehlschraenken(Integer kuehlschrankId) {
        return ladeZielKuehlschraenke(kuehlschrankId).stream()
                .flatMap(kuehlschrank -> kuehlschrank.getItems().stream());
    }

    private Stream<Item> filterNachKategorie(Stream<Item> itemStream, String kategorieStr) {
        if (kategorieStr == null || kategorieStr.isBlank()) {
            return itemStream;
        }

        Kategorie suchKategorie = Kategorie.valueOf(kategorieStr.toUpperCase());

        return itemStream.filter(item ->
                item.getLebensmittel().getKategorie().equals(suchKategorie)
        );
    }

    private Stream<Item> filterNachStatus(Stream<Item> itemStream, String status) {
        if (status == null || status.isBlank()) {
            return itemStream;
        }
        if ("ABGELAUFEN".equalsIgnoreCase(status)) {
            return itemStream.filter(this::istAbgelaufen);
        } else if ("OK".equalsIgnoreCase(status)) {
            return itemStream.filter(item -> !istAbgelaufen(item));
        } else {
            throw new IllegalArgumentException("Unbekannter Status: " + status + ". Erlaubte Werte: OK, ABGELAUFEN");
        }
    }

    private boolean istAbgelaufen(Item item) {
        return item.getHaltbarkeit().getDatum().isBefore(LocalDate.now());
    }

    private Stream<Item> sortiere(Stream<Item> itemStream, String sortBy) {
        if ("haltbarkeit".equalsIgnoreCase(sortBy)) {
            return itemStream.sorted(
                    Comparator.comparing(item -> item.getHaltbarkeit().getDatum())
            );
        }

        return itemStream.sorted(
                Comparator.comparing(item -> item.getLebensmittel().getName().toLowerCase())
        );
    }
}