/*package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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
    void itemVerbrauchen_kompletterVerbrauchEntferntItem() throws Exception {
        Item item = erstelleItem("Milch", 3, Einheit.PACKUNG);
        setId(item, 1);
        kühlschrank.itemHinzufuegen(item);

        kühlschrank.itemVerbrauchen(1, 3);

        assertTrue(kühlschrank.getItems().isEmpty());
    }

    @Test
    void itemVerbrauchen_zuVielWirftException() throws Exception {
        Item item = erstelleItem("Milch", 2, Einheit.PACKUNG);
        setId(item, 1);
        kühlschrank.itemHinzufuegen(item);

        assertThrows(IllegalArgumentException.class, () -> kühlschrank.itemVerbrauchen(1, 5));
    }

    private Item erstelleItem(String name, int anzahl, Einheit einheit) {
        Lebensmittel lebensmittel = new Lebensmittel(name, Kategorie.MILCHPRODUKTE);
        Haltbarkeitsdatum haltbarkeit = new Haltbarkeitsdatum(LocalDate.now().plusDays(7));
        return new Item(lebensmittel, haltbarkeit, anzahl, einheit);
    }

    private void setId(Item item, int id) throws Exception {
        Field idField = Item.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(item, id);
    }
}
*/