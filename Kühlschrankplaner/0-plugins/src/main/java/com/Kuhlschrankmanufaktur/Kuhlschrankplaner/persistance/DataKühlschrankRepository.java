package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.persistance;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataKühlschrankRepository extends JpaRepository<Kühlschrank, Long> {
}