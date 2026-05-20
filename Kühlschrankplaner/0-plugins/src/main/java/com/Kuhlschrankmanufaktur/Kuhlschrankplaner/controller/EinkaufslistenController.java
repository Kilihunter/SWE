package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.EinkaufslistenVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.EinkaufslisteResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslisteResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemResource;

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
    /*@PostMapping
    public ResponseEntity<EinkaufslisteResource> einträgeErstellen(
            @RequestBody List<ItemResource> Items) {
        //einträge erstellen
    }
    @PostMapping
    public ResponseEntity<EinkaufslisteResource> sachenDieNachgekauftWerdenMüssen() { 
          //automatische einträge anhand von kühlschrankinhalterstellen
    }

*/
   
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
