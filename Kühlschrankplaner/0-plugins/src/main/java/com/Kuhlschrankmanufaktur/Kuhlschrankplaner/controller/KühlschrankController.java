package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.KühlschrankVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.KühlschrankResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.KühlschrankResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.ItemResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemErstellenResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/kuehlschrank")
public class KühlschrankController {

    private final KühlschrankVerwaltungsService appService;
    private final KühlschrankResourceMapper kühlschrankMapper;

    public KühlschrankController(KühlschrankVerwaltungsService appService,
                                  KühlschrankResourceMapper kühlschrankMapper) {
        this.appService = appService;
        this.kühlschrankMapper = kühlschrankMapper;
    }

    @PostMapping
    public ResponseEntity<KühlschrankResource> erstelleKühlschrank(@RequestBody String name) {
        var neuerKühlschrank = appService.kühlschrankAnlegen(name);
        var responseResource = kühlschrankMapper.map(neuerKühlschrank);
        return ResponseEntity.ok(responseResource);
    }
    @GetMapping("/")
    public ResponseEntity<?> getKühlschränke(@RequestParam(required = false) Integer id) {
        if(id == null){
            var alleKühlschränke = appService.getAlleKühlschränke();
            var responseResources = alleKühlschränke.stream()
                    .map(kühlschrankMapper::map)
                    .toList();
            return ResponseEntity.ok(responseResources);
        }else{
        var kühlschrank = appService.getKühlschrank(id);
        var responseResource = kühlschrankMapper.map(kühlschrank);
        return ResponseEntity.ok(responseResource); 
        }
        
    }
    @PostMapping("/{id}/item")
    public ResponseEntity<KühlschrankResource> eingekauft(@PathVariable Integer id, @RequestBody ItemErstellenResource request) {
        var kühlschrank = appService.itemHinzufügen(
                request.getName(),
                request.getEinheit(),
                request.getKategorie(),
                request.getHaltbarkeit(),
                request.getMenge(),
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
    public ResponseEntity<KühlschrankResource> teilweiseVerbraucht(@PathVariable Integer idk, @PathVariable Integer id, @RequestBody int neueAnzahl) {
        var kühlschrank = appService.itemTeilweiseVerbraucht(idk, id, neueAnzahl);
        var responseResource = kühlschrankMapper.map(kühlschrank);
        return ResponseEntity.ok(responseResource);
    }
}