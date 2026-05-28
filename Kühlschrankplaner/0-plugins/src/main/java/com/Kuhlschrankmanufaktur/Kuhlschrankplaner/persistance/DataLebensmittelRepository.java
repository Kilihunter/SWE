package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.persistance;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Lebensmittel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataLebensmittelRepository extends JpaRepository<Lebensmittel, String> {
    Optional<Lebensmittel> findByNameIgnoreCase(String name);
}
