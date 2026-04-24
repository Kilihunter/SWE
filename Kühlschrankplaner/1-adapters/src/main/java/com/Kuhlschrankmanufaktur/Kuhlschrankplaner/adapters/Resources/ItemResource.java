package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;
public class ItemResource {
    
    public Integer id;
    public String name;
    public String kategorie;
    public int menge;
    public String einheit;
    public String haltbarkeit;

    public ItemResource(Integer id, String name, String kategorie, int menge, String einheit, String haltbarkeit) {
        this.id = id;
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
    public String getHaltbarkeit() {
        return haltbarkeit;
    }
    public Integer getId() {
        return id;
    }
    public int getMenge() {
        return menge;
    }
    public String getName() {
        return name;
    }
}
