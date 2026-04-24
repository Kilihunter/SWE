package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.KühlschrankVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.KühlschrankResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.KühlschrankResourceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kuehlschrank")
public class KühlschrankController {

    private final KühlschrankVerwaltungsService appService;
    private final KühlschrankResourceMapper mapper;

    public KühlschrankController(KühlschrankVerwaltungsService appService,
                                  KühlschrankResourceMapper mapper) {
        this.appService = appService;
        this.mapper = mapper;
    }

    public record CreateKühlschrankRequest(String name) {}

    @PostMapping
    public ResponseEntity<KühlschrankResource> erstelleKühlschrank(@RequestBody CreateKühlschrankRequest request) {
        var neuerKühlschrank = appService.kühlschrankAnlegen(request.name());
        var responseResource = mapper.map(neuerKühlschrank);
        return ResponseEntity.ok(responseResource);
    }
    @GetMapping("/{id}")
    public ResponseEntity<KühlschrankResource> getKuehlschrank(@PathVariable Long id) {
        var kuehlschrank = appService.getKuehlschrank(id);
        var responseResource = mapper.map(kuehlschrank);
        return ResponseEntity.ok(responseResource); 
    }
}