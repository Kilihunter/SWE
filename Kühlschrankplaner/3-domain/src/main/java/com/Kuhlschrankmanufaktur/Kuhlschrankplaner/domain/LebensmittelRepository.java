package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;
import java.util.List;
import java.util.Optional;



public interface LebensmittelRepository {

    Optional<Lebensmittel> findByLebensmittelName(String name);
    List<Lebensmittel> findAll();

    Lebensmittel save(Lebensmittel lebensmittel);
}
