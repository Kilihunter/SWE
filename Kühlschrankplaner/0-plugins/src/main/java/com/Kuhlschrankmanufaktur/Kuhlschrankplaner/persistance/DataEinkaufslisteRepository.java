package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.persistance;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einkaufsliste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataEinkaufslisteRepository extends JpaRepository<Einkaufsliste, Integer> {
}
