package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

import java.time.LocalDate;

public class ItemErstellenResource {

    public String lebensmittelName;
    public Integer anzahl;
    public LocalDate haltbarkeit;

    public ItemErstellenResource(String lebensmittelName, Integer anzahl, LocalDate haltbarkeit) {
        this.lebensmittelName = lebensmittelName;
        this.anzahl = anzahl;
        this.haltbarkeit = haltbarkeit;
    }

    public String getLebensmittelName() {
        return lebensmittelName;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public LocalDate getHaltbarkeit() {
        return haltbarkeit;
    }
}