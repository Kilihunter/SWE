package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.persistance;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einkaufsliste;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.EinkaufslisteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EinkaufslisteRepositoryBridge implements EinkaufslisteRepository {

    private final DataEinkaufslisteRepository repo;

    public EinkaufslisteRepositoryBridge(DataEinkaufslisteRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Einkaufsliste> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public List<Einkaufsliste> findAll() {
        return repo.findAll();
    }

    @Override
    public Einkaufsliste save(Einkaufsliste einkaufsliste) {
        return repo.save(einkaufsliste);
    }
}
