package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources.ItemResource;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Item;

import java.time.LocalDate;

import org.springframework.stereotype.Component;


@Component
public class ItemResourceMapper {
    public ItemResource map(Item item) {
        Integer id = item.getId();
        String name = item.getLebensmittel().getName();
        String kategorie = item.getLebensmittel().getKategorie().name();
        int menge = item.getMenge().getAnzahl();
        String einheit = item.getMenge().getEinheit().name();
        LocalDate haltbarkeit = item.getHaltbarkeit().getDatum();

        return new ItemResource(id, name, kategorie, menge, einheit, haltbarkeit);
    }
}
