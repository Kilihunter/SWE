/*package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EinkaufslisteTest {

    private Einkaufsliste liste;

    @BeforeEach
    void setUp() {
        liste = new Einkaufsliste("Wocheneinkauf");
    }

    @Test
    void schreibeAuf_summiertBeiGleichemLebensmittel() {
        liste.schreibeAuf(2, "Milch");
        liste.schreibeAuf(3, "Milch");

        assertEquals(5, liste.getEintraege().get("Milch"));
    }

    @Test
    void eingekauft_reduziertAnzahl() {
        liste.schreibeAuf(5, "Eier");

        liste.eingekauft(3, "Eier");

        assertEquals(2, liste.getEintraege().get("Eier"));
    }

    @Test
    void eingekauft_entferntEintragBeiKomplettEingekauft() {
        liste.schreibeAuf(3, "Milch");

        liste.eingekauft(3, "Milch");

        assertFalse(liste.getEintraege().containsKey("Milch"));
    }

    @Test
    void eingekauft_nichtAufListeWirftException() {
        assertThrows(IllegalArgumentException.class, () -> liste.eingekauft(1, "Steak"));
    }
}
*/