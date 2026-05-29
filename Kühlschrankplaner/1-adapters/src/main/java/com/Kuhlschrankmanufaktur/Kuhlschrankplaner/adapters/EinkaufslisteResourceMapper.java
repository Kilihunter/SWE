package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslisteResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einkaufsliste;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EinkaufslisteResourceMapper {

    public EinkaufslisteResource map(Einkaufsliste liste) {
        Map<String, Integer> eintraegeNamen = liste.getEintraege().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().getName(),
                        Map.Entry::getValue
                ));
        return new EinkaufslisteResource(liste.getId(), liste.getName(), eintraegeNamen);
    }
}
