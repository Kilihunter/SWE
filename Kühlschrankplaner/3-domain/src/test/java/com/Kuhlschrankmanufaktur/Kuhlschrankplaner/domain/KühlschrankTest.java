package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class KühlschrankTest {

    private Kühlschrank kühlschrank;

    @BeforeEach
    void setUp() {
        kühlschrank = new Kühlschrank("Küche");
    }

    @Test
    void itemHinzufuegen_fügtItemHinzuUndSetztReferenz() {
        Item item = erstelleItem("Milch", 2, Einheit.PACKUNG);

        kühlschrank.itemHinzufuegen(item);

        assertEquals(1, kühlschrank.getItems().size());
        assertSame(kühlschrank, item.getKühlschrank());
    }

    @Test
    void itemVerbrauchen_kompletterVerbrauchEntferntItem() {
        Item item = erstelleItem("Milch", 3, Einheit.PACKUNG);
        kühlschrank.itemHinzufuegen(item);

        kühlschrank.itemVerbrauchen(item, 3);

        assertTrue(kühlschrank.getItems().isEmpty());
    }

    @Test
    void itemVerbrauchen_zuVielWirftException() {
        Item item = erstelleItem("Milch", 2, Einheit.PACKUNG);
        kühlschrank.itemHinzufuegen(item);

        assertThrows(IllegalArgumentException.class, () -> kühlschrank.itemVerbrauchen(item, 5));
    }

    private Item erstelleItem(String name, int anzahl, Einheit einheit) {
        Lebensmittel lebensmittel = new Lebensmittel(name, Kategorie.MILCHPRODUKTE, einheit, 0);
        Haltbarkeitsdatum haltbarkeit = new Haltbarkeitsdatum(LocalDate.now().plusDays(5));
        return new Item(lebensmittel, haltbarkeit, anzahl);
    }

}
