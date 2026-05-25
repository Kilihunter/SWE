package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

import java.time.LocalDate;

public class ItemResource {
    
    public Integer Itemid;
    public String name;
    public String kategorie;
    public int menge;
    public String einheit;
    public LocalDate haltbarkeit;

    public ItemResource(Integer id, String name, String kategorie, int menge, String einheit, LocalDate haltbarkeit) {
        this.Itemid = id;
        this.name = name;
        this.kategorie = kategorie;
        this.menge = menge;
        this.einheit = einheit;
        this.haltbarkeit = haltbarkeit;
    }

    public String getKategorie() {
        return kategorie;
    }
    public String getEinheit() {
        return einheit;
    }
    public LocalDate getHaltbarkeit() {
        return haltbarkeit;
    }
    public Integer getItemid() {
        return Itemid;
    }
    public int getMenge() {
        return menge;
    }
    public String getName() {
        return name;
    }
}
