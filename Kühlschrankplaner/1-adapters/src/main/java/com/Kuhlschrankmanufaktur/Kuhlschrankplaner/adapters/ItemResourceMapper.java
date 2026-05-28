package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Item;

import java.time.LocalDate;

import org.springframework.stereotype.Component;


@Component
public class ItemResourceMapper {
    public ItemResource map(Item item) {
        Integer id = item.getId();
        String lebensmittelname = item.getLebensmittel().getName();
        Integer anzahl = item.getAnzahl();
        LocalDate haltbarkeit = item.getHaltbarkeit().getDatum();

        return new ItemResource(id, lebensmittelname, anzahl, haltbarkeit);
    }
}
