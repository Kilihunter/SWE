package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

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
    void schreibeAuf_überschreibtBeiGleichemLebensmittel() {
        Lebensmittel milch = new Lebensmittel("Milch", Kategorie.GETRAENKE, Einheit.LITER, 1);
        liste.schreibeAuf(2, milch);
        liste.schreibeAuf(3, milch);

        assertEquals(3, liste.getEintraege().get(milch));
    }

    @Test
    void eingekauft_reduziertAnzahl() {
        Lebensmittel eier = new Lebensmittel("Eier", Kategorie.EIER, Einheit.STUECK, 1);
        liste.schreibeAuf(5, eier);

        liste.eingekauft(3, eier);

        assertEquals(2, liste.getEintraege().get(eier));
    }

    @Test
    void eingekauft_entferntEintragBeiKomplettEingekauft() {
        Lebensmittel milch = new Lebensmittel("Milch", Kategorie.GETRAENKE, Einheit.LITER, 1);
        liste.schreibeAuf(3, milch);

        liste.eingekauft(3, milch);

        assertFalse(liste.getEintraege().containsKey(milch));
    }

    @Test
    void eingekauft_nichtAufListeWirftException() {
        Lebensmittel steak = new Lebensmittel("Steak", Kategorie.FLEISCH, Einheit.STUECK, 1);
        assertThrows(IllegalArgumentException.class, () -> liste.eingekauft(1, steak));
    }
}
