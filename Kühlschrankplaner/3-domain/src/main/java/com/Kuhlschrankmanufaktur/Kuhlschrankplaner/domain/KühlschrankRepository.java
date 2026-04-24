package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.List;
import java.util.Optional;


public interface KühlschrankRepository {

    Optional<Kühlschrank> findById(Integer id);
    List<Kühlschrank> findAll();

    Kühlschrank save(Kühlschrank kühlschrank);
}
