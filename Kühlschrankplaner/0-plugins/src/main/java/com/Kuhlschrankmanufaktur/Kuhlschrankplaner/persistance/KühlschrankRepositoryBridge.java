package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.persistance;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.KühlschrankRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KühlschrankRepositoryBridge implements KühlschrankRepository {

    private final DataKühlschrankRepository Repo;

    public KühlschrankRepositoryBridge(DataKühlschrankRepository springDataRepo) {
        this.Repo = springDataRepo;
    }

    @Override
    public Optional<Kühlschrank> findById(Long id) {
        return Repo.findById(id);
    }

    @Override
    public List<Kühlschrank> findAll() {
        return Repo.findAll();
    }

    @Override
    public Kühlschrank save(Kühlschrank kühlschrank) {
        return Repo.save(kühlschrank);
    }
}