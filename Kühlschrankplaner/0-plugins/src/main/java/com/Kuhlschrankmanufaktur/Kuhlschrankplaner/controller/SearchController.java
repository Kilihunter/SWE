package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application.SearchVerwaltungsService;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.ItemResourceMapper;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {
    
    private final SearchVerwaltungsService queryService;
    private final ItemResourceMapper itemMapper;

    public SearchController(SearchVerwaltungsService queryService, ItemResourceMapper itemMapper) {
        this.queryService = queryService;
        this.itemMapper = itemMapper;
    }

    
    @GetMapping("/ablaufende")
    public ResponseEntity<List<ItemResource>> getAblaufendeItems(
            @RequestParam(required = false) Integer kuehlschrankId, 
            @RequestParam(defaultValue = "3") int tageBisAblauf    
    ) {
        var items = queryService.findeAbgelaufendeItems(kuehlschrankId, tageBisAblauf);
        var response = items.stream().map(itemMapper::map).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bestand")
    public ResponseEntity<List<ItemResource>> getBestandGefiltert(
            @RequestParam(required = false) Integer kuehlschrankId, 
            @RequestParam(required = false) String kategorie,      
            @RequestParam(required = false) String status,          
            @RequestParam(defaultValue = "name") String sortBy     
    ) {
        var items = queryService.filterUndSortiereBestand(kuehlschrankId, kategorie, status, sortBy);
        var response = items.stream().map(itemMapper::map).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }
}
