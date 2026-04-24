package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.util.List;
import java.util.Optional;

public interface KühlschrankRepository {

    Optional<Kühlschrank> findById(Long id);
    
    Optional<Kühlschrank> findByName(String name); 
    
    List<Kühlschrank> findAll();

    Kühlschrank save(Kühlschrank kühlschrank);
    
    void deleteById(Long id); 
}