package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.EinkaufslistenVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einkaufsliste;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.EinkaufslisteResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslisteResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslistenEintragErstellenResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemErstellenResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/einkaufsliste")
public class EinkaufslistenController {

    private final EinkaufslistenVerwaltungsService service;
    private final EinkaufslisteResourceMapper mapper;

    public EinkaufslistenController(EinkaufslistenVerwaltungsService service,
                                     EinkaufslisteResourceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity<EinkaufslisteResource> erstelleEinkaufsliste(
            @RequestBody String name) {
        var Einkaufsliste = service.einkaufslisteAnlegen(name);
        return ResponseEntity.ok(mapper.map(Einkaufsliste));
    }
    @PostMapping("/{einkaufslisteId}/eintraege")
    public ResponseEntity<EinkaufslisteResource> einträgeErstellen(
            @PathVariable Integer einkaufslisteId,
            @RequestBody List<EinkaufslistenEintragErstellenResource> Items) {

        for (EinkaufslistenEintragErstellenResource item : Items) {
        service.schreibeAuf(
                einkaufslisteId,
                item.getMenge(),
                item.getName()
        );
    }

    return ResponseEntity.ok(
            mapper.map(service.getEinkaufsliste(einkaufslisteId)) // könnte ein smell sein aber bin mir nich ganz sicher 
    );
    }
     @PostMapping("/{einkaufslisteId}/nachkaufen-aus-kuehlschrank/{kuehlschrankId}")
    public ResponseEntity<EinkaufslisteResource> sachenDieNachgekauftWerdenMuessen(
            @PathVariable Integer einkaufslisteId,
            @PathVariable Integer kühlschrankId) {

        service.sachenDieNachgekauftWerdenMüssen(
                kühlschrankId,
                einkaufslisteId
        );

        return ResponseEntity.ok(mapper.map(service.getEinkaufsliste(einkaufslisteId)));
    }

    @PostMapping("/{einkaufslisteId}/verarbeiten/kühlschrank/{kühlschrankId}")
    public ResponseEntity<EinkaufslisteResource> einkaufVerarbeiten(
            @PathVariable Integer einkaufslisteId,
            @PathVariable Integer kühlschrankId,
            @RequestBody List<ItemErstellenResource> items) {
        Einkaufsliste einkaufsliste = service.getEinkaufsliste(einkaufslisteId);
        for (ItemErstellenResource item : items) {
            einkaufsliste = service.einkaufVerarbeiten(
                    einkaufslisteId,
                    kühlschrankId,
                    item.getMenge(),
                    item.getName(),
                    item.getHaltbarkeit(),
                    item.getKategorie(),
                    item.getEinheit()
            );
        }
        return ResponseEntity.ok(
                mapper.map(einkaufsliste)
        );
    }

    @GetMapping
    public ResponseEntity<List<EinkaufslisteResource>> getAlleEinkaufslisten() {
        var Einkaufslisten = service.getAlleEinkaufslisten().stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(Einkaufslisten);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EinkaufslisteResource> getEinkaufsliste(@PathVariable Integer id) {
        var Einkaufsliste = service.getEinkaufsliste(id);
        return ResponseEntity.ok(mapper.map(Einkaufsliste));
    }


   
}
