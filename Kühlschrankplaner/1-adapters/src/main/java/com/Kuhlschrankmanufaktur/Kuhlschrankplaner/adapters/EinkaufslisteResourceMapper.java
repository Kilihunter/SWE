package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.EinkaufslisteResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einkaufsliste;
import org.springframework.stereotype.Component;

@Component
public class EinkaufslisteResourceMapper {

    public EinkaufslisteResource map(Einkaufsliste liste) {
        return new EinkaufslisteResource(liste.getId(), liste.getName(), liste.getEintraege());
    }
}
