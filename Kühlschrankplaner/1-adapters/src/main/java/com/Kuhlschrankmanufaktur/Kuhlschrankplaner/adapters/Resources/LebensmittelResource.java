package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

public class LebensmittelResource {

    public String name;
    public String kategorie;
    public String einheit;
    public int minMenge;

    public LebensmittelResource(String name, String kategorie, String einheit, int minMenge) {
        this.name = name;
        this.kategorie = kategorie;
        this.einheit = einheit;
        this.minMenge = minMenge;
    }
    public String getName() { return name; }
    public String getKategorie() { return kategorie; }
    public String getEinheit() { return einheit; }
    public int getMinMenge() { return minMenge; }
}
