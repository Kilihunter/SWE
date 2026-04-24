/*package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;


import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Haltbarkeitsdatum;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Kategorie;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Lebensmittel;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.service.ItemService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.service.LebensmittelService;


@RestController
public class LebensmittelController {
    private final LebensmittelService lebensmittelService;
    private final ItemService itemService;

    public LebensmittelController(LebensmittelService lebensmittelService, ItemService itemService) {
        this.lebensmittelService = lebensmittelService;
        this.itemService = itemService;
    }
    @PostMapping("/lebensmittel")
    public String lebensmittelAnlegen(@RequestBody HinzufügenRequest request) {
        if (request == null || request.name() == null || request.name().trim().isEmpty()) {
            throw new IllegalArgumentException("name must not be empty");
        }
         if (request.haltbarkeitsdatum() == null) {
            throw new IllegalArgumentException("haltbarkeitsdatum must not be null");
        }
         if (request.menge() <= 0) {
            throw new IllegalArgumentException("menge must be > 0");
        }

        Kategorie kategorie = new Kategorie(request.kategoriename().trim());
        Lebensmittel lebensmittel = lebensmittelService.lebensmittelHinzufügen(kategorie, request.name().trim(), 0);
        Haltbarkeitsdatum haltbarkeitsdatum = new Haltbarkeitsdatum(request.haltbarkeitsdatum());

        for (int i = 0; i < request.menge(); i++) {
              itemService.create(haltbarkeitsdatum,lebensmittel);

        }
        return "Lebensmittel angelegt: " + request.name() + ", Haltbarkeitsdatum: " + request.haltbarkeitsdatum() + ", Menge: " + request.menge();
    }
    
    public record HinzufügenRequest(String name, Date haltbarkeitsdatum, int menge, String kategoriename) {
    }

    @GetMapping("/lebensmittel")
    public List<Lebensmittel> getLebensmittel() {
       return lebensmittelService.findAll();
    }
}
    */
   