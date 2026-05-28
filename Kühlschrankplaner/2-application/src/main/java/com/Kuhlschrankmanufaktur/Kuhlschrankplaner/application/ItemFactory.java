package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.application;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Einheit;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Haltbarkeitsdatum;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Item;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Kategorie;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Lebensmittel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemFactory {

    public Item erstelleItem(
            Lebensmittel lebensmittel,
            LocalDate haltbarkeit,
            int anzahl) {


        Haltbarkeitsdatum haltbarkeitsdatum = new Haltbarkeitsdatum(haltbarkeit);

        return new Item(
                lebensmittel,
                haltbarkeitsdatum,
                anzahl
        );
    }

}