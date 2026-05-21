package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

import java.time.LocalDate;

public class ItemErstellenResource {

    public String name;
    public String kategorie;
    public int menge;
    public String einheit;
    public LocalDate haltbarkeit;

    public String getName() {
        return name;
    }

    public String getKategorie() {
        return kategorie;
    }

    public int getMenge() {
        return menge;
    }

    public String getEinheit() {
        return einheit;
    }

    public LocalDate getHaltbarkeit() {
        return haltbarkeit;
    }
}