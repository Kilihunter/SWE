package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Lebensmittel;


@Repository
public interface LebensmittelRepository extends JpaRepository<Lebensmittel, Integer> {
    Optional<Lebensmittel> findByName(String name);
}
