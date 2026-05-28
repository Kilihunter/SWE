package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.persistance;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Lebensmittel;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.LebensmittelRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public class LebensmittelRepositoryBridge implements LebensmittelRepository {

    private final DataLebensmittelRepository repo;

    public LebensmittelRepositoryBridge(DataLebensmittelRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Lebensmittel> findByLebensmittelName(String name) {
        return repo.findByNameIgnoreCase(name);
    }

    @Override
    public List<Lebensmittel> findAll() {
        return repo.findAll();
    }

    @Override
    public Lebensmittel save(Lebensmittel lebensmittel) {
        return repo.save(lebensmittel);
    }
}
