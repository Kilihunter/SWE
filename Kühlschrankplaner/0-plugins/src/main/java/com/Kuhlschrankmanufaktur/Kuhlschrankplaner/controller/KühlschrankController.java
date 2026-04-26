package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.KühlschrankVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.KühlschrankResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.KühlschrankResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.ItemResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.ItemResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/kuehlschrank")
public class KühlschrankController {

    private final KühlschrankVerwaltungsService appService;
    private final KühlschrankResourceMapper kühlschrankMapper;
    private final ItemResourceMapper itemMapper;

    public KühlschrankController(KühlschrankVerwaltungsService appService,
                                  KühlschrankResourceMapper kühlschrankMapper,
                                  ItemResourceMapper itemMapper) {
        this.appService = appService;
        this.kühlschrankMapper = kühlschrankMapper;
        this.itemMapper = itemMapper;
    }

    public record CreateKühlschrankRequest(String name) {}
    public record CreateItemRequest(String lebensmittelName, String kategorie, String einheit, LocalDate haltbarBis, int anzahl) {}
    public record UpdateItemRequest(int neueAnzahl) {}
    @PostMapping
    public ResponseEntity<KühlschrankResource> erstelleKühlschrank(@RequestBody CreateKühlschrankRequest request) {
        var neuerKühlschrank = appService.kühlschrankAnlegen(request.name());
        var responseResource = kühlschrankMapper.map(neuerKühlschrank);
        return ResponseEntity.ok(responseResource);
    }
    @GetMapping("/{id}")
    public ResponseEntity<KühlschrankResource> getKühlschrank(@PathVariable Integer id) {
        var kühlschrank = appService.getKühlschrank(id);
        var responseResource = kühlschrankMapper.map(kühlschrank);
        return ResponseEntity.ok(responseResource); 
    }
    @PostMapping("/{id}/item")
    public ResponseEntity<KühlschrankResource> eingekauft(@PathVariable Integer id, @RequestBody CreateItemRequest request) {
        var kühlschrank = appService.itemHinzufügen(
                request.lebensmittelName(),
                request.einheit(),
                request.kategorie(),
                request.haltbarBis(),
                request.anzahl(),
                id
        );
        var responseResource = kühlschrankMapper.map(kühlschrank);
        return ResponseEntity.ok(responseResource);
    }
    @DeleteMapping("/{idk}/item/{id}")
    public ResponseEntity<?> komplettVerbraucht(@PathVariable Integer idk, @PathVariable Integer id) {
        var kühlschrank = appService.itemEntfernen(idk, id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{idk}/item/{id}")
    public ResponseEntity<KühlschrankResource> teilweiseVerbraucht(@PathVariable Integer idk, @PathVariable Integer id, @RequestBody UpdateItemRequest request) {
        var kühlschrank = appService.itemTeilweiseVerbraucht(idk, id, request.neueAnzahl());
        var responseResource = kühlschrankMapper.map(kühlschrank);
        return ResponseEntity.ok(responseResource);
    }
}