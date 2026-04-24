package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.util.List;
import java.util.Optional;

/**
 * Repository-Interface für die Einkaufsliste (Aggregate Root).
 * Gehört in die Domain-Schicht – definiert den Vertrag für Datenzugriff.
 * Die Implementierung erfolgt in der Plugin-Schicht.
 */
public interface EinkaufslisteRepository {

    Optional<Einkaufsliste> findById(Long id);
    List<Einkaufsliste> findAll();


    Einkaufsliste save(Einkaufsliste einkaufsliste);
}