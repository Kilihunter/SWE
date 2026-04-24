package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.List;
import java.util.Optional;


public interface EinkaufslisteRepository {

    Optional<Einkaufsliste> findById(Integer id);
    List<Einkaufsliste> findAll();

    Einkaufsliste save(Einkaufsliste einkaufsliste);
}
