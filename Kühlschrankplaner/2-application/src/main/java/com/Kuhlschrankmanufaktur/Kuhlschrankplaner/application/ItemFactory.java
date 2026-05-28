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
            String lebensmittelName,
            String einheit,
            String kategorie,
            LocalDate haltbarkeit,
            int anzahl) {

        Einheit einheitDomain = parseEinheit(einheit);
        Kategorie kategorieDomain = parseKategorie(kategorie);

        Lebensmittel lebensmittel = new Lebensmittel(
                lebensmittelName,
                kategorieDomain
        );

        Haltbarkeitsdatum haltbarkeitsdatum = new Haltbarkeitsdatum(haltbarkeit);

        return new Item(
                lebensmittel,
                haltbarkeitsdatum,
                anzahl,
                einheitDomain
        );
    }

    private Kategorie parseKategorie(String kategorie) {
        return Kategorie.valueOf(kategorie.toUpperCase());
    }

    private Einheit parseEinheit(String einheit) {
        return Einheit.valueOf(einheit.toUpperCase());
    }
}