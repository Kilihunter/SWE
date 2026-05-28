package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.EinkaufslistenVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einkaufsliste;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.EinkaufslisteResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslisteResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslistenEintragErstellenResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemErstellenResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslistenErstellResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/einkaufsliste")
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
            @RequestBody EinkaufslistenErstellResource request) {
        var Einkaufsliste = service.einkaufslisteAnlegen(request.getName());
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
            mapper.map(service.getEinkaufsliste(einkaufslisteId)) 
    );
    }
     @PostMapping("/{einkaufslisteId}/nachkaufen")
    public ResponseEntity<EinkaufslisteResource> sachenDieNachgekauftWerdenMuessen(
            @PathVariable Integer einkaufslisteId
           ) {

       Einkaufsliste einkaufsliste =  service.sachenDieNachgekauftWerdenMüssen(
                einkaufslisteId
        );

        return ResponseEntity.ok(mapper.map(einkaufsliste));
    }

    @PostMapping("/{einkaufslisteId}/verarbeiten/kühlschrank/{kuehlschrankId}")
    public ResponseEntity<EinkaufslisteResource> einkaufVerarbeiten(
            @PathVariable Integer einkaufslisteId,
            @PathVariable Integer kuehlschrankId,
            @RequestBody List<ItemErstellenResource> items) {
        Einkaufsliste einkaufsliste = service.getEinkaufsliste(einkaufslisteId);
        for (ItemErstellenResource item : items) {
            einkaufsliste = service.einkaufVerarbeiten(
                    einkaufslisteId,
                    kuehlschrankId,
                    item.getAnzahl(),
                    item.getLebensmittelName(),
                    item.getHaltbarkeit()
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
