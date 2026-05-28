package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.LebensmittelVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.LebensmittelResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Lebensmittel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lebensmittel")
public class LebensmittelController {

    private final LebensmittelVerwaltungsService service;

    public LebensmittelController(LebensmittelVerwaltungsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Lebensmittel> lebensmittelAnlegen(@RequestBody LebensmittelResource request) {
        Lebensmittel lebensmittel = service.lebensmittelAnlegen(
                request.getName(),
                request.getKategorie(),
                request.getEinheit(),
                request.getMinMenge()
        );
        return ResponseEntity.ok(lebensmittel);
    }

    @PutMapping
    public ResponseEntity<Lebensmittel> lebensmittelAnpassen(@RequestBody LebensmittelResource request) {
       Lebensmittel lebensmittel = service.anpassen(
                request.getName(),
                request.getKategorie(),
                request.getEinheit(),
                request.getMinMenge()
        );
        return ResponseEntity.ok(lebensmittel);
    }
}

