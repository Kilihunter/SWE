package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

import java.util.Map;

import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain.Lebensmittel;

public class EinkaufslisteResource {

    public Integer id;
    public String name;
    public Map<Lebensmittel, Integer> eintraege;

    public EinkaufslisteResource(Integer id, String name, Map<Lebensmittel, Integer> eintraege) {
        this.id = id;
        this.name = name;
        this.eintraege = eintraege;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Map<Lebensmittel, Integer> getEintraege() { return eintraege; }
}
