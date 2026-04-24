package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kühlschrank;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.KühlschrankRepository;
import org.springframework.stereotype.Service;

@Service
public class KühlschrankVerwaltungsService {

    private final KühlschrankRepository kuehlschrankRepository;

    public KühlschrankVerwaltungsService(KühlschrankRepository kuehlschrankRepository) {
        this.kuehlschrankRepository = kuehlschrankRepository;
    }

    public Kühlschrank kühlschrankAnlegen(String name) {
        Kühlschrank neuerKuehlschrank = new Kühlschrank(name);
        return kuehlschrankRepository.save(neuerKuehlschrank);
    }

    public Kühlschrank getKuehlschrank(Long id) {
        return kuehlschrankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kühlschrank mit ID " + id + " nicht gefunden."));
    }
}
